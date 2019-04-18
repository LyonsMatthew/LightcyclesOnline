package com.lightcycles.online.Server;

import com.lightcycles.online.Server.Server;

public class ServerRunnable implements Runnable
{
	@Override
	public void run()
	{
		Server server = new Server();
	}
}
