package com.mygdx.game.Mapa;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ControlPrincipal.GameOverScreen;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;
import com.mygdx.game.ControlPrincipal.WinScreen;
import com.mygdx.game.Megaman;

public class Disidente extends Sprite {
    public enum State {RUN};

    public World world;
    public Body body;
    private TextureRegion disidenterun;
    private Animation run;
    public Vector2 velocity = new Vector2(-60, -20);
    public int actualX;
    public int previousX;
    public Fixture fixture;
    PantallaPrincipal pantalla;
    private float stateTimer;


    private boolean derecha = true;
    Array<TextureRegion> frames = new Array<>();
    int stateTime;

    public  State currentState;
    public  State previousState;

    public Disidente(World w, PantallaPrincipal p) {
        super(p.getAtlasEnemigo().findRegion("disidentes"));
        world = w;
        pantalla = p;

        currentState =  State.RUN;
        previousState =  State.RUN;


        for (int i = 2; i < 4; i++) {
            if (i == 1)
                frames.add(new TextureRegion(getTexture(), 0, 0, 45, 45));
            frames.add(new TextureRegion(getTexture(), (i * 45), 0, 45, 45));
        }
        run = new Animation(0.4F, frames);


        defineBody();

        disidenterun = (TextureRegion) run.getKeyFrame(stateTime, true);
        setBounds(0, 0, 45 / Megaman.PPM, 45 / Megaman.PPM);

        actualX = (int) body.getPosition().x;
        comprobacion();
    }




    public  State getState() {


            return  State.RUN;


    }


    public TextureRegion getFrame(float dt) {

        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            default:
            case RUN:
                region = (TextureRegion) run.getKeyFrame(stateTimer, true);
                break;



        }

        if ((body.getLinearVelocity().x < 0 || !derecha) && region.isFlipX()) {
            region.flip(true, false);
            derecha = false;
        } else if ((body.getLinearVelocity().x > 0 || derecha) && !region.isFlipX()) {
            region.flip(true, false);
            derecha = true;
        }
        stateTimer = (currentState == previousState) ? (stateTimer + dt) : 0;
        previousState = currentState;
        return region;


    }




    public void update(float dt) {

        setRegion(getFrame(dt));
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 4);
        body.setLinearVelocity(velocity);
        previousX = actualX;
        actualX = (int) body.getPosition().x;


    }

    private void defineBody() {

        BodyDef bodydef = new BodyDef();
        bodydef.position.set(1000 / Megaman.PPM, 50 / Megaman.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodydef);
        FixtureDef fixdef = new FixtureDef();


        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Megaman.PPM);
        fixdef.shape = shape;
        body.createFixture(fixdef);


        FixtureDef fix = new FixtureDef();
        CircleShape s = new CircleShape();
        s.setRadius(15 / Megaman.PPM);
        fix.shape = s;
        fix.isSensor = true;

        fixture = body.createFixture(fix);
        fixture.setUserData("muerte");

    }

    public void comprobacion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (previousX == actualX) {
                        if (velocity.x == -60) {
                            velocity = new Vector2(60, -20);
                        } else {
                            velocity = new Vector2(-60, -20);


                        }
                    } else {
                        System.out.println(".");
                    }
                }
            }
        }).start();


    }

    public void Collision() {

        pantalla.getGame().setScreen(new GameOverScreen(pantalla.getGame()));

    }

    public void Muerte() {

        pantalla.getGame().setScreen(new WinScreen(pantalla.getGame()));


    }

}

