package com.xzb.product.simplejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author: xzb
 * @date: 2019/7/8
 * @description:
 */
@Repository
public class SimpleJdbcDemo {
    @Autowired
    @Qualifier("sourceTemplate")
    private JdbcTemplate jdbcTemplate;

    public void batchInsert() {
        jdbcTemplate.batchUpdate("INSERT INTO test.t_member VALUES (?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, UUID.randomUUID().toString());
                        ps.setString(2, "xiaozhang");
                        ps.setString(3, "18823464285");
                        ps.setInt(4, 18);
                        ps.setString(5, "nanc");
                    }

                    @Override
                    public int getBatchSize() {
                        return 2;
                    }
                }
        );

    }

    public void setSimpleJdbcInsert() {

    }

}
