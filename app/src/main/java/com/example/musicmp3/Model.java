package com.example.musicmp3;

public class Model {
    String Title;
    String Ten;
    int icon;

    public Model(String title, String ten, int icon) {
        Title = title;
        Ten = ten;
        this.icon = icon;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getTen() {
        return this.Ten;
    }

    public int getIcon() {
        return this.icon;
    }
}
