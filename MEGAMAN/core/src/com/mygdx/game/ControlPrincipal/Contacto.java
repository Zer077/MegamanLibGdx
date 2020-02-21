package com.mygdx.game.ControlPrincipal;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Mapa.Terreno;

public class Contacto implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        //identificar colision entre dos objetos
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "pies" || fixB.getUserData() == "pies") {
            Fixture head = fixA.getUserData() == "pies" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && Terreno.class.isAssignableFrom(object.getUserData().getClass())) {
                System.out.println("en suelo");
            }
        }
        if (fixA.getUserData() == "abajoDerecha" || fixB.getUserData() == "abajoDerecha") {
            Fixture head = fixA.getUserData() == "abajoDerecha" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && Terreno.class.isAssignableFrom(object.getUserData().getClass())) {
                System.out.println("subiendo");
            }


        }
        if (fixA.getUserData() == "abajoIzquierda" || fixB.getUserData() == "abajoIzquierda") {
            Fixture head = fixA.getUserData() == "abajoIzquierda" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && Terreno.class.isAssignableFrom(object.getUserData().getClass())) {
                System.out.println("subiendo");
            }


        }
    }

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
