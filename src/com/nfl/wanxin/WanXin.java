package com.nfl.wanxin;

import com.nfl.utils.GsonTool;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.biff.WritableWorkbookImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WanXin {

    private static String Drivde = "org.sqlite.JDBC";

    public static void main(String[] args) {
        try {
            File file = new File("D:\\contacts.csv") ;
            FileWriter fileWriter = new FileWriter(file) ;
            Class.forName(Drivde);// 加载驱动,连接sqlite的jdbc
            Connection connection = DriverManager.getConnection("jdbc:sqlite:databases/csair_ecloud.db");// 连接数据库zhou.db,不存在则创建

            String sql = "select user_id , username , position , sex , tel , phone , email , fax , address from im_user";
            String sql2 = "select deptid , user_id from im_dept_user";
            String sql3 = "select deptname , deptid , parentid from im_dept" ;

            Statement statement = connection.createStatement(); // 创建连接对象，是Java的一个操作数据库的重要接口
            Statement statement2 = connection.createStatement(); // 创建连接对象，是Java的一个操作数据库的重要接口
            Statement statement3 = connection.createStatement(); // 创建连接对象，是Java的一个操作数据库的重要接口

            ResultSet rSet2 = null;// 搜索数据库，将搜索的放入数据集ResultSet中
            ResultSet rSet3 = null;// 搜索数据库，将搜索的放入数据集ResultSet中
            // 获取部门列表
            Map<Integer , Dept> depts = new HashMap<>() ;
            rSet3 = statement3.executeQuery(sql3) ;
            Dept dept = null ;
            while(rSet3.next()){
                dept = new Dept() ;
                dept.setParentid(rSet3.getInt("parentid"));
                dept.setDeptname(rSet3.getString("deptname"));
                depts.put(rSet3.getInt("deptid") , dept) ;
            }
            rSet3.close();rSet3 = null ;
            statement3.close(); statement3 = null ;

            Map<Integer , Integer> userid_deptid = new HashMap<>() ;
            rSet2 = statement2.executeQuery(sql2) ;
            while (rSet2.next()){
                userid_deptid.put(rSet2.getInt("user_id") , rSet2.getInt("deptid")) ;
            }
            rSet2.close();rSet2 = null ;
            statement2.close(); statement2 = null ;
            System.out.println("准备工作完成");
//			statement.executeUpdate("drop table if exists tables");// 判断是否有表tables的存在。有则删除
//			statement.executeUpdate(sql); // 创建数据库
//			statement.executeUpdate("insert into tables values('zhou','156546')");// 向数据库中插入数据
            ResultSet rSet = statement.executeQuery(sql);// 搜索数据库，将搜索的放入数据集ResultSet中
            Employee employee;
            int user_id;
            int deptid;
            int parentid;
            int count = 0;
            while (rSet.next()) { // 遍历这个数据集
                count++;
                employee = new Employee();

                employee.setUsername(rSet.getString("username"));
                fileWriter.write(employee.getUsername());

                employee.setPosition(rSet.getString("position"));
                fileWriter.write(",");
                fileWriter.write(employee.getPosition() + "");

                employee.setSex(rSet.getInt("sex"));
                fileWriter.write(",");
                fileWriter.write(employee.getSex() == 1 ? "男" : "女");

                employee.setTel(rSet.getString("tel"));
                fileWriter.write(",");
                fileWriter.write(employee.getTel() + "");

                employee.setPhone(rSet.getString("phone"));
                fileWriter.write(",");
                fileWriter.write(employee.getPhone() + "");

                employee.setEmail(rSet.getString("email"));
                fileWriter.write(",");
                fileWriter.write(employee.getEmail() + "");

                employee.setFax(rSet.getString("fax"));
                fileWriter.write(",");
                fileWriter.write(employee.getFax() + "");

                employee.setAddress(rSet.getString("address"));
                fileWriter.write(",");
                fileWriter.write(employee.getAddress() + "");

                user_id = rSet.getInt("user_id");
                deptid = userid_deptid.get(user_id) ;
                employee.setDept(depts.get(deptid).getDeptname());
                parentid = depts.get(deptid).getParentid() ;
                while (parentid != 0){
                    employee.setDept(depts.get(parentid).getDeptname() + "/" + employee.getDept() );
                    parentid = depts.get(parentid).getParentid() ;
                }
                fileWriter.write(",");
                fileWriter.write(employee.getDept() + "");
                fileWriter.write("\n");
            }
            fileWriter.close();
            rSet.close();// 关闭数据集
            statement.close();
            connection.close();// 关闭数据库连接
            System.out.println("总数：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
