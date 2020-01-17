package com.LOLdaojucheng.service.Impl;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.dao.UserInfoMapper;
import com.LOLdaojucheng.dao.impl.UserDaoImpl;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IUserService;
import com.LOLdaojucheng.utils.MD5Utils;
import com.LOLdaojucheng.utils.TokenCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public  class UserServiceImpl implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserDaoImpl userDao;

    @Override
    /**
     * 实现用户登录的方法
     */
    public ServerResponse login(String username, String password){
        //去查询是否有此账号
        int result = userDao.checkUser(username);
        if(result==0){
            return ServerResponse.serverResponseByError("用户名不存在");
        }
        //去dao层查询账号的详细信息，且封装到UserInfo
        UserInfo userInfo = userDao.selectUserByUsernameAndPassword(username,MD5Utils.string2MD5(password));
        //检验用户名密码是否匹配
        if (userInfo==null){
            return ServerResponse.serverResponseByError("用户名密码错误");
        }
        //将密码置空
        userInfo.setPassword("");
        //返回登录结果
        return ServerResponse.serverResponseBySucess(userInfo,"登录成功");
    }
    /**
     * 实现用户注册的功能
     * */
    @Override
    public ServerResponse register(UserInfo userInfo) {
        //检查用户名和密码是否为空
        if (userInfo==null){
            return ServerResponse.serverResponseByError("参数必须");
        }
        //检查用户名是否为空
        if(userInfo.getUsername().equals("")||userInfo.getUsername()==null){
            return ServerResponse.serverResponseByError("请输入用户名");
        }
        //检查密码是否为空
        if(userInfo.getPassword().equals("")||userInfo.getPassword()==null){
            return ServerResponse.serverResponseByError("请输入密码");
        }


       //检查用户名是否存在
        int result = userDao.checkUser(userInfo.getUsername());
        if(result>0){
            return ServerResponse.serverResponseByError("用户名已存在");
        }
        //检查邮箱
        int result_email = userDao.checkEmail(userInfo.getEmail());
        if(result_email>0){
            return ServerResponse.serverResponseByError("邮箱已存在");
        }
        userInfo.setRole(Const.RoleEnum.ROLE_CUSTOMER.getCode());
        userInfo.setPassword(MD5Utils.string2MD5(userInfo.getPassword()));
        //注册用户
        int sesult_register = userDao.insert(userInfo);
        if (sesult_register>0){
            return ServerResponse.serverResponseBySuccess("注册成功");
        }

        return ServerResponse.serverResponseByError("注册失败");
    }
    //实现根据用户名查询密保问题的方法
    @Override
    public ServerResponse selectQueryByUsername(String username) {
        //得到用户名以后，需要对用户名进行非空校验
        if (username==null||username.equals("")){
            return ServerResponse.serverResponseByError("用户名不能为空");
        }
        //需要检查用户名是否存在
        int result = userDao.checkUser(username);
        if (result==0){
            return ServerResponse.serverResponseByError("用户名不存在");
        }
        //经过上面两个步骤 确定有这个用户名 那么就需要到数据库中查询该用户名的问题了
        //调用dao层的方法
        String question = userDao.selectQuestionByUsername(username);
        ServerResponse serverResponse = ServerResponse.serverResponseBySucess(question,"找回密码成功");
        return serverResponse;
    }

    /**
     * 根据用户名，问题，密码查询密码是否正确
     * */
    @Override
    public ServerResponse selectQueryByUsernameAndQuestionAndAnswer(String username, String question, String answer) {
        //进行非空校验
        if (username == null || username.equals("")){
            return ServerResponse.serverResponseByError("用户名为空");
        }
        if (question == null || question.equals("")){
            return ServerResponse.serverResponseByError("问题为空");
        }
        if (answer == null || answer.equals("")){
            return ServerResponse.serverResponseByError("答案为空");
        }

        //校验答案是否正确
        int result = userDao.selectQueryByUsernameAndQuestionAndAnswer(username,question,answer);
        if (result==0){//答案不正确
            return ServerResponse.serverResponseByError("答案错误");
        }
        //获取token,防止横向越权
        String forgettoken = UUID.randomUUID().toString();
        TokenCache.set(username,forgettoken);
        // 返回给客户端
        return ServerResponse.serverResponseBySuccess(forgettoken);
    }

    @Override
    public ServerResponse updatePasswordByUsernameAndForgettoken(String username, String password, String forgettoken) {
        //校验参数
        if (username == null || username.equals("")){
            return ServerResponse.serverResponseByError("用户名为空");
        }
        if (password == null || password.equals("")){
            return ServerResponse.serverResponseByError("问题为空");
        }
        if (forgettoken == null || forgettoken.equals("")){
            return ServerResponse.serverResponseByError("答案为空");
        }

        //校验token
        String token = TokenCache.get(username);
        if(token==null||token.equals("")){
            return ServerResponse.serverResponseByError("token失效");
        }
        if(!token.equals(forgettoken)){
            return ServerResponse.serverResponseByError("无效的token");
        }
        //判断是否修改成功
        int result = userDao.updatePasswordByUsernameAndForgettoken(username,MD5Utils.string2MD5(password));
        if(result>0){
            return ServerResponse.serverResponseBySuccess();
        }
        return ServerResponse.serverResponseByError("修改密码失败了");
    }

    @Override
    public ServerResponse check_valid(String str, String type) {
        //对参数进行非空校验
        if (str==null||type.equals("")){
            return  ServerResponse.serverResponseByError("用户名或邮箱不能为空");
        }
        if (type==null||type.equals("")){
            return  ServerResponse.serverResponseByError("类型不能为空");
        }
        //判断是什么类型
        if (type.equals("username")||type.equals("用户名")){//类型是用户名
            int result = userDao.checkUser(str);
            if (result>0){
                return ServerResponse.serverResponseByError("用户名已存在");
            }
            return ServerResponse.serverResponseBySuccess("可以注册");
        }else if (type.equals("Email")||type.equals("邮箱")){//类型是邮箱
            int result = userDao.checkEmail(str);
            if (result>0){
                return  ServerResponse.serverResponseByError("邮箱已被注册");
            }
            return  ServerResponse.serverResponseBySuccess("可以使用该邮箱");
        }else {
            return ServerResponse.serverResponseByError("无效的类型");
        }

    }

    @Override
    public ServerResponse update_password_login(String username, String passwordOld, String passwordNew) {
        //非空校验

        if (passwordOld==null||passwordOld.equals("")){
            return ServerResponse.serverResponseByError("旧密码为空");
        }
        if (passwordNew==null||passwordNew.equals("")){
            return ServerResponse.serverResponseByError("新密码为空");
        }

        //根据用户名和旧密码查询用户信息
        UserInfo userInfo = userDao.selectUserByUsernameAndPassword(username,MD5Utils.string2MD5(passwordOld));
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("用户旧密码错误");
        }

        //修改密码
        userInfo.setPassword(MD5Utils.string2MD5(passwordNew));
        int result = userDao.updateByPrimaryKey(userInfo);
        if (result > 0) {
            return ServerResponse.serverResponseBySuccess("修改成功");
        }

        return ServerResponse.serverResponseByError("更新失败");
    }

    @Override
    public ServerResponse update_information(UserInfo user) {
        //进行参数的非空校验
        if (user==null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        //调用dao层的方法
        int result = userDao.update_information(user);
        if (result>0){
            return ServerResponse.serverResponseBySuccess();
        }
        return ServerResponse.serverResponseByError("更新出错");
    }

    @Override
    public UserInfo getInformationById(int id) {
        UserInfo userInfo = userDao.getInformationById(id);
        return userInfo;
    }

    /***
     * 后台
     * 用户登录
     */
    @Override
    public ServerResponse login_back(String username, String password){
        //判断用户名是否为空
        if(username==null||username.equals("")) {
            return ServerResponse.serverResponseByError("用户名为空");
        }
        //判断密码是否为空
        if (password==null||password.equals("")){
            return ServerResponse.serverResponseByError("密码为空");
        }
        //去查询是否有此账号
        int result = userDao.checkUser(username);
        if(result==0){
            return ServerResponse.serverResponseByError("用户名不存在");
        }

        //去dao层查询账号的详细信息，且封装到UserInfo
        UserInfo userInfo = userDao.selectUserByUsernameAndPassword(username,MD5Utils.string2MD5(password));
        //检验用户名密码是否匹配
        if (userInfo==null){
            return ServerResponse.serverResponseByError("用户名密码错误");
        }
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseBySuccess("用户权限不够");
        }
        //将密码置空
        userInfo.setPassword("");
        //返回登录结果
        return ServerResponse.serverResponseBySucess(userInfo,"登录成功");
    }
    //保存token
    @Override
    public int savaTokenByUserId(Integer userId, String token) {
        userInfoMapper.updateTokenByUserId(userId,token);
        return 0;
    }

    @Override
    public UserInfo findUserInfoByToken(String token) {
        if (token==null||token.equals("")){
            return null;
        }
        return userInfoMapper.findUserInfoByToken(token);
    }

    /***
     * 分页展示用户列表
     */
    @Override
    public ServerResponse selectAllUser(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);

        return ServerResponse.serverResponseBySuccess(pageInfo);
    }

    /***
     * 更改用户状态
     */
    @Override
    public  ServerResponse updateRole(Integer userId,Integer role){
        int result = userInfoMapper.updateRole(userId,role);
        if (result<1){
            return ServerResponse.serverResponseByError("更新失败");
        }else {
            return ServerResponse.serverResponseBySuccess("更改用户状态成功");
        }
    }
}
