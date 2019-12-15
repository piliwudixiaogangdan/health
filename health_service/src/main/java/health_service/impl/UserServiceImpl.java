package health_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import health_interface.UserService;
import health_mapper.UserMapper;
import health_pojo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
       User user =  userMapper.findByuserName(username);
        return user;
    }
}
