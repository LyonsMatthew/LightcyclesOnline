package com.lightcycles.online.Client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.IOException;

public class Client
{
	boolean isPlayer;

	SocketHints shints;
	Socket socket;

	public Client(boolean isPlayer)
	{
		this.isPlayer = isPlayer;

		shints = new SocketHints();
		socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 17893, shints);

		try {
			while(true) {
				socket.getOutputStream().write((isPlayer ? "player\n" : "better\n").getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}