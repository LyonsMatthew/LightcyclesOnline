package com.lightcycles.online.Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Client.InputRunnable;
import com.lightcycles.online.Game.LightcycleGameSimulation;
import com.lightcycles.online.Game.LightcycleTimer;
import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	Map<Integer, Character> input_map;
	List<InputPointer> inpy;
	LightcycleTimer timer;

	public Server(LightcyclesGame game, Map<Integer, Character> input_map, List<InputPointer> inpy)
	{
		this.game = game;
		this.input_map = input_map;
		this.inpy = inpy;

		this.timer = new LightcycleTimer(Settings.GAME_START_DElAY);

		Thread fred_the_thread = new Thread(new ServerClientButItsActuallyaServerHandlerRunnable(this));
		fred_the_thread.start();

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
						input_map.put(totalPlayers-1, 'o');
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
		timer.start();
		inpy.get(1).input_char = (char)totalPlayers;
		while(true)
		{
			String input = "o";
			try {
				input = buff.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			input_map.put(clientIndex, input.charAt(0));
			inpy.get(0).input_char = 'y';
		}
	}

	public void manageClientBetter(int clientIndex)
	{
		System.out.println("Welcome, better " + clientIndex + "!");
	}

	public void manageClientButItsActuallyAServer(int notAClientIndex)
	{
//		while(true)
//		{
//			if (timer.time_left() < 0 && totalPlayers > 0) inpy.get(0).input_char = 'y';
//		}
		int last_time = -10;
		while (inpy.get(0).input_char == 'n')
		{
			System.out.print("");
			if ((int)inpy.get(1).input_char != totalPlayers) continue;
			if (totalPlayers > 0)
			{
				if (last_time != (int)timer.time_left())
				{
					last_time = (int)timer.time_left();
					if (timer.time_left() > 0)
					{
						System.out.println("Game starting in " + (int)timer.time_left() + " second" +
								((timer.time_left() == 1) ? "" : "s") + "!");
					}
					else
					{
						System.out.println("LET'S START IT UP BOIIIIIIIZZZ");
						System.out.println("WE PARTYIN UP IN THIS");
						System.out.println("L I G H T");
						System.out.println("C Y C L E SZZZZZZZZZZZZZZZZ");
						inpy.get(0).input_char = 'y';
					}
				}
			}
		}
	}
}