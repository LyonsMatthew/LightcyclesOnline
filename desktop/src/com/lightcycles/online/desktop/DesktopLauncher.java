package com.lightcycles.online.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lightcycles.online.Game.LightcyclesGame;
import com.lightcycles.online.LightcyclesOnline;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = true;
		new LwjglApplication(new LightcyclesGame(), config);
	}
}
