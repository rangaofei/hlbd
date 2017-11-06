package com.hanlinbode.hlbd.service;

import com.hanlinbode.hlbd.composbean.Token;

public interface TokenService {
    Token generateToken(String src);
}
