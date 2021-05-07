package com.cache.sun.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Package: com.cache.sun.bean包名
 * @ClassName: RolesBean主类名
 * @Author: 22947作者
 * @CreateTime: 9:39 创建时间
 * @Description: 文件描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesBean {
    private String id;
    private String roleName;
    private String roleDescrit;
    private String roleId;
    private Map[] children;
    private int total;
}
