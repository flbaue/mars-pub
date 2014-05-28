/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package de.androidbuch.rechner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Florian Bauer on 11.05.14. flbaue@posteo.de
 */
public class ErgebnisActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnis_anzeigen);

        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            final Ergebnis ergebnis = new Ergebnis();

            ergebnis.betrag = extras.getFloat(FormularActivity.BETRAG_KEY);
            ergebnis.isNetto = extras.getBoolean(FormularActivity.BETRAG_ART);
            ergebnis.ustProzent = extras.getInt(FormularActivity.UST_PROZENT);

            zeigeErgebnis(ergebnis);
        }
    }

    private void zeigeErgebnis(Ergebnis ergebnis) {
        setTitle(R.string.txt_ergebnis);

        ergebnis.berecheErgebnis();

        final TextView txtNettobetrag = (TextView) findViewById(R.id.tv_nettobetrag);
        txtNettobetrag.setText(String.valueOf(ergebnis.betragNetto));

        final TextView txtUmsatzsteuer = (TextView) findViewById(R.id.tv_umsatzsteuer);
        txtUmsatzsteuer.setText(String.valueOf(ergebnis.betragUst));

        final TextView txtBruttobetrag = (TextView) findViewById(R.id.tv_bruttobetrag);
        txtBruttobetrag.setText(String.valueOf(ergebnis.betragBrutto));

    }
}