package com.pl.admin.service;


import com.pl.admin.dto.AuthDto;
import com.pl.admin.dto.LoginDto;
import com.pl.admin.dto.RegisterDto;
import com.pl.admin.dto.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    Result<AuthDto> login(LoginDto ld);
    Result<AuthDto> logout(AuthDto ad);
    Result<AuthDto> register(RegisterDto ld);
    Result<AuthDto> reset(RegisterDto ld);

    Result sendRegister(String email);

    Boolean isConfirmMatch(String email,String confirm);
}
