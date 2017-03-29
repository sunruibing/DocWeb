package com.servlet.registration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.po.Registration;
import com.po.User;
import com.service.RegistrationService;
import com.service.UserService;
import com.util.StringUtil;
@WebServlet(name = "AddRegUser", urlPatterns = {"/AddRegUser"})
public class AddRegUser extends HttpServlet {
	private static final long serialVersionUID = 2215112232612039778L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String name = request.getParameter("name");
		   System.out.print(name); 
		
		String gender = request.getParameter("gender");
		String section = request.getParameter("section");
		String  age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reservationDate = request.getParameter("time");
		
		Registration registration = new Registration();
		registration.setName(name);
		registration.setGender(gender);
		registration.setSection(section);
		registration.setAge(age);
		registration.setPhone(phone);
		registration.setCity(city);
		registration.setOrderCode(orderCode);
		registration.setReservationDate(reservationDate);
		UserService userService = new UserService();
		try {
			User user = userService.queryUserByPhone(phone);
			System.out.println("user......................\n"+user);
			if (user.getPhone()!=null) {
				RegistrationService registrationService = new RegistrationService();
				registrationService.insertUser(registration);
				response.getWriter().print(0);
			}else {
				User user2 = new User();
				BeanUtils.populate(user2, request.getParameterMap());
				user2.setPassword("123456");
				System.out.println("user......................\n"+user2);
				int result = userService.addUser(user2);
				if (result>0) {
					RegistrationService registrationService = new RegistrationService();
					registrationService.insertUser(registration);
					response.getWriter().print(0);
				}else {
					response.getWriter().print(1);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			response.getWriter().print(1);
		}
	}
}
