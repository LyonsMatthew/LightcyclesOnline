package com.lightcycles.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.ServerSocket;

public class Server
{
	ServerSocketHints sshints = new ServerSocketHints();
	ServerSocket ssocket = Gdx.net.newServerSocket(Net.Protocol.TCP, 17892, sshints);

	public Server()
	{

	}
}