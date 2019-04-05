package com.desafio.cf.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}

	public static boolean isEmailInvalido(String email) {
		return !validateEmail(email);
	}
	
	public static boolean isAlgumEmailInvalido(Collection<String> emails) {
		return emails.stream().anyMatch(email -> isEmailInvalido(email));
	}
	
	public static boolean isTelefoneInvalido(String telefone) {
		return !telefone.matches("^[0-9]{10,11}$");
	}

	public static boolean isAlgumTelefoneInvalido(Collection<String> telefones) {
		return telefones.stream().anyMatch(telefone -> isTelefoneInvalido(telefone));
	}
}
