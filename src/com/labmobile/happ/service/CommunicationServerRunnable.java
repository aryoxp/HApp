package com.labmobile.happ.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.labmobile.happ.service.interfaces.CommunicationInterface;

public class CommunicationServerRunnable implements Runnable {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private CommunicationInterface communicationInterface;
	public static final int SERVERPORT = 8888;
	
	
	public CommunicationServerRunnable(CommunicationInterface communicationInterface) {
		this.communicationInterface = communicationInterface;
	}
	
	@Override
	public void run() {
		this.clientSocket = null;
		try {
			this.serverSocket = new ServerSocket(SERVERPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		while(!Thread.currentThread().isInterrupted()) {
			try {
				this.clientSocket = this.serverSocket.accept();
				CommunicationThread communicationThread = new CommunicationThread(clientSocket, communicationInterface);
				new Thread(communicationThread).start();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				break;
			}
		}
		this.closeServerSocket();
	}
	
	public void closeServerSocket() {
		try {
			this.serverSocket.close();
			this.clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
