package com.xzb.product;

import com.xzb.product.common.CommonException;
import com.xzb.product.tranaction.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DruidDemoApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void test1() {
        try {
            studentService.invokeInsertThenRollbackAddTransactional();
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            studentService.invokeInsertThenRollbackByAopContext();
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            studentService.invokeInsertThenRollbackBySelfService();
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }
}
