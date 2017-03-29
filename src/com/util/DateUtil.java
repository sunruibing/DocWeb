package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice
 *         2016å¹?10æœ?12æ—?
 */
public class DateUtil {
	private static final DateFormat FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static List<String> getTimeSlot(String start, String end)
			throws ParseException {
		List<String> list = new ArrayList<String>();
		if (start.compareTo(end) == 0) {
			list.add(start);
			return list;
		} else {
			Calendar startDay = Calendar.getInstance();
			Calendar endDay = Calendar.getInstance();
			startDay.setTime(FORMATTER.parse(start));
			endDay.setTime(FORMATTER.parse(end));
			Calendar currentDay = startDay;
			list.add(start);
			while (true) {
				currentDay.add(Calendar.DATE, 1);
				if (currentDay.compareTo(endDay) == 0) {
					break;
				}
				String middleDay = FORMATTER.format(currentDay.getTime());
				list.add(middleDay);
			}
			list.add(end);
			return list;
		}
	}
	public static String DateToString(Date date) {
		if (date != null && !"".equals(date)) {
			String dateSting = new SimpleDateFormat("yyyy-MM-dd hh:mm")
					.format(date);
			return dateSting;
		}
		return "";
	}
}
