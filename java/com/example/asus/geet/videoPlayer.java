package com.example.asus.geet;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class videoPlayer extends AppCompatActivity {
    VideoView videov;
    FetchVideos fv;
    Button nextv,prevv;
    ArrayList<File> myvideos;
    int position1;
    String path1;
    ImageButton im1;
    MediaController mediaController;
    Uri u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videov=(VideoView)findViewById(R.id.videoView);
        nextv=(Button)findViewById(R.id.nxt);
        prevv=(Button)findViewById(R.id.back);
        im1=(ImageButton)findViewById(R.id.playlistButton1);
        fv=new FetchVideos();
        if(fv.findvideostatus()!=true){
            myvideos=fv.findvideos(Environment.getExternalStorageDirectory());
        }
        else{
            myvideos=fv.getvideolist();
        }
        mediaController=new MediaController(this);
        Intent intent=getIntent();
        position1=intent.getIntExtra("pos",0);
        path1=myvideos.get(position1).toString();
        u= Uri.parse(path1);
        videov.setVideoURI(u);
        videov.setMediaController(mediaController);
        mediaController.setAnchorView(videov);
        videov.start();
        nextv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position1=(position1+1)%myvideos.size();
                path1=myvideos.get(position1).toString();
                u=Uri.parse(path1);
                videov.setVideoURI(u);
                videov.setMediaController(mediaController);
                mediaController.setAnchorView(videov);
                videov.start();
            }
        });
        prevv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position1=(position1-1<0)? myvideos.size()-1:position1-1;
                path1=myvideos.get(position1).toString();
                u=Uri.parse(path1);
                videov.setVideoURI(u);
                videov.setMediaController(mediaController);
                mediaController.setAnchorView(videov);
                videov.start();
            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(videoPlayer.this,Main2Activity.class);
        startActivity(intent);
    }
}
