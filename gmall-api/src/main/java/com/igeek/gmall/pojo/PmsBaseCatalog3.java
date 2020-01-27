package com.igeek.gmall.pojo;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author 余祥刚
 * @create 2020-01-27 13:58
 */
@Data
public class PmsBaseCatalog3 implements java.io.Serializable{

    @Id
    private String id;

    private String name;

    private String catalog2Id;

}
