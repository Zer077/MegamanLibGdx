package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Mapa.Disidente;
import com.mygdx.game.Mapa.Objeto;

public class WorldContactListener implements ContactListener {
    PantallaPrincipal p;

    public WorldContactListener(PantallaPrincipal pantalla) {
        p = pantalla;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if (fixA.getUserData() == "cuerpo" || fixB.getUserData() == "cuerpo") {
            //Al contactar con el suelo o puntos podrá volver a saltar
            p.zero.saltado=0;
            Fixture cuerpo = fixA.getUserData() == "cuerpo" ? fixA : fixB;
            Fixture object = cuerpo == fixA ? fixB : fixA;
            Gdx.app.log("CHOCA", "por segunda vez");



            if (object.getUserData() != null && Objeto.class.isAssignableFrom(object.getUserData().getClass())) {
                ((Objeto) object.getUserData()).Collision();
            }
            if (object.getUserData() != null && object.getUserData()=="muerte") {
                Gdx.app.log("entro en el if de muerte zero", "por segunda vez");
              //  ((Disidente) object.getUserData()).Collision();
                if (p.disidente.fixture==object){
                    p.disidente.Collision();


                }
            }
        }

        if ((fixA.getUserData() == "ataquederecha" || fixB.getUserData() == "ataquederecha") && p.zero.derecha && p.zero.hit) {
            Fixture cuerpo = fixA.getUserData() == "ataquederecha" ? fixA : fixB;
            Fixture object = cuerpo == fixA ? fixB : fixA;

            if (object.getUserData() != null && object.getUserData()=="muerte") {

                if (p.disidente.fixture==object){
                    p.disidente.Muerte();


                }
            }
        }

        if ((fixA.getUserData() == "ataqueizquierda" || fixB.getUserData() == "ataqueizquierda") && !p.zero.derecha && p.zero.hit) {
            Fixture cuerpo = fixA.getUserData() == "ataqueizquierda" ? fixA : fixB;
            Fixture object = cuerpo == fixA ? fixB : fixA;

            if (object.getUserData() != null &&   object.getUserData()=="muerte") {

                if (p.disidente.fixture==object){
                    p.disidente.Muerte();


                }
        }

    }}

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
