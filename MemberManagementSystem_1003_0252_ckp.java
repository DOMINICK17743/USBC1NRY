// 代码生成时间: 2025-10-03 02:52:27
package com.example.membersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MemberManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(MemberManagementSystem.class, args);
    }
}

// Member entity
package com.example.membersystem.model;

public class Member {
    private Long id;
    private String name;
    private String email;
    private String phone;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

// MemberService interface
package com.example.membersystem.service;

import com.example.membersystem.model.Member;
import reactor.core.publisher.Mono;

public interface MemberService {
    Mono<Member> createMember(Member member);
    Mono<Member> getMemberById(Long id);
    Mono<Void> deleteMemberById(Long id);
    // Other member related operations
}

// MemberServiceImpl class
package com.example.membersystem.service.impl;

import com.example.membersystem.model.Member;
import com.example.membersystem.repository.MemberRepository;
import com.example.membersystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Mono<Member> createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Mono<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteMemberById(Long id) {
        return memberRepository.deleteById(id);
    }

    // Other member related operations
}

// MemberRepository interface
package com.example.membersystem.repository;

import com.example.membersystem.model.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemberRepository extends ReactiveCrudRepository<Member, Long> {
    // Custom queries can be added here
}

// MemberController class
package com.example.membersystem.controller;

import com.example.membersystem.model.Member;
import com.example.membersystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Mono<ResponseEntity<Member>> createMember(@RequestBody Member member) {
        return memberService.createMember(member)
                .map(member1 -> ResponseEntity.ok().body(member1));
    }

    @GetMapping(