package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;

@SuppressWarnings("serial")
@WebServlet("/on/modifyCashbook")
public class ModifyCashbookController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		cashbook = cashbookDao.selectCashbookOne(cashbookNo);
				
		//request.setAttribute("cashbookNo", cashbookNo);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("cashbook", cashbook);
		
		request.getRequestDispatcher("/WEB-INF/view/modifyCashbook.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCategory(category);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		CashbookDao cashbookDao = new CashbookDao();
		int row = cashbookDao.modifyCashbook(cashbook);
		if(row == 1) { // 가계부 수정 성공
			System.out.println("가계부 수정 성공");
			// 기존해시태그 삭제
			HashtagDao hashtagDao = new HashtagDao();
			hashtagDao.removeHashtag(cashbookNo);
			// 해시태그 추출 알고리즘
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
	        
	        response.sendRedirect(request.getContextPath()+"/on/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
			
			return;
		}else {// 가계부 수정 실패
			response.sendRedirect(request.getContextPath()+"/on/modifyCashbook");
			System.out.println("가계부 수정 실패");
		}
	}

}
