package com.pablojvm.application;

import com.pablojvm.user.LoginData;

import java.util.List;

public interface ValidationData {
    List<String> loginData(LoginData data);
}
