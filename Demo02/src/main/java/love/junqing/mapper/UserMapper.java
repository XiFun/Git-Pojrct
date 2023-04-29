package love.junqing.mapper;

import love.junqing.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> allUser(User user);
    Integer deleteUser(@Param("usernames") String []usernames);
    Integer addUsers(@Param("users") List<User> users);
}
