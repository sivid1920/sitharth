package com.framework.utilities;

import java.util.Date;

public class DateUtil {
	public static String getDate() {
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}

}
