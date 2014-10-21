package com.pmbox.pm.service;

public class Validator {
	private final String isValid = "VALID";
	private final String isNull = "IS NULL";
	private final String notInt = "IS NOT INTEGER";
	private final String confirmPassword = "CONFIRM PASSWORD DOES NOT MATCH PASSWORD";
	private final String notEmail = "IS NOT VALID EMAIL ADDRESS";
	//not null
	public static boolean isNull(String input){
		String status = "VALID";
		return input.trim().equals("");		
	}
	
			//if is int
			//confirm password == password
			//email format
}
