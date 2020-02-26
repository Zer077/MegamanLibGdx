package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
 import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;
import com.mygdx.game.Megaman;

public class MainMenuScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Game game;




    public MainMenuScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(Megaman.VENTANA_WIDHT, Megaman.VENTANA_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Megaman) game).batch);


        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Texture  texture = new Texture(Gdx.files.internal("menu.png"));
        TextureRegion region = new TextureRegion(texture, 0, 0, 400, 308);
        com.badlogic.gdx.scenes.scene2d.ui.Image actor = new com.badlogic.gdx.scenes.scene2d.ui.Image(region);

        table.add(actor);
        table.row();

        stage.addActor(table);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            game.setScreen(new PantallaPrincipal((Megaman) game));
            dispose();
        }

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // game.screenPort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();


    }


}

