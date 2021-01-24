package personal.nfl.mybatis;

import personal.nfl.mybatis.beans.User;
import personal.nfl.mybatis.beans.UserMapper;
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
            findMethodThree(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void findMethodOne(SqlSession session) {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user);
        System.out.println("--------------分隔线---------------");
    }

    private static void findMethodTwo(SqlSession session) {
        User user = session.selectOne("com.nfl.mybatis.beans.User.selectUser", 1);
        System.out.println(user);
        System.out.println("--------------分隔线---------------");
    }

    private static void findMethodThree(SqlSession session) {
        List<User> users = session.selectList("com.nfl.mybatis.beans.User.selectUsers");
        System.out.println(users);
        System.out.println("--------------分隔线---------------");
    }
}
