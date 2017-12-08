package com.hanlinbode.hlbd;

import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.util.JWTUtil;
import com.hanlinbode.hlbd.util.UUIDUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilTest {
    private Logger logger = LoggerFactory.getLogger(UtilTest.class);
    private String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTI1NDAwNTcsInN1YiI6IlRUVDE4NzYxNTY0NDI1IiwiZXhwIjoxNTEyNTQxODU3fQ.WWELuYROPPD-o-WGbkOuWSFVTFI0NU0n14PY3Dz5REk";

    @Test
    public void testGenerateToken() {
        Token t = Token.generateToken("123456");
        logger.info(t.toString());
    }

}
