package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/off/login")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키에 저장된 아이디가 있다면 request속성에 저장 
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("loginId")) {
					request.setAttribute("loginId", c.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
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
			response.sendRedirect(request.getContextPath()+"/off/login");
			return;
		}else { // 로그인 성공 : session사용
			HttpSession session = request.getSession();
			System.out.println("로그인 성공");
			session.setAttribute("loginMember", loginMember);
			
			// idSave 체크값이 넘어 왔다면 쿠키에 저장
			if(request.getParameter("idSave") != null) {
				Cookie loginIdcookie =  new Cookie("loginId", memberId);
				loginIdcookie.setMaxAge(60*60*24); // 하루
				response.addCookie(loginIdcookie);
			}
			
			response.sendRedirect(request.getContextPath()+"/on/calendar");
		}
		
	}

}
