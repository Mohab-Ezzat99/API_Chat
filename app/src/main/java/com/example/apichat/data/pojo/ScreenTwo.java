package com.example.apichat.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScreenTwo {

    @SerializedName("Name")
    private String Name;

    @SerializedName("Pic")
    private String Pic;

    @SerializedName("Messages")
    private ArrayList<MyMassage> Messages;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public ArrayList<MyMassage> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<MyMassage> messages) {
        Messages = messages;
    }
}
