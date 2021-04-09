package com.cache.sun.bean;

import lombok.Data;
import java.util.Map;

/**
 * @Package: com.cache.sun.bean包名
 * @ClassName: MenuBean主类名
 * @Author: 22947作者
 * @CreateTime: 10:54 创建时间
 * @Description: 文件描述信息
 */
@Data
public class MenuBean {
    private Integer id;
    private String authName;
    private String path;
    private Map[] children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getPath() {
        return path;
    }


    public Map[] getChildren() {
        return children;
    }

    public void setChildren(Map[] children) {
        this.children = children;
    }

    public void setPath(String path) {
        this.path = path;
    }



}
