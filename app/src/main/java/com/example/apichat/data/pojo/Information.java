package com.example.apichat.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Information {
    @SerializedName("Name")
    private String Name;

    @SerializedName("Pic")
    private String Pic;

    @SerializedName("Message")
    private String Message;

    @SerializedName("Time")
    private String Time;
    private int New;

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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getNew() {
        return New;
    }

    public void setNew(int aNew) {
        New = aNew;
    }
}
