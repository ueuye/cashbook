package cash.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cash.model.CashbookDao;

@SuppressWarnings("serial")
@WebServlet("/on/chartAjax")
public class ChartAjaxController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		// 디버깅
		System.out.println(memberId + " <-- chartAjax Controller memberId");
		System.out.println(targetYear + " <-- chartAjax Controller targetYear");
		System.out.println(targetMonth + " <-- chartAjax Controller targetMonth");
		
		CashbookDao cd = new CashbookDao();
		List<Map<String, Object>> list = null;
		list = cd.monthSum(memberId, targetYear, targetMonth+1);
		// 디버깅
		System.out.println(list.size() + " <--chartAjax Controller list.size");
		
		// 자바객체 list변수를 json문자열 변경
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		// 디버깅
		System.out.println(jsonStr + " <--chartAjax Controller jsonStr");
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonStr);

	}
}
