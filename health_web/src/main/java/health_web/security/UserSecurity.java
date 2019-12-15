package health_web.security;

import com.alibaba.dubbo.config.annotation.Reference;
import health_interface.UserService;
import health_pojo.pojo.Permission;
import health_pojo.pojo.Role;
import health_pojo.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component()
public class UserSecurity implements UserDetailsService {

    @Reference
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //去数据库查询用户的权限和角色
        User user = userService.findByUserName(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        //给用户添加角色

        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword(),grantedAuthorities );
    }
}
