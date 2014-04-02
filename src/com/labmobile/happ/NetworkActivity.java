package com.labmobile.happ;
import com.labmobile.netutil.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class NetworkActivity extends Activity {
	
	TextView networkIpAddressText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_network);
		this.networkIpAddressText = (TextView) findViewById(R.id.networkIpAddressText);
	}
	
	@Override
	protected void onResume() {
		this.networkIpAddressText.setText( Utils.getIPAddress(true) );
		super.onResume();
	}
}
