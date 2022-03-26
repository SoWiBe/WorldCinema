package com.example.batumicinema.ui.collections;

import android.widget.TextView;

import java.io.Serializable;


public class Collection implements Serializable {
    private int col_ItemId;
    private String col_Title;

    public Collection(int col_ItemId, String col_Title) {
        this.col_ItemId = col_ItemId;
        this.col_Title = col_Title;
    }

    public int getCol_ItemId() {
        return col_ItemId;
    }

    public void setCol_ItemId(int col_ItemId) {
        this.col_ItemId = col_ItemId;
    }

    public String getCol_Title() {
        return col_Title;
    }

    public void setCol_Title(String col_Title) {
        this.col_Title = col_Title;
    }
}
