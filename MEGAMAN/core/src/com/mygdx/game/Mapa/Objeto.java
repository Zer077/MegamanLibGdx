package com.mygdx.game.Mapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;
import com.mygdx.game.Megaman;

public abstract class Objeto {
    protected World world;
    protected TiledMap mapa;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    public PantallaPrincipal pantalla;

    public Objeto(World world, TiledMap mapa, Rectangle bounds, PantallaPrincipal p) {
        this.world = world;
        this.mapa = mapa;
        this.bounds = bounds;
        pantalla=p;
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Megaman.PPM, (bounds.getY() + bounds.getHeight() / 2) / Megaman.PPM);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bounds.getWidth() / 2 / Megaman.PPM, bounds.getHeight() / 2 / Megaman.PPM);
        fdef.shape = shape;

        body = world.createBody(bdef);
        fixture = body.createFixture(fdef);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapa.getLayers().get(4);
        return layer.getCell((int) (body.getPosition().x * Megaman.PPM/16),(int) (body.getPosition().y * Megaman.PPM/16 ));
    }
    public void Collision(){




    }



}
