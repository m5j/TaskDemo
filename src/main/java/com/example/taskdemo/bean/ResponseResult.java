package com.example.taskdemo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {

    private String status;

    private String msg;

    private Object data;
}
