package cn.jxust.leave.service;

import cn.jxust.leave.po.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 管理员的service层。
 * @author tyeerth
 * @date 2019/11/13 - 19:25
 */
public interface AdminService {
    /**
     * 修改管理员信息。
     * @param admin
     */
    void update(Employee admin);

    /**
     * 实现管理员登入
     * 管理员按照用户名登入
     * @param username
     */
    Employee getAdminByUsername(String username);

    void importExcelInfo(InputStream in, MultipartFile file) throws Exception;

    String InputExcel(InputStream is, String originalFilename);


//    void importExcelInfo(InputStream in, MultipartFile file) throws Exception;


    /**
     * 管理员实现导入excel学生信息
     * @param in
     * @param file
     */
//    public void importExcelInfo(InputStream in, CommonsMultipartFile file);
}
