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
@WebServlet("/on/modifyMember")
public class ModifyMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		// 모델 값 구하기(Dao 메서드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		
		request.setAttribute("member", member);
				
		// jsp페이지로 포워드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 요청값 변수에 저장
		String memberPw = request.getParameter("memberPw");
				
		Member member = new Member();
		member.setMemberId(loginMember.getMemberId());
		member.setMemberPw(memberPw);
		
		// Dao호출
		MemberDao memberDao = new MemberDao();
				
		int row = memberDao.modifyMember(member);
		if(row == 1) { // 회원정보수정 성공
			// memberOne.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath()+"/on/memberOne");
			System.out.println("회원정보수정 성공");
			return;
		}else {// 회원정보수정 실패
			// modifyMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath()+"/on/modifyMember");
			System.out.println("회원정보수정 실패");
		}
	}

}
