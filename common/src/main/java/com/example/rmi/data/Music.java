package com.example.rmi.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class Music implements Serializable {
    String id;
    String name;
    String url;
}
