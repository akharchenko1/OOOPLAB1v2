package com.company;

class Words{
    private int count;
    private String nkey;
    public Words(String key, int value){
        count=value;
        nkey=key;
    }
    String getNkey(){return nkey;}
    Integer getCount(){return count;}
}
