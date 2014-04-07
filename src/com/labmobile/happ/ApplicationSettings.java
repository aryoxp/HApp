package com.labmobile.happ;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ApplicationSettings extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}
