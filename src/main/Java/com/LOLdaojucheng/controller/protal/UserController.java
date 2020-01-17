package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;


    /**
     * 登录功能Restful
     * */
    @RequestMapping("/login/{username}/{password}")
    public ServerResponse loginRestful(HttpSession session,
                                @PathVariable("username") String username,
                                @PathVariable("password") String password){

        //获取了用户名密码之后需要去service层处理业务逻辑

        ServerResponse serverResponse =  iUserService.login(username,password);
        //判断是否登录成功
        if (serverResponse.isSuccess()){
            UserInfo userInfo = (UserInfo)serverResponse.getData();
            if (userInfo.getRole()!=Const.RoleEnum.ROLE_CUSTOMER.getCode()){
                return ServerResponse.serverResponseByError("管理员不能登录普通用户");
            }
            session.setAttribute(Const.CURRENTUSER,userInfo);
        }

        return serverResponse;
    }
    /**
     * 登录功能
     * */
    @RequestMapping("/login")
    public ServerResponse login(HttpSession session,
                                 String username,
                                 String password){

        //获取了用户名密码之后需要去service层处理业务逻辑

        ServerResponse serverResponse =  iUserService.login(username,password);
        //判断是否登录成功
        if (serverResponse.isSuccess()){
            UserInfo userInfo = (UserInfo)serverResponse.getData();
            session.setAttribute(Const.CURRENTUSER,userInfo);

        }

        return serverResponse;
    }
    /***
     * 检测用户是否登录
     */
    @RequestMapping("/haveSession")
    public ServerResponse haveSession(HttpSession session){
        Object object = session.getAttribute(Const.CURRENTUSER);
        if (object!=null){
            return ServerResponse.serverResponseBySuccess(object);
        }else {
            return ServerResponse.serverResponseByError("没有session信息");
        }
    }
    /**
     * 注册功能
     * */
 @ResponseBody
@RequestMapping("/register.do")
    public ServerResponse register(HttpSession session,UserInfo userInfo){
        //接收到参数之后转到service层去处理业务逻辑
        ServerResponse serverResponse = iUserService.register(userInfo);
        return serverResponse;
    }


    /**
     * 根据用户名查找用户找回密码问题功能
     * */
    @RequestMapping("/find_question")
    public ServerResponse selectQueryByUsername(String username){
        ServerResponse serverResponse = iUserService.selectQueryByUsername(username);
        return serverResponse;
    }
    /**
     * 根据用户名查找用户找回密码问题功能Restful
     * */
    @RequestMapping("/find_question/{username}")
    public ServerResponse selectQueryByUsernameRestful(@PathVariable("username") String username){
        ServerResponse serverResponse = iUserService.selectQueryByUsername(username);
        return serverResponse;
    }
    /**
     * 根据用户名问题密码查询密码是否正确
     * */
    @RequestMapping("/select_isAnswerTrue")
    public ServerResponse selectQueryByUsernameAndQuestionAndAnswer(String username,String question,String answer){
        ServerResponse serverResponse = iUserService.selectQueryByUsernameAndQuestionAndAnswer(username,question,answer);
        return serverResponse;
    }
    /**
     * 根据用户名问题密码查询密码是否正确Restful
     * */
    @RequestMapping("/select_isAnswerTrue/{username}/{question}/{answer}")
    public ServerResponse selectQueryByUsernameAndQuestionAndAnswerRestful(@PathVariable("username") String username,
                                                                           @PathVariable("question") String question,
                                                                           @PathVariable("answer") String answer){
        ServerResponse serverResponse = iUserService.selectQueryByUsernameAndQuestionAndAnswer(username,question,answer);
        return serverResponse;
    }
    /**
     *根据用户名以及forgettoken修改密码
     * */
    @RequestMapping("/update_password")
    public ServerResponse updatePasswordByUsernameAndForgettoken(String username,String password,String forgettoken){

        ServerResponse serverResponse = iUserService.updatePasswordByUsernameAndForgettoken(username,password,forgettoken);
        return serverResponse;
    }
    /**
     *根据用户名以及forgettoken修改密码Restful
     * */
    @RequestMapping("/update_password/{username}/{password}/{forgettoken}")
    public ServerResponse updatePasswordByUsernameAndForgettokenRestful(@PathVariable("username") String username,
                                                                        @PathVariable("password") String password,
                                                                        @PathVariable("forgettoken") String forgettoken){

        ServerResponse serverResponse = iUserService.updatePasswordByUsernameAndForgettoken(username,password,forgettoken);
        return serverResponse;
    }
    /**
     * 检查用户名和邮箱是否有效
     * */
    @RequestMapping("/check_valid")
    public ServerResponse check_valid(String str,String type){
        ServerResponse serverResponse = iUserService.check_valid(str,type);
        return serverResponse;
    }

    /**
     * 检查用户名和邮箱是否有效Restful
     * */
    @RequestMapping("/check_valid/{str}/{type}")
    public ServerResponse check_validRestful(@PathVariable("str") String str,
                                             @PathVariable("type") String type){
        ServerResponse serverResponse = iUserService.check_valid(str,type);
        return serverResponse;
    }

    /**
     * 检查登录用户信息
     * */
    @RequestMapping("/check_user_info")
    public ServerResponse check_user_info(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        userInfo.setPassword("");
        userInfo.setRole(null);
        userInfo.setAnswer("");
        userInfo.setQuestion("");
        return ServerResponse.serverResponseBySuccess(userInfo);
    }
    /**
     * 登录状态修改密码
     * */
    @RequestMapping("/update_password_login")
    public ServerResponse update_password_login(HttpSession session,String passwordOld,String passwordNew){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录");
        }
        ServerResponse serverResponse = iUserService.update_password_login(userInfo.getUsername(),passwordOld,passwordNew);
        return serverResponse;
    }

    /**
     * 登录状态修改密码Restful
     * */
    @RequestMapping("/update_password_login/{passwordOld}/{passwordNew}")
    public ServerResponse update_password_loginRestful(HttpSession session,
                                                       @PathVariable("passwordOld") String passwordOld,
                                                       @PathVariable("passwordNew") String passwordNew){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录");
        }
        ServerResponse serverResponse = iUserService.update_password_login(userInfo.getUsername(),passwordOld,passwordNew);
        return serverResponse;
    }
    /**
     * 更新个人信息
     * */
    @RequestMapping("/update_information")
    public ServerResponse update_information(HttpSession session,UserInfo user){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){//说明没有登录
            ServerResponse.serverResponseByError("没有进行登录");
        }
        user.setId(userInfo.getId());//根据用户ID进行查询 所以要给他ID
        ServerResponse serverResponse = iUserService.update_information(user);
        if (serverResponse.isSuccess()){
            UserInfo userInfo1 = iUserService.getInformationById(user.getId());

            session.setAttribute(Const.CURRENTUSER,userInfo1);
        }



        return  serverResponse;
    }
    /**
     * 查看登录用户详细信息
     * */
    @RequestMapping("/get_user_info")
    public ServerResponse get_user_info(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录");
        }

        return ServerResponse.serverResponseBySuccess(userInfo);
    }
    /***
     * 是否登录
     */
    @RequestMapping("/hasLogin")
    public ServerResponse hasLogin(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("未登录");
        }else {
            return ServerResponse.serverResponseBySuccess("已经登录");
        }
    }
    /**
     * 退出登录
     * */
    @RequestMapping("/exit")
    public ServerResponse exit(HttpSession session){
        session.removeAttribute(Const.CURRENTUSER);
        return ServerResponse.serverResponseBySuccess("退出登录成功");
    }
}
