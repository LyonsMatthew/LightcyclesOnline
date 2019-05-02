package com.lightcycles.online.Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.lightcycles.online.Client.InputRunnable;
import com.lightcycles.online.Game.LightcycleGameSimulation;
import com.lightcycles.online.Game.LightcyclesGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Server
{
	final LightcyclesGame game;

	ServerSocketHints sshints;
	ServerSocket ssocket;
	ArrayList<Socket> clientPlayerSockets = new ArrayList<>();
	ArrayList<BufferedReader> clientPlayerSocketInputStreams = new ArrayList<>();
	ArrayList<Socket> clientBetterSockets = new ArrayList<>();
	ArrayList<BufferedReader> clientBetterSocketInputStreams = new ArrayList<>();
	int totalPlayers;
	int totalBetters;
	int totalConnections;

	public Server(LightcyclesGame game)
	{
		this.game = game;

		sshints = new ServerSocketHints(); //server socket properties
		sshints.acceptTimeout = 0;
		ssocket = Gdx.net.newServerSocket(Net.Protocol.TCP, 17893, sshints);

		SocketHints shints = new SocketHints(); //connecting client socket properties
		shints.connectTimeout = 0;
		shints.socketTimeout = 0;

		while (true)
		{
			Socket clientSocket = ssocket.accept(null);
			BufferedReader buff = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//check if it's a better or a player; for now, just assume all are players
			try {
				do {
					String playerOrBetter = buff.readLine();
					if (playerOrBetter.equals("player")) {
						clientPlayerSockets.add(clientSocket);
						clientPlayerSocketInputStreams.add(buff);
						totalConnections += 1;
						totalPlayers += 1;
						Thread thread = new Thread(new ServerClientHandlerRunnable(this,
								totalPlayers-1, true));
						thread.start();
						break;
					} else if (playerOrBetter.equals("better")) {
						clientBetterSockets.add(clientSocket);
						clientBetterSocketInputStreams.add(buff);
						totalConnections += 1;
						totalBetters += 1;
						Thread thread = new Thread(new ServerClientHandlerRunnable(this,
								totalBetters-1, false));
						thread.start();
						break;
					} else {
						//ERROR AHHHHHH
					}
				} while (true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void manageClient(int clientIndex, boolean isPlayer)
	{
		if (isPlayer) {
			manageClientPlayer(clientIndex);
		} else {
			manageClientBetter(clientIndex);
		}
	}

	public void manageClientPlayer(int clientIndex)
	{
		BufferedReader buff = new BufferedReader(new InputStreamReader(clientPlayerSockets.get(clientIndex).getInputStream()));
		try {
			clientPlayerSockets.get(clientIndex).getOutputStream().
					write((String.valueOf(totalPlayers) + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Welcome, player " + clientIndex + "!");

		while(true)
		{
			String input = "o";
			try {
				input = buff.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(input);
			//Gdx.app.postRunnable(new InputRunnable(input.charAt(0)));
		}
	}

	public void manageClientBetter(int clientIndex)
	{
		System.out.println("Welcome, better " + clientIndex + "!");
	}
}