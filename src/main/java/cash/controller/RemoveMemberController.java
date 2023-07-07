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
@WebServlet("/on/removeMember")
public class RemoveMemberController extends HttpServlet {
	// 비밀번호 입력 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	// 탈퇴
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member();
		member.setMemberId(loginMember.getMemberId());
		member.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.removeMember(member);
		if(row == 1) { // 회원탈퇴 성공
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/off/login");
			return;
		}else {// 회원탈퇴 실패
			// removeMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath()+"/on/removeMember");
		}
	}

}
