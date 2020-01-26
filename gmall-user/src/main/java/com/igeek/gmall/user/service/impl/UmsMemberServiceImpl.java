package com.igeek.gmall.user.service.impl;


import com.igeek.gmall.pojo.UmsMember;
import com.igeek.gmall.service.MemberService;
import com.igeek.gmall.user.mapper.UmsMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-14 10:06
 */
@Service
public class UmsMemberServiceImpl implements MemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public List<UmsMember> queryAll() {
        return umsMemberMapper.selectAll();
    }

    @Override
    public int save(UmsMember umsMember) {
        int result  = umsMemberMapper.insertSelective(umsMember);
        System.out.println(umsMember);
        return result;
    }

}
