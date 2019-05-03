package com.lightcycles.online.Server;

public class ServerClientButItsActuallyaServerHandlerRunnable implements Runnable
{
	Server server;

	public ServerClientButItsActuallyaServerHandlerRunnable(Server server)
	{
		this.server = server;
	}

	@Override
	public void run()
	{
		server.manageClientButItsActuallyAServer(0);
	}
}
