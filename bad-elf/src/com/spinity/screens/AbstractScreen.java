package com.spinity.screens;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

/**
 * @author Mats Svensson
 */
public abstract class AbstractScreen extends InputAdapter implements Screen {

    protected MainScreen game;

    public AbstractScreen(MainScreen game) {
        this.game = game;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
