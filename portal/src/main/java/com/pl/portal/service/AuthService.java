package com.pl.portal.service;


import com.pl.portal.dto.AuthDto;
import com.pl.portal.dto.LoginDto;
import com.pl.portal.dto.RegisterDto;
import com.pl.portal.dto.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    Result<AuthDto> login(LoginDto ld);
    Result<AuthDto> logout(AuthDto ad);
    Result<AuthDto> register(RegisterDto ld);
    Result<AuthDto> reset(RegisterDto ld);

    Result sendRegister(String email);

    Boolean isConfirmMatch(String email,String confirm);
}
