package com.example.asus.geet;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView lv1;
    String[] items1;
    FetchVideos fv;
    ArrayList<File> myvideos;
    ProgressDialog dialog;
   // private DrawerLayout mDrawerlayout;
   // private ActionBarDrawerToggle mToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv1=(ListView)findViewById(R.id.listView1);
        fv=new FetchVideos();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait, Fetching Songs...");
        dialog.setCancelable(true);
        dialog.show();
       // mDrawerlayout=(DrawerLayout)findViewById(R.id.drawer);
       // mToggle=new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
       // mDrawerlayout.addDrawerListener(mToggle);
       // mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        while(fv.findvideostatus()!=true){
            myvideos=fv.findvideos(Environment.getExternalStorageDirectory());
        }
        if(myvideos!=null){
            dialog.dismiss();
        }

        myvideos = fv.getvideolist();

        items1= new String[myvideos.size()];
        for (int i=0;i<myvideos.size();i++){
            items1[i]=myvideos.get(i).getName().toString().replace(".mp4","").replace(".3gp","");
        }

        ArrayAdapter<String> adp= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items1);
        lv1.setAdapter(adp);
       lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),videoPlayer.class);
                intent.putExtra("pos",i);
                startActivity(intent);
                finish();
            }
        });
    }
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public void onBackPressed(){
        Intent i=new Intent(MainActivity.this,splash.class);
        startActivity(i);
        super.onBackPressed();
        finish();
    }*/
}


