package com.cache.sun.bean;


import lombok.Data;

@Data
public class UserBean {

    private String id;
    private String loginname;
    private String username;
    private Integer userage;
    private String userpassword;
    private Boolean status;
    private int total;
}
