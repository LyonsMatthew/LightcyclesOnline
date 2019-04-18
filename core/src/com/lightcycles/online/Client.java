package com.lightcycles.online;

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

	public Client()
	{
		isPlayer = true;

		shints = new SocketHints();
		socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 17892, shints);

		try {
			socket.getOutputStream().write("player".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}