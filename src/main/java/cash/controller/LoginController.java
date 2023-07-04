package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) {
	         response.sendRedirect(request.getContextPath() + "/calendar");
	         return;
	      }
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청값 변수에 저장
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 요청값을 하나의 데이터로 만든다
		Member member = new Member(memberId, memberPw, null, null);
		
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.SelectMemberById(member);
		
		if(loginMember == null) { // null이면 로그인 실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}else { // 로그인 성공 : session사용
			HttpSession session = request.getSession();
			System.out.println("로그인 성공");
			session.setAttribute("loginMember", loginMember);
			response.sendRedirect(request.getContextPath()+"/calendar");
		}
		
	}

}
