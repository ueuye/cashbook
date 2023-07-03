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
@WebServlet("/calendarByDate")
public class CalendarByDateController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		int targetYear = Integer.parseInt(request.getParameter("y"));
		int targetMonth = Integer.parseInt(request.getParameter("m"));
		int targetDay = Integer.parseInt(request.getParameter("d"));
		
		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(loginMember.getMemberId(), targetYear, targetMonth+1, targetDay);
		request.setAttribute("list", list);
		 
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDay", targetDay);	
		 
		request.getRequestDispatcher("/WEB-INF/view/calendarByDate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
