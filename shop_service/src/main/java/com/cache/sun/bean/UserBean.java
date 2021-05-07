package com.cache.sun.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {

    private String id;
    private String loginname;
    private String username;
    private Integer userage;
    private String userpassword;
    private Boolean status;
    private int total;
}
