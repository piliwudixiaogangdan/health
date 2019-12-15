package health_mapper;

import health_pojo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByuserName(@Param("username") String username);
}
