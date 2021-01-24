package personal.nfl.mybatis.beans;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id= #{id}")
    User findById(int id);

    @Select("select * from user")
    List<User> getAll();
}
