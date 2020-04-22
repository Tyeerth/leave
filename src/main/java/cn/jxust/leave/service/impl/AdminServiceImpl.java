package cn.jxust.leave.service.impl;

import cn.jxust.leave.dao.AdminMapper;
import cn.jxust.leave.dao.StudentMapper;
import cn.jxust.leave.po.Employee;
import cn.jxust.leave.po.Student;
import cn.jxust.leave.service.AdminService;
import cn.jxust.leave.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是管理员服务层的实现类
 * @author tyeerth
 * @date 2019/11/13 - 19:26
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 修改管理员信息。
     * @param admin
     */
    public void update(Employee admin) {
        adminMapper.updateAdmin(admin);
    }

    /**
     * 实现管理员登入功能
     * @param username
     */
    public Employee getAdminByUsername(String username) {
        return adminMapper.getAdminByUserName(username);
    }

    /**
     * 管理员导入学生信息。
     * @param in
     * @param file
     * @throws Exception
     */
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception {
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<Student> studentList = new ArrayList<Student>();
        int academy_id = 0;
        int campus_id = 0;
        int role_id = 0;
        int major_id = 0;
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            Student student = new Student();
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
//            student.setId(Integer.parseInt(ob.get(0).toString()));
            student.setName(String.valueOf(ob.get(1)));
            if (String.valueOf(ob.get(2)).equals("男")) {
                student.setGender("1");
            }else {
                student.setGender("0");
            }
            switch (String.valueOf(ob.get(3))){

                case "信息学院" : academy_id = 1;break;
                case "文法学院": academy_id = 2;break;
                case "建测学院": academy_id = 3;break;
                case "理学院": academy_id = 4;break;
                case "外语外贸学院": academy_id = 5;break;
                case "机电学院": academy_id = 6;break;
                case "材料冶金化学学部": academy_id = 7;break;
                case "商学院": academy_id = 8;break;
                case "资源与环境管理工程学院": academy_id = 9;break;
                case "马克思主义学院": academy_id = 10;break;
                case "能源与机械工程学院": academy_id = 11;break;
                case "电气工程学院": academy_id = 12;break;
                case "软件工程学院": academy_id = 13;break;
                case "经济管理学院": academy_id = 14;break;
            }
                  student.setAcademyId(academy_id);
                switch (String.valueOf(ob.get(4))){
                    case "红旗校区" : campus_id = 1;break;
                    case "黄金校区" : campus_id = 2;break;
                    case "南昌校区" : campus_id = 3;break;
                    case "西校区"   : campus_id = 4;break;
                }
                student.setCampusId(campus_id);
                student.setUniversity(String.valueOf(ob.get(5)));
                student.setCardNumber(String.valueOf(ob.get(6)));
                student.setPassword(String.valueOf(ob.get(7)));
                student.setAddress(String.valueOf(ob.get(8)));
                student.setPhoneNumber(String.valueOf(ob.get(9)));
                student.setBuildingId(Integer.parseInt(String.valueOf(ob.get(10))));
                student.setDormitory(String.valueOf(ob.get(11)));
                switch (String.valueOf(ob.get(12))){
                    case "学生" : role_id = 1; break;
                    case "班长" : role_id = 2; break;
                    case "班主任" : role_id = 3; break;
                    case "学院(校区)领导" : role_id = 4; break;
                    case "学工部" : role_id = 5; break;
                    case "学校主管领导" : role_id = 6; break;
                    case "辅导员" : role_id = 7; break;
                    case "管理员" : role_id = 8; break;
                }
                student.setRoleId(role_id);
                student.setMail(String.valueOf(ob.get(13)));
                student.setState(String.valueOf(ob.get(14)));
                switch (String.valueOf(ob.get(15))){
                    case "计算机" : major_id = 1;break;
                    case "网络工程" : major_id = 2;break;
                    case "信息安全" : major_id = 3;break;
                    case "法学" : major_id = 4;break;
                    case "电子信息工程" : major_id = 5;break;
                    case "机械制造" : major_id = 6;break;
                    case "工商管理" : major_id = 7;break;
                }
                student.setMajorId(major_id);
                student.setClazz(String.valueOf(ob.get(16)));
                student.setGrade(Integer.parseInt(String.valueOf(ob.get(17))));
                studentMapper.addStudent(student);
//                studentList.add(student);
        }
        //批量插入
//        studentMapper.insertInfoBatch(studentList);
    }

    @Override
    public String InputExcel(InputStream is, String originalFilename) {
        return null;
    }


}
