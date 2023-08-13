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

@WebServlet("/kakaoLogin")
public class KakaoLoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		System.out.println("로그인 아이디 확인" + email);
		
		MemberDao memberDao = new MemberDao();
		int kakaoLogin = memberDao.checkIdkakao(email);
		if(kakaoLogin == 0) {
			// 최초 카카오 로그인시 member테이블에 아이디 저장
			memberDao.insertKakao(email);
			
			Member member = new Member(email, "1234", null, null);

			Member loginMember = memberDao.SelectMemberById(member);
			
			if(loginMember == null) { // null이면 로그인 실패
				System.out.println("로그인 실패");
				response.sendRedirect(request.getContextPath()+"/off/login");
				return;
			}else { // 로그인 성공 : session사용
				HttpSession session = request.getSession();
				System.out.println("로그인 성공");
				session.setAttribute("loginMember", loginMember);
				response.sendRedirect(request.getContextPath()+"/on/calendar");
			}
		}else {
			Member member = new Member(email, "1234", null, null);

			Member loginMember = memberDao.SelectMemberById(member);
			
			if(loginMember == null) { // null이면 로그인 실패
				System.out.println("로그인 실패");
				response.sendRedirect(request.getContextPath()+"/off/login");
				return;
			}else { // 로그인 성공 : session사용
				HttpSession session = request.getSession();
				System.out.println("로그인 성공");
				session.setAttribute("loginMember", loginMember);
				response.sendRedirect(request.getContextPath()+"/on/calendar");
			}
		}
		
	}

}
