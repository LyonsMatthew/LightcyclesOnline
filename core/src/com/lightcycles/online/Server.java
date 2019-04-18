package com.lightcycles.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Server
{
	ServerSocketHints sshints;
	ServerSocket ssocket;
	ArrayList<Socket> clientPlayerSockets = new ArrayList<>();
	ArrayList<BufferedReader> clientPlayerSocketInputStreams = new ArrayList<>();
	ArrayList<Socket> clientBetterSockets = new ArrayList<>();
	ArrayList<BufferedReader> clientBetterSocketInputStreams = new ArrayList<>();

	public Server()
	{
		sshints = new ServerSocketHints(); //server socket properties
		sshints.acceptTimeout = 0;
		ssocket = Gdx.net.newServerSocket(Net.Protocol.TCP, 17892, sshints);

		SocketHints shints = new SocketHints(); //connecting client socket properties
		shints.connectTimeout = 0;
		shints.socketTimeout = 0;
		while (true)
		{
			Socket clientSocket = ssocket.accept(shints);
			BufferedReader buff = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//check if it's a better or a player; for now, just assume all are players
			try {
				String playerOrBetter = buff.readLine();
				if (playerOrBetter.equals("player")) {
					clientPlayerSockets.add(clientSocket);
					clientPlayerSocketInputStreams.add(buff);
				} else {
					clientBetterSockets.add(clientSocket);
					clientBetterSocketInputStreams.add(buff);
				}
				System.out.println(playerOrBetter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}