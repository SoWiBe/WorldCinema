package com.example.batumicinema.Discussions;

//класс обсуждения
public class Discussion {
    private int discIcon;
    private String discTitle;
    private String discChatText;

    public Discussion(int discIcon, String discTitle, String discChatText) {
        this.discIcon = discIcon;
        this.discTitle = discTitle;
        this.discChatText = discChatText;
    }

    public int getDiscIcon() {
        return discIcon;
    }

    public void setDiscIcon(int discIcon) {
        this.discIcon = discIcon;
    }

    public String getDiscTitle() {
        return discTitle;
    }

    public void setDiscTitle(String discTitle) {
        this.discTitle = discTitle;
    }

    public String getDiscChatText() {
        return discChatText;
    }

    public void setDiscChatText(String discChatText) {
        this.discChatText = discChatText;
    }

}
