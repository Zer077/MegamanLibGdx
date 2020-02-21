package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Megaman;

public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Label gameOverLabel;
    public Label.LabelStyle font;

    public GameOverScreen(Game game){
        this.game = game;
        viewport = new FitViewport(Megaman.VENTANA_WIDHT, Megaman.VENTANA_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Megaman) game).batch);

     font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

          setGameOverLabel(new Label("GAME OVER", font));
        Label playAgainLabel = new Label("Click to Play Again", font);

        table.add(getGameOverLabel()).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PantallaPrincipal((Megaman) game));
            dispose();
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    public Label getGameOverLabel() {
        return gameOverLabel;
    }

    public void setGameOverLabel(Label gameOverLabel) {
        this.gameOverLabel = gameOverLabel;
    }
}
