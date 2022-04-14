package com.example.demo.member;

import com.example.demo.member.MemberService;
import com.example.demo.member.annotation.ClassAop;
import com.example.demo.member.annotation.MethodAop;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
