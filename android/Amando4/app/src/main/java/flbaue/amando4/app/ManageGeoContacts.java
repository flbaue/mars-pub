/*
 * Copyright (c) 2014.
 * Florian Bauer, flbaue@posteo.de
 */

package flbaue.amando4.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Arrays;


public class ManageGeoContacts extends ActionBarActivity implements GeoContactsFragment.OnFragmentInteractionListener {

    private AdapterView.OnItemSelectedListener mSpinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            GeoContactsFragment fragment = (GeoContactsFragment) getSupportFragmentManager().findFragmentById(R.id.geo_contacts_fragment);
            switch (position) {
                case 0:
                    fragment.initializeNames();
                    fragment.mAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    Arrays.sort(fragment.NAMES);
                    fragment.mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_geo_contacts);
        ((Spinner) findViewById(R.id.geo_contacts_sp_order)).setOnItemSelectedListener(mSpinnerItemSelectedListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_geo_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        System.out.println(id);
    }
}
