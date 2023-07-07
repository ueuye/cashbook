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
@WebServlet("/on/cashbookListByTag")
public class CashbookListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String word = request.getParameter("word");
		
		CashbookDao cashbookDao = new CashbookDao();
		
		// 페이징 알고리즘
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage -1)*rowPerPage;
		int totalRow = cashbookDao.cashbookByTagCnt(loginMember.getMemberId(), word);
		int lastPage = totalRow / rowPerPage;
		if(totalRow % rowPerPage != 0){
			lastPage = lastPage + 1;
		}
		if(totalRow < currentPage){
			currentPage = lastPage;
		}
		int pagePerPage = 5;
		int startPage = ((currentPage - 1) / pagePerPage) * pagePerPage + 1;
		int endPage = startPage + pagePerPage - 1;
		if(endPage > lastPage){
			endPage = lastPage;
		}

		List<Cashbook> list = cashbookDao.selectCashbookListByTag(loginMember.getMemberId(), word, beginRow, rowPerPage);
		
		request.setAttribute("list", list);
		request.setAttribute("word", word);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

}
