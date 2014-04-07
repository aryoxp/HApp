package com.labmobile.happ;

import com.labmobile.happ.utility.LanguageUtility;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class AppSettingActivity extends PreferenceActivity 
	implements OnPreferenceChangeListener {
	
	ListPreference languagePreference;
	EditTextPreference portNumberPreference;
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		languagePreference = (ListPreference) this.findPreference("prefLanguage");
		languagePreference.setOnPreferenceChangeListener(this);
		portNumberPreference = (EditTextPreference) this.findPreference("prefPortNumber");
		portNumberPreference.setSummary("TCP/IP Port " + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("prefPortNumber", "8888"));
		portNumberPreference.setOnPreferenceChangeListener(this);
		
		LanguageUtility.configureLanguage(getBaseContext());
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if(preference.getKey().equals("prefLanguage")) {			
			languagePreference.setValue((String)newValue);
			LanguageUtility.configureLanguage(getBaseContext(), (String)newValue);
			finish();
			this.startActivity(getIntent());
		} else if(preference.getKey().equals("prefPortNumber")) {
			portNumberPreference.setText((String)newValue);
			portNumberPreference.setSummary("TCP/IP Port " + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("prefPortNumber", "8888"));
			//finish();
			//this.startActivity(getIntent());
		}
		return false;
	}
	
}
