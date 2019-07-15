package com.xzb.product;

import com.xzb.product.simplejdbc.SimpleJdbcDemo;
import com.xzb.product.tranaction.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class
})
@EnableTransactionManagement
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("reportDataSource")
    private DataSource dataSource;
    @Autowired
    @Qualifier("reportTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcDemo simpleJdbcDemo;

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Object o = jdbcTemplate.queryForList("SELECT * FROM t_member LIMIT 1");
        log.info(o.toString());
        log.info(dataSource.toString());*/

        /**
         * 这里无法切换，dataosurce指定了，后面根据mybatis修改
         */
        /*try {
            DynamicDataSourceContextHolder.clear();
            DynamicDataSourceContextHolder.set("source");
            String sql = "SELECT * from `course-study`.t_course_info";
            ResultSet rs = dataSource.getConnection().prepareStatement(sql).executeQuery();

            while (rs.next()) {
                log.info(rs.getString(1));
            }
        } finally {
            DynamicDataSourceContextHolder.clear();
        }*/

        // simpleJdbcDemo.batchInsert();
    }

}
