package com.icecoldapps.getlocation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Fahime on 19/02/2017.
 */


public class ShowActivity extends AppCompatActivity {
    ListView lst_show;
    ArrayList<String> ListArray = new ArrayList<String>();
    public static String[] arryid, arrylat, arrylon,arryacc,arrytime;
    Datatbase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_show);
        lst_show=(ListView)findViewById(R.id.lst_show);
        db=new Datatbase(this);

        Show_info();


    }
    public void Show_info(){
        db.copyDatabase();
        db.open();
        arryid=db.Select("id");
        arrylat=db.Select("lat");
        arrylon=db.Select("long");
        arryacc=db.Select("accuracy");
        arrytime=db.Select("date");

        for (int i = 0; i < arryid.length ; i++) {
            ListArray.add("id: "+arryid[i]+"\n"+"Lat: "+arrylat[i]+"\n"+"Lon: "+arrylon[i]+"\n"+"Acc: "+arryacc[i]+"\n"+"Time: "+arrytime[i]+"\n"+"ــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
        }

        db.close();
        lst_show.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListArray));
    }
}
