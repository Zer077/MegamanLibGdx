package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

    Music music;
    public MainMenuScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(Megaman.VENTANA_WIDHT, Megaman.VENTANA_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Megaman) game).batch);


//        Texture  texture = new Texture(Gdx.files.internal("menu.png"));
//        TextureRegion region = new TextureRegion(texture, 0, 0, 400, 308);
//        com.badlogic.gdx.scenes.scene2d.ui.Image actor = new com.badlogic.gdx.scenes.scene2d.ui.Image(region);
//
//
//
//        stage.addActor(actor);

          music = Gdx.audio.newMusic(Gdx.files.internal("title.mp3"));

    }


    @Override
    public void show() {

    }

    int i = 1;
    double time = 0;
int tiempoespera=30;
    @Override
    public void render(float delta) {

        try {
            Thread.sleep(tiempoespera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i==45){
            music.play();


        }
        if (i==190){
            tiempoespera=15;


        }
        Texture texture = new Texture(Gdx.files.internal("menu (" + i + ").png"));
        TextureRegion region = new TextureRegion(texture, 0, 0, 400, 308);
        com.badlogic.gdx.scenes.scene2d.ui.Image actor = new com.badlogic.gdx.scenes.scene2d.ui.Image(region);


        stage.addActor(actor);

        if (Gdx.input.justTouched()) {
            game.setScreen(new PantallaPrincipal((Megaman) game));
            dispose();
        }

        stage.draw();
        i++;
        if (i == 246 && time >= 10) {
//            if(!music.isPlaying()){
//
//music.play();
//            }
tiempoespera=30;
            i = 1;
            time = 0;
        }

        if (i == 246 && time < 10) {
            i = 222;
            System.out.println(time);
                time +=0.5;

        }


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

