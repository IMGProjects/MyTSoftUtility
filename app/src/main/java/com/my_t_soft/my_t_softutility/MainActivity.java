package com.my_t_soft.my_t_softutility;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.TagType);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //String sData is the actual text that gets sent to My-T-Soft keyboard for typing
                String sData = "Testing";

                sendDataToMyTSoft(sData);

                Toast.makeText(MainActivity.this,
                        "Text sent to My-T-Soft! Data = "+sData, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void sendDataToMyTSoft(String sData){

        //this sequence is the code that sends the data
        //String sData is the actual text that gets sent to My-T-Soft keyboard for typing

        //This is on receive side
        //filter.addAction("com.mytsoft.SCAN");
        //filter.addCategory("android.intent.category.DEFAULT");

        //MyTSoft Default Profile setup
        Intent i = new Intent(Intent.ACTION_SEND);
        // set the action to perform
        i.setAction("com.mytsoft.SCAN");
        // add additional info for the category
        i.putExtra(Intent.CATEGORY_DEFAULT, "android.intent.category.DEFAULT");
        //place the data (in the same configuration as Zebra's DataWedge utility)
        final String DATA_STRING_TAG = "com.motorolasolutions.emdk.datawedge.data_string";
        i.putExtra(DATA_STRING_TAG,sData);
        // send data / Broadcast the intent to My-T-Soft
        Context ctx = getApplicationContext();
        ctx.sendBroadcast(i);  //this results in My-T-Soft receiving data and "typing"

    }

}
