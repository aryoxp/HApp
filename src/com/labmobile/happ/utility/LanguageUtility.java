package com.labmobile.happ.utility;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

public class LanguageUtility {
	public static void configureLanguage(Context context, String language) {
		Locale locale = new Locale(language);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources().updateConfiguration(config,
				context.getResources().getDisplayMetrics());
	}
	
	public static void configureLanguage(Context context) {
		String language = PreferenceManager.getDefaultSharedPreferences(context)
				.getString("prefLanguage", "en");
		LanguageUtility.configureLanguage(context, language);
	}
}
