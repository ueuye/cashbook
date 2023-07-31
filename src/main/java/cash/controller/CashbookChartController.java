package cash.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/on/cashbookChart")
public class CashbookChartController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberId = loginMember.getMemberId();
		
		// view 에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance();
		
		// 출력하고자 하는 년도와 월, 일의 기본값은 이번달 1일
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		firstDay.set(Calendar.DATE, 1); // 1일
		
		// 출력하고자 하는 년도와 월이 매개값으로 넘어왔다면
		if(request.getParameter("targetYear") != null
				&& request.getParameter("targetMonth") != null) {
			
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			
			firstDay.set(Calendar.YEAR, targetYear);
			// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월 1, 년 +1
			// API에서 자동으로 Calendar.MONTH값으로 -1이 입력되면 월 12, 년 -1
			firstDay.set(Calendar.MONTH, targetMonth);
			targetYear = firstDay.get(Calendar.YEAR);
			targetMonth = firstDay.get(Calendar.MONTH);
		}
				
		// 오늘 날짜 
		Calendar today = Calendar.getInstance();
		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		
		// targetMonth 영문으로 변환
		String strTargetMonth = null;
		if(targetMonth == 0) {
			strTargetMonth = "January";
		}else if(targetMonth == 1) {
			strTargetMonth = "February";
		}else if(targetMonth == 2) {
			strTargetMonth = "March";
		}else if(targetMonth == 3) {
			strTargetMonth = "April";
		}else if(targetMonth == 4) {
			strTargetMonth = "May";
		}else if(targetMonth == 5) {
			strTargetMonth = "June";
		}else if(targetMonth == 6) {
			strTargetMonth = "July";
		}else if(targetMonth == 7) {
			strTargetMonth = "August";
		}else if(targetMonth == 8) {
			strTargetMonth = "September";
		}else if(targetMonth == 9) {
			strTargetMonth = "October";
		}else if(targetMonth == 10) {
			strTargetMonth = "November";
		}else {
			strTargetMonth = "December";
		}
		
		request.setAttribute("memberId", memberId);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		
		request.setAttribute("today", today);
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("todayMonth", todayMonth);
		
		request.setAttribute("strTargetMonth", strTargetMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookChart.jsp").forward(request,response);
		
	}

}
