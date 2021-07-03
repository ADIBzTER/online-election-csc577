package com.config;

import java.text.DecimalFormat;

public class CurrencyFormatter {

	public static String format(double number) {
		DecimalFormat df = new DecimalFormat("#,###,###.00");
		return df.format(number);
	}
}