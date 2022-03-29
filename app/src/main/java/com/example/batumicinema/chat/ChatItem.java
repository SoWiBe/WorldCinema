package com.example.batumicinema.chat;

public class ChatItem {
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;

    private int viewType;
    private String text;

    public ChatItem(int viewType, String text) {
        this.text = text;
        this.viewType = viewType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private int icon;
    private String text_one, text_two;

    public ChatItem(int viewType, int icon, String text_one, String text_two) {
        this.viewType = viewType;
        this.icon = icon;
        this.text_one = text_one;
        this.text_two = text_two;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }

    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }
}
