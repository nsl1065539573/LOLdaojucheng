package com.LOLdaojucheng.dao.inte;

import com.LOLdaojucheng.pojo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  {
    /**
     * 检查用户名是否存在
     * */
    int checkUser(String username);
    /**
     *
     * 检查邮箱是否存在
     */
    int checkEmail(String Email);
    /**
     * 根据用户名和密码查找用户信息
     * */
    UserInfo selectUserByUsernameAndPassword( String username, String password);
    /**
     * 注册用户
     * */
    int insert(UserInfo userInfo);
    /**
     * 根据用户名查找密保问题
     * */
    String  selectQuestionByUsername(String username);
    /**
     * 根据用户名问题以及密码查询密码是否正确
     * */
    int selectQueryByUsernameAndQuestionAndAnswer(String username, String question, String answer);
    /**
     * 根据用户名以及forgettoken修改密码
     * */
    int  updatePasswordByUsernameAndForgettoken(String username, String password);
    /**
     * 更新用户信息
     * */
    int updateByPrimaryKey(UserInfo record);
    /**
     * 登陆中更新用户信息
     * */
    int  update_information(UserInfo user);
    /**
     * 根据用户id获得用户信息
     * */
    UserInfo getInformationById(Integer id);
}
