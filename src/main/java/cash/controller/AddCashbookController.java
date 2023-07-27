package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/on/addCashbook")
public class AddCashbookController extends HttpServlet {
	// 입력폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인정보 저장
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// request 매개값
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		//ex)4월을 04월로 나타내기 위해 
		String strM = (targetMonth+1)+""; //숫자 targetMonth을 공백붙여서 문자로 바꿈
		if(targetMonth < 10){
			strM = "0"+strM;
		}
		//ex)1일을 01일로 나타내기 위해 
		String strD = targetDate+"";
		if(targetDate < 10){
			strD = "0"+strD;
		}
		String cashbookDate = targetYear+"-"+strM+"-"+strD;
		// 나머지 데이터는 입력폼에서 사용자가 입력
		
		request.setAttribute("memberId", loginMember.getMemberId());
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);	
		request.setAttribute("cashbookDate", cashbookDate);
		
		// jsp페이지로 포워드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
	}
	
	// 입력액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// request 매개값
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		String memberId = request.getParameter("memberId");
		String cashbookDate = request.getParameter("cashbookDate");
		String category = request.getParameter("category");
		String subcategory = request.getParameter("subcategory");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setCategory(category);
		cashbook.setSubcategory(subcategory);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook); // 키값 반환
		// 입력 실패시
		if(cashbookNo == 0) {
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/on/addCashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
			return;
		}
		// 입력 성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
		System.out.println("가계부 입력성공");
		
		// 해시태그 추출 알고리즘
		HashtagDao hashtagDao = new HashtagDao();
		String memo1 = cashbook.getMemo();
		String memo2 = memo1.replace("#", " #"); // "#구디#아카데미" -> " #구디 #아카데미"
		
		Set<String> set = new HashSet<String>(); // 중복된 해시태그방지를 위해 set자료구조를 사용
		// 해시태그가 여러개이면 반복해서 입력
		for(String ht : memo2.split(" ")) { // issue : split된 배열을 Set으로 변경하면 중복된 내용 제거 가능
			if (ht.startsWith("#")) {
                String ht2 = ht.replace("#", "");
			    if(ht2.length() > 0) {
                    set.add(ht2); // set은 중복된 값은 add되지 않는다
			    }
            }
		}
        for(String s : set) {
    		Hashtag hashtag = new Hashtag();
			hashtag.setCashbookNo(cashbookNo);
			hashtag.setWord(s);
			hashtagDao.insertHashtag(hashtag);
			System.out.println("hashtag입력성공");
        }
		
		// redirect -> CalendarByDateController -> forward -> jsp
		response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}
}
