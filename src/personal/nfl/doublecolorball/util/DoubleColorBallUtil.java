package personal.nfl.doublecolorball.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import personal.nfl.doublecolorball.bean.DoubleColorBallBean;
import personal.nfl.mybatis.beans.User;
import personal.nfl.mybatis.beans.UserMapper;
import personal.nfl.utils.DateUtil;
import personal.nfl.utils.MySqlUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoubleColorBallUtil {

    public static void main(String[] args) throws IOException, SQLException {
        File file = new File("双色球历史开奖数据(2003年2月23日-2020年9月17日).txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lineString;
        DoubleColorBallBean doubleColorBallBean;
        Connection connection = MySqlUtil.openDatabase("double_color_ball");
        String sql = "INSERT INTO history_result (occur_date,dateInt,redBall1,redBall2,redBall3,redBall4,redBall5,redBall6,blueBall) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        List<String> stringList = new ArrayList<>();
        while ((lineString = bufferedReader.readLine()) != null) {
            stringList.add(lineString);
        }
        for (int i = stringList.size() - 1; i >= 0; i--) {
            doubleColorBallBean = string2DoubleColorBallBean(stringList.get(i));
            preparedStatement = connection.prepareStatement(sql);
            insert(preparedStatement, doubleColorBallBean);
        }
        if (null != preparedStatement) {
            preparedStatement.close();
        }
        MySqlUtil.close(connection);
    }

    private static DoubleColorBallBean string2DoubleColorBallBean(String str) {
        DoubleColorBallBean doubleColorBallBean = new DoubleColorBallBean();
        doubleColorBallBean.occur_date = new Date(DateUtil.getMilliseconds(str.substring(0, 10)));
        doubleColorBallBean.dateInt = Integer.parseInt(str.substring(11, 18));
        doubleColorBallBean.redBall1 = Integer.parseInt(str.substring(19, 21));
        doubleColorBallBean.redBall2 = Integer.parseInt(str.substring(22, 24));
        doubleColorBallBean.redBall3 = Integer.parseInt(str.substring(25, 27));
        doubleColorBallBean.redBall4 = Integer.parseInt(str.substring(28, 30));
        doubleColorBallBean.redBall5 = Integer.parseInt(str.substring(31, 33));
        doubleColorBallBean.redBall6 = Integer.parseInt(str.substring(34, 36));
        doubleColorBallBean.blueBall = Integer.parseInt(str.substring(37, 39));
        return doubleColorBallBean;
    }

    private static void insert(PreparedStatement preparedStatement, DoubleColorBallBean doubleColorBallBean) throws SQLException {
        preparedStatement.setDate(1, doubleColorBallBean.getOccur_date());
        preparedStatement.setInt(2, doubleColorBallBean.getDateInt());
        int[] allBalls = doubleColorBallBean.getAllBalls();
        for (int j = 0; j < allBalls.length; j++) {
            preparedStatement.setInt(j + 3, allBalls[j]);
        }
        preparedStatement.execute();
    }

    private static void mybatis() throws IOException {
        String resource = "mybatis/DoubleColorBallConfigs.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = ssf.openSession();
        try {
            DoubleColorBallBean doubleColorBallBean = new DoubleColorBallBean();
            doubleColorBallBean.setRedBall1(1);
            doubleColorBallBean.setRedBall2(2);
            doubleColorBallBean.setRedBall3(3);
            doubleColorBallBean.setRedBall4(4);
            doubleColorBallBean.setRedBall5(5);
            doubleColorBallBean.setRedBall6(6);
            doubleColorBallBean.setBlueBall(7);
            insert(session, doubleColorBallBean);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void insert(SqlSession session, DoubleColorBallBean doubleColorBallBean) {
        int result = session.insert("com.nfl.doublecolorball.bean.DoubleColorBallBean.insert", session);
        System.out.printf("插入数据结果：" + result);
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
        List<DoubleColorBallBean> doubleColorBallBeans = session.selectList("com.nfl.doublecolorball.bean.DoubleColorBallBean.selectAll");
        System.out.println(doubleColorBallBeans);
        System.out.println("--------------分隔线---------------");
    }
}
