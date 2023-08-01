package com.cnzh.csjl.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class StrUtil {
	
	public static String Timesamp2String(Timestamp timesamp) {
		 final SimpleDateFormat TimesFormat= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		if(null!=timesamp) {
			try {
			return TimesFormat.format(timesamp);
			}catch (Exception e) {
				System.err.println("Timesamp2String:"+e.toString());
			}
			return TimesFormat.format(0);
		}
		else {
			return TimesFormat.format(0);
		}
	}

}

