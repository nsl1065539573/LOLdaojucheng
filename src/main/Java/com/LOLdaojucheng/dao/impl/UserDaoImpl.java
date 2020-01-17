package com.LOLdaojucheng.dao.impl;

import com.LOLdaojucheng.dao.UserInfoMapper;
import com.LOLdaojucheng.dao.inte.UserDao;
import com.LOLdaojucheng.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int checkUser(String username){
        return userInfoMapper.checkUser(username);
    }

    @Override
    public int checkEmail(String Email) {
        return userInfoMapper.checkEmail(Email);
    }

    public UserInfo selectUserByUsernameAndPassword(String username,String password){
        return userInfoMapper.selectUserByUsernameAndPassword(username,password);
    }

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public String selectQuestionByUsername(String username) {
        return userInfoMapper.selectQuestionByUsername(username);
    }

    @Override
    public int selectQueryByUsernameAndQuestionAndAnswer(String username, String question, String answer) {

        return userInfoMapper.selectQueryByUsernameAndQuestionAndAnswer(username,question,answer);
    }

    @Override
    public int updatePasswordByUsernameAndForgettoken(String username, String password) {
        return userInfoMapper.updatePasswordByUsernameAndForgettoken(username,password);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update_information(UserInfo user) {
        return userInfoMapper.update_information(user);
    }

    @Override
    public UserInfo getInformationById(Integer id) {
        return userInfoMapper.getInformationById(id);
    }
}
