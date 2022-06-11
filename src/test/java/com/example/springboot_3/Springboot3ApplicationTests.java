package com.example.springboot_3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class Springboot3ApplicationTests {

    @Test
    void contextLoads() {
        int a = 1;
        int b = 2;
        Assert.isTrue(a == b,"what??");
    }
  

}
