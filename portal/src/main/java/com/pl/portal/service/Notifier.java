package com.pl.portal.service;


import com.pl.portal.dto.Result;

public interface Notifier {
    Result sendRegister(String addr, String sid, String code);
    Result sendInvite(String add, String invideId);
}
