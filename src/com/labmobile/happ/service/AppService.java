package com.labmobile.happ.service;

import com.labmobile.happ.service.interfaces.CommunicationInterface;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AppService extends Service implements CommunicationInterface {
	
	private String message;
	private Handler messageHandler;
	private Thread serverThread;
	private CommunicationServerRunnable communicationServerRunnable;
	
	private final IBinder binderInterface = new ServiceBinder();
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("Hotel App Service", "Service started");
		this.messageHandler = new Handler();
		if(this.serverThread == null || !this.serverThread.isAlive()) {
			this.communicationServerRunnable = new CommunicationServerRunnable(this);
			this.serverThread = new Thread(communicationServerRunnable);
			this.serverThread.start();
		}
		return Service.START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return this.binderInterface;
	}

	@Override
	public void putMessage(String message) {
		this.message = message;
	}

	@Override
	public void onDestroy() {
		
		this.serverThread.interrupt();
		this.communicationServerRunnable.closeServerSocket();
		this.stopService();
		Log.d("Hotel App Service", "Server socket closed");
		super.onDestroy();
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void processMessage() {
		final String messageText = this.getMessage();
		this.messageHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void stopService() {
		this.stopSelf();
		Log.d("Hotel App Service", "Service stopped");
	}
	
	public class ServiceBinder extends Binder {
		public AppService getService() {
			return AppService.this;
		}
	}

}
