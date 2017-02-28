package com.example.marinex.gittrendapp;

/**
 * Created by marinex on 27/2/17.
 */

public class dataModel {
  String repo_name;
     String language;
   int stars;
   int forks;
    String svn_url;
public String getRepo_name(){
    return  repo_name;
}
    public String getLanguage(){
        return language;
    }
    public  int getStars(){
        return stars;
    }
    public int getForks(){

        return forks;
    }
    public String getSvn_url(){
        return svn_url;
    }
}
