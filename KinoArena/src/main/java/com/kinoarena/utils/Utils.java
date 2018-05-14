package com.kinoarena.utils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public abstract class Utils {
	private static final String EMAIL_REGEX = "^(.+)@(.+)$";
	private static final String BIRTHDATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
	private static final Pattern VALID_BIRTHDATE = Pattern.compile(BIRTHDATE_REGEX);
	private static final int MIN_PASS_LENGTH = 5;
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
	private static final int GSM_WITHOUT_COUNTRY_CODE = 10;
	private static final int GSM_WITH_COUNTRY_CODE = 13;

	public static boolean emailValidator(String emailStr) {
		return EmailValidator.getInstance().isValid(emailStr);
	}

	public static boolean gsmValidator(String gsm) {
		if (checkString(gsm)) {
			if (gsm.length() >= GSM_WITHOUT_COUNTRY_CODE || gsm.length() <= GSM_WITH_COUNTRY_CODE) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkString(String text) {
		if (text != null && text.trim().length() > 0) {
			return true;
		}
		return false;
	}

	public static boolean validatePassword(String password) {
		if (checkString(password)) {
			if (password.length() >= MIN_PASS_LENGTH) {
				return true;
			}
		}
		return false;
	}

	public static boolean comparePasswords(String pass, String rePass) {
		if (pass.equals(rePass)) {
			return true;
		}
		return false;
	}

	public static boolean dateValidator(String birthdate) {
		Matcher matcher = VALID_BIRTHDATE.matcher(birthdate);

		return matcher.find();
	}

	public static boolean fileValidator(MultipartFile file, Model model) {
		if (file.getOriginalFilename().endsWith(".jpeg") || file.getOriginalFilename().endsWith(".jpg")
				|| file.getOriginalFilename().endsWith(".png")) {
			return true;
		}
		return false;
	}
}
