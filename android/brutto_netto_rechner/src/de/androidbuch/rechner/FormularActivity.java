package de.androidbuch.rechner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FormularActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public static final String BETRAG_KEY = "betrag";
    public static final String BETRAG_ART = "art";
    public static final String UST_PROZENT = "ust";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formular_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_beenden:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickBerechnen(View view) {
        //Betrag:
        final EditText txtBetrag = (EditText) findViewById(R.id.edt_betrag);
        final float betrag;
        try {
            betrag = Float.parseFloat(txtBetrag.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        //Art des betrags(Brutto, Netto)
        boolean isNetto = true;
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg_art);
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb_art_netto:
                isNetto = true;
                break;
            case R.id.rb_art_brutto:
                isNetto = false;
                break;
        }

        //Prozentwert Umsatzsteuer
        final Spinner spinner = (Spinner) findViewById(R.id.sp_umsatzsteuer);
        final int pos = spinner.getSelectedItemPosition();
        final int[] prozentwerte = getResources().getIntArray(R.array.ust_werte);
        final int prozentwert = prozentwerte[pos];

        final Intent intent = new Intent(this, ErgebnisActivity.class);
        intent.putExtra(BETRAG_KEY, betrag);
        intent.putExtra(BETRAG_ART, isNetto);
        intent.putExtra(UST_PROZENT, prozentwert);

        startActivity(intent);
    }
}
