package com.igeek.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.UmsMember;
import com.igeek.gmall.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-26 19:21
 */
@RestController
public class MemberController {

    @Reference
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
