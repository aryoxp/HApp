package com.labmobile.happ.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.labmobile.happ.service.interfaces.CommunicationInterface;

public class ServerThread implements Runnable {

	ServerSocket serverSocket;
	Socket clientSocket;
	CommunicationInterface communicationInterface;
	public static final int SERVERPORT = 8888;
	
	
	public ServerThread(CommunicationInterface communicationInterface) {
		this.communicationInterface = communicationInterface;
	}
	
	@Override
	public void run() {
		clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVERPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		while(!Thread.currentThread().isInterrupted()) {
			try {
				clientSocket = serverSocket.accept();
				CommunicationThread communicationThread = new CommunicationThread(clientSocket, communicationInterface);
				new Thread(communicationThread).start();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				break;
			}
		}
	}
	
	public void closeServerSocket() {
		try {
			this.clientSocket.close();
			//this.serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
