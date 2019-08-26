package com.wu.service.impl;

import com.wu.dao.UserDao;
import com.wu.pojo.Role;
import com.wu.pojo.Users;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务层实现类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    /************************************************************************/
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**************************************************************************/

    /**
     * 查询所有
     */
    public List<Users> findAll()throws Exception{
        //调用持久层查询方法，获取返回值
        return userDao.findAll();
    }

    /**
     * 保存用户信息
     * @param users
     * @throws Exception
     */
    public void save(Users users) throws Exception {
         //将密码加密
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        //调用持久层方法保存用户
        userDao.save(users);
    }

    /**
     * 根据用户id查寻用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public Users findById(String id) throws Exception {
        //调用持久层查询方法
        Users users = userDao.findById(id);

        return users;
    }

    /**
     * 将所选的所有角色全都添加到所选用户当中
     * @param userId
     * @param ids
     * @throws Exception
     */
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        //遍历角色id
        for (String id : ids) {
            //调用持久层方法将角色添加到指定用户当中
            userDao.addRoleToUser(userId,id);
        }
    }


    /**
     * 权限管理登录
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users=null;
        try {
            users = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SimpleGrantedAuthority> authoritys=getAuthority(users.getRoles());

        //处理自己的用户对象封装成UserDetails
//        User user=new User(users.getUsername(),"{noop}"+users.getPassword(),getAuthority(users.getRoles()));

        User user=new User(users.getUsername(),users.getPassword(),
                users.getStatus()==0?false:true,true,true,true, authoritys);

        return user;
    }

    /**
     * 返回一个权限的集合
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {

            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return userDao.findOtherRole(id);
    }
}
