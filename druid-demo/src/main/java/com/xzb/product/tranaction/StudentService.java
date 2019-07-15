package com.xzb.product.tranaction;

import com.xzb.product.common.CommonException;

/**
 * @author: xzb
 * @date: 2019/7/10
 * @description:
 */
public interface StudentService {
    int add();
    void delete() throws CommonException;
    void test() throws CommonException;


    void invokeInsertThenRollbackBySelfService() throws CommonException;

    void invokeInsertThenRollbackByAopContext() throws CommonException;

    void invokeInsertThenRollbackAddTransactional() throws CommonException;
}
