package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.model.HashtagDao;

@SuppressWarnings("serial")
@WebServlet("/on/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		CashbookDao cashbookDao = new CashbookDao();
		HashtagDao hashtagDao = new HashtagDao();
		
		int row = cashbookDao.removeCashbook(cashbookNo);
		if(row == 1) { // 삭제 성공
			hashtagDao.removeHashtag(cashbookNo);
			response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
			return;
		}else {// 삭제 실패
			response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
		}
	
	}
}
