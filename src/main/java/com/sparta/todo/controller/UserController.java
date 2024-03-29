package com.sparta.todo.controller;

import com.sparta.todo.dto.user.LoginRequestDto;
import com.sparta.todo.dto.user.LoginResponseDto;
import com.sparta.todo.dto.user.SignupRequestDto;
import com.sparta.todo.service.UserService;
import com.sparta.todo.status.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(
        summary = "회원 가입",
        description = "이름과 비밀번호를 입력해 주세요"
    )
    public ApiResult signup(@RequestBody @Valid SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        description = "이름과 비밀번호를 입력해 주세요"
    )
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }

}
