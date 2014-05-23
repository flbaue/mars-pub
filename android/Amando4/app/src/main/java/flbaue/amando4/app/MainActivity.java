/*
 * Copyright (c) 2014.
 * Florian Bauer, flbaue@posteo.de
 */

package flbaue.amando4.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    public static final String HELP_PAGE = "HELP_PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.main_btn_manage_geo_contacts));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.main_btn_manage_geo_contacts) {
            getMenuInflater().inflate(R.menu.main_context_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.context_action_help) {
            Intent intent = new Intent(this, Help.class);
            intent.putExtra(HELP_PAGE, R.raw.help_geo_contacts);
            startActivity(intent);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.action_help:
                startActivity(new Intent(this, Help.class));
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnClickSendPosition(View view) {
        startActivity(new Intent(this, SendPosition.class));
    }

    public void btnClickManageGeoContacts(View view) {
        startActivity(new Intent(this, ManageGeoContacts.class));
    }

    public void btnClickShowMap(View view) {
        startActivity(new Intent(this, ShowMap.class));
    }

    public void btnClickStartSimulation(View view) {
        startActivity(new Intent(this, StartSimulation.class));
    }
}
