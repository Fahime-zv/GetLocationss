package com.icecoldapps.getlocation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_Start,bt_End,bt_Show;
    GPSTracker gps;
    Typeface font;
    Datatbase db;

    public void Define(){
        bt_Start=(Button)findViewById(R.id.bt_Start);
        bt_End=(Button)findViewById(R.id.bt_End);
        bt_Show=(Button)findViewById(R.id.bt_Show);

        font=Typeface.createFromAsset(getAssets(),"Yekan.TTF");
        bt_Start.setTypeface(font);
        bt_End.setTypeface(font);
        bt_Show.setTypeface(font);
        db=new Datatbase(MainActivity.this);
        gps = new GPSTracker(MainActivity.this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        Define();
        db.copyDatabase();
            }

    public void StartOnClick (View v){
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        double accuracy=gps.getAccuracy();
        if(gps.canGetLocation()) {

            db.copyDatabase();
            db.open();
            db.Insert(latitude, longitude, accuracy, Datejalali.getCurrentShamsidate());
            db.close();
            Toast.makeText(getApplicationContext(), "Loc Add to db", Toast.LENGTH_LONG).show();
        }
        else {
            gps.showSettingsAlert();
        }
    }

    public void EndOnClick (View v) {


    }
    public void ShowOnClick (View v) {

      startActivity(new Intent(MainActivity.this,ShowActivity.class));

    }

}
