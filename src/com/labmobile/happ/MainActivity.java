package com.labmobile.happ;

import com.labmobile.happ.service.AppService;
import com.labmobile.happ.service.AppService.ServiceBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Intent serviceIntent;
	AppService appService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.mainNetworkButton).setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		this.serviceIntent = new Intent(getApplicationContext(), AppService.class);
		this.serviceIntent.putExtra("AppService", "Service Start");
		this.bindService(serviceIntent, this.serviceConnection, Context.BIND_AUTO_CREATE);
		this.startService(this.serviceIntent);
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		this.unbindService(serviceConnection);
		if(this.serviceIntent != null)
			this.stopService(serviceIntent);
		super.onDestroy();
	}
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			appService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			AppService.ServiceBinder serviceBinder = (ServiceBinder) service;
			appService = serviceBinder.getService();
			Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT)
	          .show();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mainNetworkButton:
			Intent i = new Intent(getApplicationContext(), NetworkActivity.class);
			this.startActivity(i);
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent i = new Intent(getApplicationContext(), ApplicationSettings.class);
		this.startActivity(i);
		return super.onOptionsItemSelected(item);
	}
}
