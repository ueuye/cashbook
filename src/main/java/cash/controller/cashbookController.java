package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/cashbook")
public class cashbookController extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(loginMember.getMemberId(), targetYear, targetMonth+1, targetDate);
		request.setAttribute("list", list);
		 
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);	
		 
		// 이번달 달력에 가계부목록의 모델값을 세팅
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request,response);
	}

}
