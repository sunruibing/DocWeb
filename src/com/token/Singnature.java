package com.token;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.util.SignatureUtil;

@WebServlet(name = "Singnature", urlPatterns = {"/Singnature"})
public class Singnature extends HttpServlet {

	/**
	 * 极光登陆接口
	 */
	
	private static final long serialVersionUID = -4269211652358399421L;
	
		private static final String APPKEY = "7c2a0e6211914830a77efa41";
		private static final String SECRET = "23df288a081ee007a9accebe";
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doPost(request, response);
		}
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			long  timestamp = new Date().getTime();
			String random_str = SignatureUtil.getRandomString(25);
			String signature =SignatureUtil.md5("appkey="+APPKEY+"&timestamp="+timestamp+"&random_str="+random_str +"&key="+SECRET);
			
			 Map<String,Object> result = new HashMap<String,Object>();
			 result.put("timestamp", timestamp);
			 result.put("signature", signature);
			 result.put("random_str", random_str);
			 result.put("appkey", APPKEY);
			
			 Map<String, Object> signatures = SignatureUtil.getSignature();
			 	Gson gson  = new Gson();
				String json = gson.toJson(signatures);
				System.out.println(json);
				response.getWriter().print(json);
				}
			}
			
