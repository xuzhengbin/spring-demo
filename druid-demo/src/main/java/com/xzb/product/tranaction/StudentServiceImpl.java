package com.xzb.product.tranaction;

import com.xzb.product.common.CommonException;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xzb
 * @date: 2019/7/10
 * @description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    @Qualifier("sourceTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentService studentService;

    @Override
    @Transactional
    public int add() {
        jdbcTemplate.execute("INSERT INTO test.t_student (f_name, f_age, f_address) VALUES ('xzb', 11, 'nanc')");
        return 0;
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void delete() throws CommonException {
        jdbcTemplate.execute("INSERT INTO test.t_student (f_name, f_age, f_address) VALUES ('didi', 12, 'nanc')");
        throw new CommonException("xxxxxxxx");
    }

    /**
     * 不能回滚
     * @throws CommonException
     */
    @Override
    public void test() throws CommonException{
        add();
        delete();
    }

    /**
     * 可以回滚
     * @throws CommonException
     */
    @Override
    public void invokeInsertThenRollbackBySelfService() throws CommonException {
        studentService.delete();
    }

    /**
     * 可以回滚
     * @throws CommonException
     */
    @Override
    public void invokeInsertThenRollbackByAopContext() throws CommonException {
        ((StudentService) (AopContext.currentProxy())).test();
    }

    /**
     * 可以回滚
     * @throws CommonException
     */
    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void invokeInsertThenRollbackAddTransactional() throws CommonException {
        test();
    }
}
