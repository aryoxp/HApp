package com.labmobile.happ.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.labmobile.happ.service.interfaces.CommunicationInterface;

public class CommunicationThread implements Runnable {

	Socket clientSocket;
	BufferedReader input;
	CommunicationInterface communicationInterface;
	
	public CommunicationThread(Socket clientSocket, CommunicationInterface communicationInterface) {
		this.clientSocket = clientSocket;
		this.communicationInterface = communicationInterface;
		try {
			this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			this.communicationInterface.putMessage(input.readLine());
			this.communicationInterface.processMessage();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}