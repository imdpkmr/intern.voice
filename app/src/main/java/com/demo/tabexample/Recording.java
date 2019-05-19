package com.demo.tabexample;

public class Recording {
    String URI,fileName;
    boolean isPlaying=false;

    public Recording(String URI, String fileName, boolean isPlaying){
        this.URI=URI;
        this.fileName=fileName;
        this.isPlaying=isPlaying;
    }

    public String getURI(){
        return URI;
    }

    public String getFileName(){
        return fileName;
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public void setPlaying(boolean playing){
        this.isPlaying=playing;
    }
}
