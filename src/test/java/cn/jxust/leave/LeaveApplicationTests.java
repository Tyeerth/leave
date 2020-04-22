package cn.jxust.leave;

import cn.jxust.leave.dao.StudentMapper;
import cn.jxust.leave.po.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class LeaveApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private StudentMapper studentMapper;
    private Logger logger = LoggerFactory.getLogger(LeaveApplicationTests.class);
    @Test
    public void testConnection() throws SQLException {

        logger.info("成功连接");
        Connection connection = dataSource.getConnection();

        logger.debug(connection.toString());
    }
    @Test
    public  void testMapper(){
        Student studentById = studentMapper.getStudentById(2);
        logger.info(studentById.toString());
    }
}
