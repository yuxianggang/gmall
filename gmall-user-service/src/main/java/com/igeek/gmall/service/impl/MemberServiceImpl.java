package com.igeek.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.mapper.MemberMapper;
import com.igeek.gmall.pojo.UmsMember;
import com.igeek.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-26 18:14
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<UmsMember> queryAll() {
        return memberMapper.selectAll();
    }

    public int save(UmsMember umsMember) {
        return memberMapper.insertSelective(umsMember);
    }
}
