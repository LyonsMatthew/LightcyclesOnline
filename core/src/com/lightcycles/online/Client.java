package com.lightcycles.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Client
{
	SocketHints shints = new SocketHints();
	Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 17892, shints);
}