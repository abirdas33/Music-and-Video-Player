package com.example.asus.geet;
/*
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.lvplaylist);
        final ArrayList<File> mySongs=findSongs(Environment.getExternalStorageDirectory());
        items=new String[mySongs.size()];
        for (int i=0;i<mySongs.size();i++){
            //toast(mySongs.get(i).getName().toString());
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Player.class).putExtra("pos",position).putExtra("songlist",mySongs));

            }
        });
    }
    public ArrayList<File> findSongs(File root){
        ArrayList<File> al=new ArrayList<File>();
        File[] files=root.listFiles();
        for (File singleFile: files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                al.addAll(findSongs(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".mp3")){
                    al.add(singleFile);
                }
            }
        }
        return al;
    }
    public void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
}*/

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    String[] items;
    FetchSongs fs;
    ArrayList<File> mySongs;
    ProgressDialog dialog;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
        }
        fs=new FetchSongs();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait, Fetching Songs...");
        dialog.setCancelable(true);
        dialog.show();
        mDrawerlayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        while(fs.getfetchstatus()!=true){
            mySongs=fs.findSongs(Environment.getExternalStorageDirectory());
        }
        if(mySongs!=null){
            dialog.dismiss();
        }

        mySongs = fs.getsonglist();

        items= new String[mySongs.size()];
        for (int i=0;i<mySongs.size();i++){
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","");
        }

        ArrayAdapter<String> adp= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),Player.class);
                intent.putExtra("pos",i);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(MainActivity.this,CollapsingToolbar.class);
        startActivity(i);
        super.onBackPressed();
        finish();
    }
}
