package com.xzb.product.simplejdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author: xzb
 * @date: 2019/7/8
 * @description:
 */
@Data
@Builder
public class Member {
    private String id;
    private String name;
    private String mobile;
    private int age;
    private String address;
}
