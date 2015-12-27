package com.ellcs.point;

import com.badlogic.gdx.InputAdapter;

/**
 * Created by alex on 12/27/15.
 */
public class MyListener extends InputAdapter {

    StackGame game;

    public MyListener(StackGame game) {
        super();
        this.game = game;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        game.handleClick();
        return true;
    }
}
