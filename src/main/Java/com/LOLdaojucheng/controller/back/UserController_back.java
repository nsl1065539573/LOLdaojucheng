package com.LOLdaojucheng.controller.back;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IUserService;
import com.LOLdaojucheng.utils.IpUtils;
import com.LOLdaojucheng.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.SocketException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/manage/user")
public class UserController_back {
    @Autowired
    IUserService iUserService;

    /**
     * 登录功能
     * */
    @RequestMapping("/login")
    public ServerResponse login(HttpSession session,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password){

        //获取了用户名密码之后需要去service层处理业务逻辑

        ServerResponse serverResponse =  iUserService.login_back(username,password);
        //判断是否登录成功
        if (serverResponse.isSuccess()){
            UserInfo userInfo = (UserInfo)serverResponse.getData();
            session.setAttribute(Const.CURRENTUSER,userInfo);
        }
        return serverResponse;
    }
    /**
     * 登录功能Restful
     * */
    @RequestMapping("/login/{username}/{password}")
    public ServerResponse loginRestful(HttpServletRequest request,
                                       HttpSession session,
                                       HttpServletResponse response,
                                       @PathVariable("username") String username,
                                       @PathVariable("password") String password){

        //获取了用户名密码之后需要去service层处理业务逻辑

        ServerResponse serverResponse =  iUserService.login_back(username,password);
        //判断是否登录成功
        if (serverResponse.isSuccess()){
            UserInfo userInfo = (UserInfo)serverResponse.getData();
            session.setAttribute(Const.CURRENTUSER,userInfo);
            //生成autoLoginToken
            String ip = IpUtils.getRemoteAddress(request);
            try {
                String mac =  IpUtils.getMACAddress(ip);
                String token = MD5Utils.string2MD5(mac);
                //token 保存到数据库
                iUserService.savaTokenByUserId(userInfo.getId(),token);
                //  token 发送到客户端
                Cookie cookie = new Cookie(Const.AUTOLOGINTOKEN,token);
                cookie.setMaxAge(60*60*24*7);//将最大token存在时间设置为七天
                cookie.setPath("/");
                response.addCookie(cookie);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }


        return serverResponse;
    }

    /***
     * 检查管理员是否登录
     */
    @RequestMapping("/checkLogin")
    public  ServerResponse checkLogin(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.serverResponseByError("没有登录");
        }else if (userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError("不是管理员");
        }
        return ServerResponse.serverResponseBySuccess("管理员已经登录");
    }

    /***
     * 分页展示用户列表
     */
    @RequestMapping("/userList")
    public ServerResponse userList(Integer pageNum,Integer pageSize){
        return  iUserService.selectAllUser(pageNum,pageSize);
    }

    /***
     * 更改用户状态
     */
    @RequestMapping("/updateRole")
    public ServerResponse updateRole(Integer userId,Integer role){
        return iUserService.updateRole(userId,role);
    }
}
