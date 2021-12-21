package com.sparta.springcore.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.dto.UsernameDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.KakaoUserService;
import com.sparta.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code,response);
        return "redirect:/";
    }

    @PutMapping("/username")
    public void postUser(@RequestBody UsernameDto usernameDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        userService.postUser(usernameDto,userDetails);
    }
}

