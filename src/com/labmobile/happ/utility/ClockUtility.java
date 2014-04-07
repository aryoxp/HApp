package com.labmobile.happ.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Handler;
import android.widget.TextView;

public class ClockUtility implements Runnable {

	TextView clockText;
	Handler timerHandler = new Handler();
	
	public ClockUtility(TextView clockText) {
		this.clockText = clockText;
	}

	@Override
	public void run() {
		Calendar c = Calendar.getInstance(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss", Locale.US);
		
        this.clockText.setText(sdf.format(c.getTime()));
		timerHandler.postDelayed(this, 500);
	}
	
	public void startClock() {
		timerHandler.postDelayed(this, 0);
	}
	
	
	public void stopClock() {
		timerHandler.removeCallbacks(this);
	}
}
