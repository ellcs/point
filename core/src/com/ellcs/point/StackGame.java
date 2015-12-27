package com.ellcs.point;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class StackGame extends Game {

	float maxCircleSize;
	float lastCircleSize;
	float circleSize = 0.0f;
	Vector2 circleMiddle;

	Vector2 timePosition;
	float time = 0;

	boolean isGameOver = true;
	boolean firstFrameInGame = true;

	SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer renderer;

	
	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		Gdx.input.setInputProcessor(new MyListener(this));
		float halfX  = Gdx.graphics.getWidth()/2;
		float thirdY = Gdx.graphics.getHeight()/3;
		maxCircleSize = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/2);
		lastCircleSize = maxCircleSize;
		circleMiddle = new Vector2(halfX, thirdY*2);
		float timeOffsetX = 50.0f;
		timePosition = new Vector2(halfX - timeOffsetX, 30.0f);
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.renderer = new ShapeRenderer();
		font.setColor(1, 0, 0, 1);
		font.getData().setScale(2);
	}

	private void drawTime() {
		batch.begin();
		font.draw(batch, "" + time, timePosition.x, timePosition.y);
		batch.end();
	}

	private void drawLastCircle() {
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(1.0f, 0.5f, 0.5f, 1.0f));
		renderer.circle(circleMiddle.x, circleMiddle.y, lastCircleSize);
		renderer.end();
	}

	private void drawCircle() {
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(0.5f, 0.5f, 0.5f, 1.0f));
		renderer.circle(circleMiddle.x, circleMiddle.y, circleSize);
		renderer.end();
	}

	public void handleClick() {
		if (!isGameOver) {
			this.lastCircleSize = this.circleSize;
			this.circleSize = 0.0f;
		} else {
			this.isGameOver = false;
			this.circleSize = 0.0f;
			this.lastCircleSize = maxCircleSize;
			this.time = 0.0f;
			this.firstFrameInGame = true;
		}
		Gdx.graphics.requestRendering();
	}

	@Override
	public void render() {
		if (!isGameOver && !firstFrameInGame) {
			time += Gdx.graphics.getDeltaTime();
			circleSize += (Gdx.graphics.getDeltaTime() * 100);
			// check circle size
			if (circleSize >= lastCircleSize) {
				this.isGameOver = true;
			}
			Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
			Gdx.gl.glClearColor(1,1,1,1);
			drawTime();
			// draw circle
			drawLastCircle();
			drawCircle();
			Gdx.graphics.requestRendering();
		}
		if (firstFrameInGame) {
			// since we stop rendering, we need
			// to throw away first frame delta.
			Gdx.graphics.getDeltaTime();
			firstFrameInGame = false;
			Gdx.graphics.requestRendering();
		}
	}
}
