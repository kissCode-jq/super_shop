package com.cache.sun.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.cache.sun.bean包名
 * @ClassName: Rights主类名
 * @Author: 22947作者
 * @CreateTime: 14:35 创建时间
 * @Description: 文件描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RightsBean {
    private String id;
    private String rightName;
    private String rightGrand;
    private String rightPath;
}
