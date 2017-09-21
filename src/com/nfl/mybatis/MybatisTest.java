package com.nfl.mybatis;

import com.nfl.mybatis.beans.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by nfl on 2017/9/21.
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "configuration.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = ssf.openSession();
        try {
            User user = session.selectOne("selectUser", 1);
            System.out.println(user.getName());
            System.out.println(user);
            System.out.println("--------------分隔线---------------");

            List<User> users = session.selectList("selectUsers");
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
