package com.hanlinbode.hlbd;

import com.hanlinbode.hlbd.composbean.Token;
import com.hanlinbode.hlbd.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilTest {
    private Logger logger = LoggerFactory.getLogger(UtilTest.class);

    @Test
    public void testGenerateToken() {
        Token t = Token.generateToken("123456");
        logger.info(t.toString());
    }
}
