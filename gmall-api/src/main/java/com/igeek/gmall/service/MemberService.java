package com.igeek.gmall.service;

import com.igeek.gmall.pojo.UmsMember;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-26 13:50
 */
public interface MemberService {

    List<UmsMember> queryAll();

    int save(UmsMember umsMember);
}
