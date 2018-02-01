package com.javens.util;


import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
	public static boolean verifyMobile(String mobile){
        if(StringUtils.isBlank(mobile)){
            return false;
        }
		String regExp = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.find();
	}

	public static boolean isInteger(String str){
		try{
			Integer.valueOf(str);
		}catch(NumberFormatException ex){
			return  false;
		}
		return true;
	}
	public static Integer getInteger(String str, Integer defaultValue){
		Integer result = defaultValue;
		try{
			result = Integer.valueOf(str);
		}catch(NumberFormatException ex){
			return result;
		}
		return result;
	}
	public static boolean isLong(String str){
		try{
			Long.valueOf(str);
		}catch(NumberFormatException ex){
			return  false;
		}
		return true;
	}
	public static Long getLong(String str, Long defaultValue){
		Long result = defaultValue;
		try{
			result = Long.valueOf(str);
		}catch(NumberFormatException ex){
			return result;
		}
		return result;
	}
	public static boolean isDouble(String str){
		try{
			Double.valueOf(str);
		}catch(NumberFormatException ex){
			return  false;
		}
		return true;
	}
	public static boolean isIDcard(String str) {
		//String regex = "(\\d{14}[0-9xX])|(\\d{17}[0-9xX])";
		return IdcardUtils.validateCard(str);
		//return match(regex, str);
	}

    public static boolean isNumber(String str)
    {
        Pattern pattern= Pattern.compile("^[1-9]\\d*$");
        Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
             return false;
        }
        else
        {
             return true;
        }
    }
	public static boolean isDate(String str){
		try{
			Long temp = getLong(str, null);
			if (temp == null) return false;

			Date value = new Date(temp);
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	public static Date getDate(String str, Date defaultValue){
		Date value = defaultValue;
		try{
			Long temp = getLong(str, null);
			if (temp == null) return value;

			value = new Date(temp);
		}catch(Exception ex){
			return value;
		}
		return value;
	}

    public static boolean isZZS(Double d)
    {
    	double y = d%1;
    	if(y == 0){
            return true;
    	}else{
            return false;
    	}
    }

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
		}
}
