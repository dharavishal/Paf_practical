package com;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java_cup.internal.runtime.Scanner;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class ItemsAPI extends HttpServlet {
	
Item i = new Item();
	
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String output = i.insertItem(request.getParameter("code"),
				request.getParameter("name"),
				request.getParameter("price"),
				request.getParameter("des"));
		
		response.getWriter().write(output);
	}
	
	//convert request parameters to a map
	private static Map getParasMap(HttpServletRequest request) {
		
		Map<String , String> map = new HashMap<String,String>();
		try
		{
			//String param = "";
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param: params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}catch (Exception e)
		{
			
		}
		return map;
		
	}
	

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = funds.updateProj(paras.get("hidItemIDSave").toString(),
				paras.get("email").toString(),
				paras.get("name").toString(),
				paras.get("address").toString(),
				paras.get("phone").toString(),
				paras.get("des").toString());
		
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = funds.deleteProj(paras.get("fundID").toString());
		
		response.getWriter().write(output);
	}
}
