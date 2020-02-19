package com.pl.admin.service;


import com.pl.admin.dto.Result;

public interface Notifier {
    Result sendRegister(String addr, String sid, String code);
    Result sendInvite(String add, String invideId);
}
