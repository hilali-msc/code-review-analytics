package com.nostrasoft.da.rest.util;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtils 
{
	private DateUtils() {}
	
	public static Date getDate4YearsBeforeToday()
	{
		return new Date((Calendar.getInstance().getTime())
				.toInstant()
				.minus(Duration.of(4 * 365L, ChronoUnit.valueOf("DAYS")))
				.toEpochMilli());
	}
}
