package com.igeek.gmall.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-02-07 12:58
 */
@Data
public class PmsSearchParam implements java.io.Serializable{

    private String keyword;

    private String catalog3Id;

    private String [] attrValueIds;
}
