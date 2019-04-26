package com.lightcycles.online.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lightcycles.online.Settings;

public class Path extends Actor
{
	Texture texture;

	int x, y;

	public Path(Texture texture, int x, int y)
	{
		this.texture = texture;
		this.x = x;
		this.y = y;

		this.setWidth(Settings.SCREEN_WIDTH / Settings.GRID_WIDTH);
		this.setHeight(Settings.SCREEN_HEIGHT / Settings.GRID_HEIGHT);
		this.setPosition(x*this.getWidth(), y*this.getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		batch.draw(texture, this.getX(), this.getY(), this.getWidth()*0.5f, this.getHeight()*0.5f,
				this.getWidth(), this.getHeight(), 1.0f, 1.0f, 0f, 0, 0,
				texture.getWidth(), texture.getHeight(), false, false);

	}
}
