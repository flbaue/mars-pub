/*
 * Copyright (c) 2014.
 * Florian Bauer, flbaue@posteo.de
 */

package flbaue.amando4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.InputStream;
import java.nio.charset.Charset;

import marstools.io.Streams;


public class Help extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final WebView view = (WebView) findViewById(R.id.help_webView);
        //view.getSettings().setJavaScriptEnabled(true);
        initializeWebKit(view, this);
        //view.bringChildToFront();
    }

    private void initializeWebKit(WebView view, Context context) {
        final String mimetype = "text/html";
        final String encoding = "UTF-8";
        String htmldata;

        int contextMenuID = getIntent().getIntExtra(MainActivity.HELP_PAGE, R.raw.help);
        InputStream is = context.getResources().openRawResource(contextMenuID);

        final byte[] bytes = Streams.toByteArrayAndClose(is);
        htmldata = new String(bytes, Charset.forName(encoding));
        view.loadData(htmldata, mimetype, encoding);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
