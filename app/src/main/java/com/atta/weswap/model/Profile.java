package com.atta.weswap.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("error")
    private Boolean error;


    @SerializedName("user")
    private User user;

    @SerializedName("error_msg")
    private String errorMsg;

    public Profile() {
    }

    public User getUser() {
        return user;
    }

    public Boolean getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
