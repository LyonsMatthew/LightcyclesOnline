package com.lightcycles.online.Server;

import com.badlogic.gdx.net.Socket;
import com.lightcycles.online.Client.InputPointer;
import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.Server.Server;

import java.util.List;
import java.util.Map;

public class ServerRunnable implements Runnable
{
	LightcyclesGame game;
	Map<Integer, Character> input_map;
	List<InputPointer> inpy;
	List<Socket> sockets;

	public ServerRunnable(LightcyclesGame game, Map<Integer, Character> input_map, List<InputPointer> inpy, List<Socket> sockets)
	{
		this.game = game;
		this.input_map = input_map;
		this.inpy = inpy;
		this.sockets = sockets;
	}

	@Override
	public void run()
	{
		Server server = new Server(game, input_map, inpy, sockets);
	}
}
