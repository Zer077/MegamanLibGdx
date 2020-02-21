package com.mygdx.game.Mapa;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;

public class CreadorDeMapa {
    public CreadorDeMapa(World w, TiledMap mapa, PantallaPrincipal p ) {

        // crear bodies/fixtures terreno rectangulos
        for (MapObject object : mapa.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Terreno(w, mapa, rect, p);
        }
        for (MapObject object : mapa.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Puntos(w,mapa,rect, p);
        }


    }

}
