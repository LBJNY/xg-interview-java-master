package com.xingu.xg;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 集成测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class XgJavaV2ApplicationTests {


    public static void main(String[] args) {
        System.out.println(IdWorker.getId());
    }
}
