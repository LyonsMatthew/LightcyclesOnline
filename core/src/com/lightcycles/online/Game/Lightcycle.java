package com.lightcycles.online.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lightcycles.online.Settings;

public class Lightcycle extends Actor
{
	private Texture texture;
	int player_num;

	int grid_x;
	int grid_y;

	int next_action; //0 = right, 1 = down, 2 = left, 3 = up
	int direction; //same as above

	boolean is_dead = false;

	Color color;

	public Lightcycle(int player_num)
	{
		this.texture = new Texture(Gdx.files.internal("lightcycle.png"));
		this.player_num = player_num;

		this.setName("cycle " + player_num);
		this.setWidth(Settings.SCREEN_WIDTH / Settings.GRID_WIDTH);
		this.setHeight(Settings.SCREEN_HEIGHT / Settings.GRID_HEIGHT);

		this.grid_x = 10;
		this.grid_y = 7;

		this.set_position();

		this.color = new Color(1.0f, 0.0f, 0.0f, 1);
	}

	public int get_player_num()
	{
		return this.player_num;
	}

	public void set_position()
	{
		this.setPosition(this.grid_x * Settings.SCREEN_WIDTH / Settings.GRID_WIDTH,
				this.grid_y * Settings.SCREEN_HEIGHT / Settings.GRID_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		if (!is_dead)
		{
			Color color_swap = new Color();
			color_swap.set(batch.getColor());

			batch.setColor(this.color);

			float rotation = direction * -90;

			batch.draw(texture, this.getX(), this.getY(), this.getWidth()*0.5f, this.getHeight()*0.5f,
					this.getWidth(), this.getHeight(), 1.0f, 1.0f, rotation, 0, 0,
					texture.getWidth(), texture.getHeight(), false, false);

			batch.setColor(color_swap);
		}
	}

	public void set_action(int action)
	{
		this.next_action = action;
	}

	public int get_next_action()
	{
		return this.next_action;
	}

	public void move()
	{
		System.out.println("MOVE " + this.next_action);
		this.direction = this.next_action;
		switch(this.next_action)
		{
			case 0:
				this.grid_x += 1;
				break;
			case 1:
				this.grid_y -= 1;
				break;
			case 2:
				this.grid_x -= 1;
				break;
			case 3:
				this.grid_y += 1;
				break;
			default:
				System.out.println("Error in Lightcycle.move, action = " + this.next_action);
		}

		this.set_position();
	}

	public int get_grid_x()
	{
		return this.grid_x;
	}

	public int get_grid_y()
	{
		return this.grid_y;
	}

	public boolean is_dead()
	{
		return this.is_dead;
	}

	public Color get_color()
	{
		return this.color;
	}

	public void die()
	{
		this.is_dead = true;
	}
}
