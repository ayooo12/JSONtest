package com.example.jsontest;

import com.google.gson.annotations.SerializedName;

public class Post {/*
    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body){
        this.body=body;
    }


*/


    @SerializedName("리날룰")
    private String 리날룰;
  //  @SerializedName("트리클로산")
  //  private String type;

    public String get리날룰() {
        return 리날룰;
    }
    public void set리날룰(String 리날룰){
        this.리날룰 = 리날룰;
    }
  /*  public String getType() {
        return type;
    }
    public void setType(String type){
        this.type =type;
    }*/





}
