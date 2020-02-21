package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;

public class Megaman extends Game {
    public SpriteBatch batch;
    public static final int VENTANA_WIDHT = 400;
    public static final int VENTANA_HEIGHT = 308;
    public static final float PPM = 1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PantallaPrincipal(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }
}
