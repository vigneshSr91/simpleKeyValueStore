package com.spring.keyvaluestore.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class KeyValue {
    private String Id;
    private String Value;
    private Timestamp expires_at;

    public KeyValue(String Id, String Value, Timestamp expires_at){
        this.Id = Id;
        this.Value = Value;
        this.expires_at = expires_at;
    }

    public KeyValue(String Id){
        this.Id = Id;
    }

    public KeyValue(){

    }

    @Override
    public String toString() {
        return "KeyValue [" + Id + ", " + Value + ", $" + expires_at + "]";
    }
}
