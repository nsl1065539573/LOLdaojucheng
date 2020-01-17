package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;



public interface IUserService {
    /**
     * 登录接口
     * */
    ServerResponse login(String username, String password);
    /**
     * 注册接口
     * */
    ServerResponse register(UserInfo userInfo);
    /**
     * 根据用户名查询密保问题接口
     * */
    ServerResponse selectQueryByUsername(String username);
    /**
     * 根据用户名，问题，密码查询密码是否正确
     * */
     ServerResponse selectQueryByUsernameAndQuestionAndAnswer(String username,String question,String answer);
    /**
     * 根据用户名以及forgettoken修改密码
     * */
     ServerResponse updatePasswordByUsernameAndForgettoken(String username,String password,String forgettoken);
     /**
      * 检查用户名或者邮箱是否有效
      * */
     ServerResponse check_valid(String str,String type);
     /**
      * 登陆中修改密码
      * */
     ServerResponse update_password_login(String username,String passwordOld,String passwordNew);
     /**
      * 登陆中修改用户信息
      * */
     ServerResponse update_information(UserInfo user);
    /**
     * 根据用户id查询用户信息
     * */
    UserInfo getInformationById(int id);
    /***
     * 后台用户登录
     */
    ServerResponse login_back(String name,String password);

    /***
     * 保存用户的token信息
     */
    int savaTokenByUserId(Integer userId,String token);
    /***
     * 根据token查询用户信息
     */
    UserInfo findUserInfoByToken(String token);

    /***
     * 分页展示用户列表
     */
    ServerResponse selectAllUser(Integer pageNum,Integer pageSize);

    /***
     * 更改用户状态
     */
    ServerResponse updateRole(Integer userId,Integer role);

}
