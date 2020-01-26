package com.igeek.gmall.user.controller;

import com.igeek.gmall.pojo.UmsMember;
import com.igeek.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-14 10:07
 */
@RestController
public class UmsMemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/user")
    public List<UmsMember> queryUserAll(){
        return memberService.queryAll();
    }

    @RequestMapping("saveUser")
    public String saveUser(UmsMember umsMember){
        int result = memberService.save(umsMember);
        return result+"";
    }
}
