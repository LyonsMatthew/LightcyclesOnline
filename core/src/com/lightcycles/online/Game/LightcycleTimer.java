package com.lightcycles.online.Game;

public class LightcycleTimer
{
	private float seconds;

	private long starting_moment;

	public LightcycleTimer(float seconds)
	{
		this.seconds = seconds;
	}

	public void start()
	{
		this.starting_moment = System.currentTimeMillis();
	}

	public boolean tick()
	{
		float grand_delta = (System.currentTimeMillis() - starting_moment)/1000f;
		if (grand_delta > seconds)
		{
			reset();
			return true;
		}
		return false;
	}

	public float time_left()
	{
		return (seconds - (System.currentTimeMillis() - starting_moment)/1000f);
	}

	public void reset()
	{
		this.starting_moment = System.currentTimeMillis();
	}
}
