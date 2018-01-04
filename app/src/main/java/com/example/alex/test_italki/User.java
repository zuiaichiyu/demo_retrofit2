package com.example.alex.test_italki;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 18-1-4.
 */

public class User {

    @SerializedName("nickname")
    private String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "user: " + nickName;
    }
}
