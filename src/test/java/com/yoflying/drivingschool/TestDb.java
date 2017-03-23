package com.yoflying.drivingschool;

import com.yoflying.drivingschool.domain.dao.TokenMapper;
import com.yoflying.drivingschool.domain.model.Token;
import com.yoflying.drivingschool.domain.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liqiang on 16/12/18.
 */
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles("single")
public class TestDb {

    @Autowired
    TokenService mapper;
    @Test
    public void test() {
        Token token = new Token();
        token.setTokenCode("111");
        token.setCategory(1);
        token.setUserId(1L);
        mapper.insertToken(token);
    }
}
