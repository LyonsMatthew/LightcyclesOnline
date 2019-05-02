package com.lightcycles.online.Server;

import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.Server.Server;

import java.util.Map;

public class ServerRunnable implements Runnable
{
	LightcyclesGame game;
	Map<Integer, Character> input_map;

	public ServerRunnable(LightcyclesGame game, Map<Integer, Character> input_map)
	{
		this.game = game;
		this.input_map = input_map;
	}

	@Override
	public void run()
	{
		Server server = new Server(game, input_map);
	}
}
