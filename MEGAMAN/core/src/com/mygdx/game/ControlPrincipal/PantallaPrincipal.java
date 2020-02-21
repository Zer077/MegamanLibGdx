package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Mapa.CreadorDeMapa;
import com.mygdx.game.Mapa.Disidente;
import com.mygdx.game.Mapa.Zero;
import com.mygdx.game.Megaman;


public class PantallaPrincipal implements Screen {
    int PARARCORRER = 100;

    private Megaman game;

    private OrthographicCamera cam;
    private Viewport gamePort;

    public Hud hud;
    public TmxMapLoader maploader;
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public World world;
    public Box2DDebugRenderer Box2d;

    public Zero zero;
    public Disidente disidente;
    private TextureAtlas atlas;
    private TextureAtlas atlasEnemigo;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("attack.wav"));

    public PantallaPrincipal(Megaman megaman) {
        setGame(megaman);
        hud = new Hud(getGame().batch);

        cam = new OrthographicCamera();
        gamePort = new FitViewport(getGame().VENTANA_WIDHT / Megaman.PPM, getGame().VENTANA_HEIGHT / Megaman.PPM, cam);
        cam.position.set(200, gamePort.getScreenHeight(), 0);

        maploader = new TmxMapLoader();
        map = maploader.load("map2.tmx");

        renderer = new OrthogonalTiledMapRenderer(map, 1 / Megaman.PPM);


        Box2d = new Box2DDebugRenderer();


        world = new World(new Vector2(0, -120 / Megaman.PPM), true);
        world.setContactListener(new Contacto());
        WorldContactListener w=new WorldContactListener(this);
        world.setContactListener(w);

        new CreadorDeMapa(world, map, this);
        cam.position.y = Megaman.VENTANA_HEIGHT / 2;

        setAtlas(new TextureAtlas("Zero.pack"));
        setAtlasEnemigo(new TextureAtlas("Disidente.pack"));

        zero = new Zero(world, this);
        disidente = new Disidente(world, this);

        Music music = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));
        music.play();

    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput();
        world.step(1 / 60f, 6, 2);
        if (zero.body.getPosition().x > 200 && zero.body.getPosition().x < 1380) {

            cam.position.x = zero.body.getPosition().x;
        }

        zero.update(dt);
        disidente.update(dt);
        cam.update();
        renderer.setView(cam);


    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) ) {
if (zero.saltado<=1){

    zero.body.applyLinearImpulse(0, 180, zero.body.getWorldCenter().x, zero.body.getWorldCenter().y, true);

zero.saltado++;
}
            // zero.body.applyLinearImpulse(new Vector2(0, 180), zero.body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && zero.body.getLinearVelocity().x >= -PARARCORRER && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {

            // zero.body.applyLinearImpulse(new Vector2(-120, 0), zero.body.getWorldCenter(), true);
            zero.body.applyLinearImpulse(-120, 0, zero.body.getWorldCenter().x, zero.body.getWorldCenter().y, true);

        }


        if (Gdx.input.isKeyPressed(Input.Keys.D) && zero.body.getLinearVelocity().x <= PARARCORRER && !Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {

            // zero.body.applyLinearImpulse(new Vector2(120, 0), zero.body.getWorldCenter(), true);
            zero.body.applyLinearImpulse(120, 0, zero.body.getWorldCenter().x, zero.body.getWorldCenter().y, true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && zero.body.getLinearVelocity().x >= -PARARCORRER && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {

            zero.body.applyLinearImpulse(-360, 0, zero.body.getWorldCenter().x, zero.body.getWorldCenter().y, true);

        }


        if (Gdx.input.isKeyPressed(Input.Keys.D) && zero.body.getLinearVelocity().x <= PARARCORRER && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {

            zero.body.applyLinearImpulse(360, 0, zero.body.getWorldCenter().x, zero.body.getWorldCenter().y, true);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            zero.hit = true;
            sound.play();


        }

    }


    @Override
    public void render(float delta) {
        update(delta);


        renderer.render();
        Box2d.render(world, cam.combined);
        getGame().batch.setProjectionMatrix(cam.combined);

        getGame().batch.begin();
        zero.draw(getGame().batch);
        disidente.draw(getGame().batch);

        getGame().batch.end();
        hud.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public TextureAtlas getAtlasEnemigo() {
        return atlasEnemigo;
    }

    public void setAtlasEnemigo(TextureAtlas atlasEnemigo) {
        this.atlasEnemigo = atlasEnemigo;
    }

    public Megaman getGame() {
        return game;
    }

    public void setGame(Megaman game) {
        this.game = game;
    }
}
