package com.example.asus.geet;

import android.provider.DocumentsContract;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by asus on 20-01-2018.
 */

public class FetchVideos {
    boolean fetchstatus1=false;
    ArrayList<File> videos=new ArrayList<File>();
    FetchVideos(){

    }
    public ArrayList<File> findvideos(File root){
        ArrayList<File> al1=new ArrayList<File>();
        File[] files1=root.listFiles();
        for (File singlefile1: files1){
            if (singlefile1.isDirectory() && !singlefile1.isHidden()){
                al1.addAll(findvideos(singlefile1));
            }
            else {
                if (singlefile1.getName().endsWith(".mp4") || singlefile1.getName().endsWith(".3gp")){
                    al1.add(singlefile1);
                }
            }
        }
        fetchstatus1=true;
        videos=al1;
        return al1;
    }
    public boolean findvideostatus(){
        return fetchstatus1;
    }
    public ArrayList<File> getvideolist(){
        return videos;
    }
}
