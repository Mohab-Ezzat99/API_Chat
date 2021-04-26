package com.example.apichat.data.pojo;

import com.google.gson.annotations.SerializedName;

public class MyMassage {
    @SerializedName("Message")
    private String Message;

    @SerializedName("Sender")
    private int Sender;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getSender() {
        return Sender;
    }

    public void setSender(int sender) {
        Sender = sender;
    }
}
