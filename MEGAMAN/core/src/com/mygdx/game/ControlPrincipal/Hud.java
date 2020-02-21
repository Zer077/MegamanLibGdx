package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Megaman;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    public Integer score = 0;
    Label puntuacion;

    public Hud(SpriteBatch sb) {

        puntuacion = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Label labelpuntuacion = new Label("Puntuacion", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        viewport = new FitViewport(Megaman.VENTANA_WIDHT, Megaman.VENTANA_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(labelpuntuacion).expandX();
        table.row();
        table.add(puntuacion).expandX().padTop(5);

        stage.addActor(table);
    }
    public  void addScore(int value){
        score += value;
        puntuacion.setText(String.format("%03d", score));
    }


}
