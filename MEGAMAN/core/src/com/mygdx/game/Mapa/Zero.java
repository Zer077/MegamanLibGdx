package com.mygdx.game.Mapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ControlPrincipal.PantallaPrincipal;
import com.mygdx.game.Megaman;

public class Zero extends Sprite {
    public boolean hit = false;

    public enum State {ATTACK, RUN, STATIC}


    public State currentState;
    public State previousState;

    public World world;
    public Body body;
    private TextureRegion zerostatic;
    private Animation run;
    private Animation estatico;
    private Animation atack;

    private float stateTimer;
      public boolean derecha;
      public int saltado;

    FixtureDef fdef;
    PolygonShape ataqueizquierda = new PolygonShape();
    PolygonShape ataquederecha = new PolygonShape();

    public Zero(World w, PantallaPrincipal p) {
        super(p.getAtlas().findRegion("staticmovement"));
        world = w;
        currentState = State.STATIC;
        previousState = State.STATIC;

        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i < 9; i++) {
            if (i == 1)
                frames.add(new TextureRegion(getTexture(), 0, 0, 45, 45));
            frames.add(new TextureRegion(getTexture(), -6 + (i * 45), 0, 45, 45));
        }
        run = new Animation(0.1F, frames);
        frames.clear();


        for (int i = 1; i < 2; i++) {
            if (i == 1)
                frames.add(new TextureRegion(getTexture(), 443, 0, 45, 45));
            frames.add(new TextureRegion(getTexture(), (i * 488), 0, 45, 45));

        }
        estatico = new Animation(0.8F, frames);
        frames.clear();


        for (int i = 0; i < 8; i++) {
            if (i == 1)
                frames.add(new TextureRegion(getTexture(), 0, 47, 87, 46));
            frames.add(new TextureRegion(getTexture(), (i * 87), 47, 87, 46));

        }


        atack = new Animation(0.04F, frames);
        frames.clear();

        defineZero();

        zerostatic = new TextureRegion(getTexture(), 443, 5, 45, 45);
        setBounds(0, 0, 45 / Megaman.PPM, 45 / Megaman.PPM);
        setRegion(zerostatic);


        Vector2[] vertice = new Vector2[4];
        Vector2[] vertice2 = new Vector2[4];


        vertice[0] = new Vector2(10, 20).scl(1 / Megaman.PPM);
        vertice[1] = new Vector2(70, 20).scl(1 / Megaman.PPM);
        vertice[2] = new Vector2(70, -10).scl(1 / Megaman.PPM);
        vertice[3] = new Vector2(10, -10).scl(1 / Megaman.PPM);
        ataquederecha.set(vertice);


        vertice2[0] = new Vector2(-10, 20).scl(1 / Megaman.PPM);
        vertice2[1] = new Vector2(-70, 20).scl(1 / Megaman.PPM);
        vertice2[2] = new Vector2(-70, -10).scl(1 / Megaman.PPM);
        vertice2[3] = new Vector2(-10, -10).scl(1 / Megaman.PPM);
        ataqueizquierda.set(vertice2);

        fdef=new FixtureDef();
        fdef.shape = ataquederecha;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("ataquederecha");

        fdef=new FixtureDef();
        fdef.shape = ataqueizquierda;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("ataqueizquierda");


        hilogolpeo();

    }

    public State getState() {
        if (body.getLinearVelocity().x == 0 && !hit) {

            return State.STATIC;


        } else if (!hit) {


            return State.RUN;
        }
        return State.ATTACK;


    }


    public TextureRegion getFrame(float dt) {

        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case RUN:
                region = (TextureRegion) run.getKeyFrame(stateTimer, true);
                break;
            case STATIC:
                region = (TextureRegion) estatico.getKeyFrame(stateTimer, true);
                break;
            case ATTACK:
                region = (TextureRegion) atack.getKeyFrame(stateTimer);
                break;
            default:
                region = (TextureRegion) estatico.getKeyFrame(stateTimer);
                break;


        }

        if ((body.getLinearVelocity().x < 0 || !derecha) && !region.isFlipX()) {
            region.flip(true, false);
            derecha = false;
        } else if ((body.getLinearVelocity().x > 0 || derecha) && region.isFlipX()) {
            region.flip(true, false);
            derecha = true;
        }
        stateTimer = (currentState == previousState) ? (stateTimer + dt) : 0;
        previousState = currentState;
        return region;


    }

    public void update(float dt) {

        setRegion(getFrame(dt));
        switch (currentState) {
            case RUN:
            case STATIC:
                setBounds(0, 0, 45 / Megaman.PPM, 45 / Megaman.PPM);
                setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 4);
                break;
            case ATTACK:

                setBounds(0, 0, 87 / Megaman.PPM, 45 / Megaman.PPM);
                if (derecha) {
                    setPosition((body.getPosition().x - getWidth() / 5), body.getPosition().y - getHeight() / 4);

                } else
                    setPosition((float) (body.getPosition().x - getWidth() / 1.3), body.getPosition().y - getHeight() / 4);
                break;

        }

    }

    private void defineZero() {

        BodyDef bodydef = new BodyDef();
        bodydef.position.set(100 / Megaman.PPM, 50 / Megaman.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodydef);

        FixtureDef fixdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Megaman.PPM);
        fixdef.shape = shape;
        body.createFixture(fixdef);

        FixtureDef fix  = new FixtureDef();
        CircleShape s = new CircleShape();
        s.setRadius(10 / Megaman.PPM);
        fix.shape = s;
        fix.isSensor=true;
        body.createFixture(fix).setUserData("cuerpo");

    }


    public void hilogolpeo() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                currentState = State.STATIC;
                while (true) {
                    if (hit) {
                        currentState = State.ATTACK;


                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        hit = false;
                        currentState = State.STATIC;
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                }


            }
        }).start();


    }


}
