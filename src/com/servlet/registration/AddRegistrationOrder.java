package com.servlet.registration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Registration;
import com.service.CalendarService;
import com.service.RegistrationService;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月21日
 */
@WebServlet(name = "AddRegistrationOrder", urlPatterns = {
		"/AddRegistrationOrder"})
public class AddRegistrationOrder extends HttpServlet {
	private static final long serialVersionUID = 8285734190247620532L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String createTime = request.getParameter("createTime");
		String reservationDate = request.getParameter("time");
		int userId = Integer.parseInt(request.getParameter("userId"));
		Registration registration = new Registration();
		registration.setCity(request.getParameter("city"));
		registration.setSection(request.getParameter("section"));
		registration.setTitle(request.getParameter("title"));
		registration.setReservationDate(reservationDate);
		registration.setName(request.getParameter("name"));
		registration.setGender(request.getParameter("gender"));
		registration.setAge(request.getParameter("age"));
		registration.setPhone(request.getParameter("phone"));
		registration.setContent(request.getParameter("content"));
		registration.setMoney("25");
		registration.setOrderCode(orderCode);
		registration.setOrderStatus("待支付");
		registration.setUserId(userId);
		registration.setCreateTime(createTime);
		RegistrationService registrationService = new RegistrationService();
		try {
			Integer registrationId = registrationService
					.queryRegistrationByDateAndUserId(reservationDate, userId);
			if (0 == registrationId) {
				int msg = registrationService.insertRegistration(registration);
				if (1 == msg) {
					response.getWriter().print(orderCode);
					Integer DBregistrationId = registrationService
							.queryRegistrationByDateAndUserId(reservationDate,
									userId);
					CalendarService calendarService = new CalendarService();
					Integer calendarId = calendarService
							.queryCalendarIdByDateAndUserId(reservationDate,
									userId);
					if (0 == calendarId) {
						calendarService.insertRegistrationId(reservationDate,
								DBregistrationId, userId);
					} else {
						calendarService.updateRegistrationId(reservationDate,
								DBregistrationId, userId);
					}
				} else {
					response.getWriter().print(2);// error
				}
			} else {
				response.getWriter().print(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().print(2);// SQL error
		}
	}
}
