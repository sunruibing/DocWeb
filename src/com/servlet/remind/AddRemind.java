package com.servlet.remind;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Remind;
import com.service.CalendarService;
import com.service.RemindService;
import com.util.DateUtil;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice
 *         2016年10月12日
 */
@WebServlet(name = "AddRemind", urlPatterns = {"/AddRemind"})
public class AddRemind extends HttpServlet {
	private static final long serialVersionUID = -748573528225131756L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));// user
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		String time3 = request.getParameter("time3");
		String content1 = request.getParameter("content1");
		String content2 = request.getParameter("content2");
		String content3 = request.getParameter("content3");
		String timestamp = String.valueOf(new Date().getTime());
		RemindService remindService = new RemindService();
		try {
			Integer remindId = remindService
					.queryRemindIdByDateAndUserId(startDay, userId);
			if (0 == remindId) {
				Remind remind = new Remind();
				remind.setTime1(time1);
				remind.setTime2(time2);
				remind.setTime3(time3);
				remind.setContent1(content1);
				remind.setContent2(content2);
				remind.setContent3(content3);
				remind.setUserId(userId);
				remind.setTimestamp(timestamp);
				int msg = 0;
				List<String> dateList = DateUtil.getTimeSlot(startDay, endDay);
				for (Iterator<String> i = dateList.iterator(); i.hasNext();) {
					String remindDate = i.next();
					remind.setRemindDate(remindDate);
					msg = remindService.insertRemindByUserId(remind);
				}
				if (1 == msg) {
					response.getWriter().print(0);// success
					CalendarService calendarService = new CalendarService();
					for (Iterator<String> i = dateList.iterator(); i
							.hasNext();) {
						String remindDate = i.next();
						Integer DBremindId = remindService
								.queryRemindIdByDateAndUserId(remindDate,
										userId);
						Integer calendarId = calendarService
								.queryCalendarIdByDateAndUserId(remindDate,
										userId);
						if (0 == calendarId) {
							calendarService.insertRemindId(remindDate,
									DBremindId, userId);
						} else {
							calendarService.updateRemindId(remindDate,
									DBremindId, userId);
						}
					}
				} else {
					response.getWriter().print(2);// error
				}
			} else {
				response.getWriter().print(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			response.getWriter().print(2);// SQL error
		} catch (ParseException e) {
			e.printStackTrace();
			response.getWriter().print(1);// Parse error
		}
	}
}
