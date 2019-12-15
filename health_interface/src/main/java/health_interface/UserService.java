package health_interface;

import health_pojo.pojo.User;

public interface UserService {
    User findByUserName(String username);
}
