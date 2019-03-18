package com.example.musicmp3;

public class Song {
    private String TenBH;
    private int File;

    public Song( String tenBH, int file) {
        TenBH = tenBH;
        File = file;
    }


    public String getTenBH() {
        return TenBH;
    }

    public void setTenBH(String tenBH) {
        TenBH = tenBH;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }


}
