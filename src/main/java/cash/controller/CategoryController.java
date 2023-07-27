package cash.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cash.model.CategoryDao;

@SuppressWarnings("serial")
@WebServlet("/on/category")
public class CategoryController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		CategoryDao cd = new CategoryDao();
		ArrayList<String> list = null;
		try {
			list = cd.selectSubcateList(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 자바객체 list변수를 json문자열 변경
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonStr.toString());
	}
	
}
