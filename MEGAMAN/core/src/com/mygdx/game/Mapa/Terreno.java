package com.mygdx.game.Mapa;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;

public class Terreno extends Objeto {
    public Terreno(World world, TiledMap mapa, Rectangle bounds, PantallaPrincipal p ) {
        super(world, mapa, bounds, p);
        fixture.setUserData(this);
    }


}
