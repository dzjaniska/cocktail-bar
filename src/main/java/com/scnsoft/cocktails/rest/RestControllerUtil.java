package com.scnsoft.cocktails.rest;

import java.util.Arrays;
import java.util.List;

public class RestControllerUtil {
	
	public static String processLang(String lang) {
		lang = lang.toLowerCase();
		List<String> langs = Arrays.asList("en", "ru");
		if (!langs.contains(lang))
			throw new InvalidLangException(lang);
		lang = lang.substring(0, 1).toUpperCase() + lang.substring(1).toLowerCase();
		return lang;
	}
}
