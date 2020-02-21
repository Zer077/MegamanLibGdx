package com.mygdx.game.Mapa;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;

class Puntos extends Objeto {

    public boolean cogido = false;

    public Puntos(World world, TiledMap mapa, Rectangle rect, PantallaPrincipal p) {
        super(world, mapa, rect, p);
        fixture.setUserData(this);
        fixture.setSensor(true);

    }

    @Override
    public void Collision() {
        if (!cogido) {

            pantalla.hud.addScore(100);

            getCell().setTile(null);
            cogido = true;

        }


    }
}
