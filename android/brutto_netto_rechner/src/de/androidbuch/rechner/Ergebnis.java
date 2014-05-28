/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package de.androidbuch.rechner;

/**
 * Created by Florian Bauer on 11.05.14. flbaue@posteo.de
 */
public class Ergebnis {

    public float betrag;
    public boolean isNetto;
    public int ustProzent;

    public float betragNetto;
    public float betragBrutto;
    public float betragUst;

    public void berecheErgebnis() {
        //Berechne Bruttobetrag aus Nettobetrag
        if(isNetto) {
            betragNetto = betrag;
            betragUst = betrag * ustProzent / 100;
            betragBrutto = betrag + betragUst;
        } else {
            betragBrutto = betrag;
            betragUst = betrag * ustProzent / (100 + ustProzent);
            betragNetto = betrag - betragUst;
        }
    }
}
