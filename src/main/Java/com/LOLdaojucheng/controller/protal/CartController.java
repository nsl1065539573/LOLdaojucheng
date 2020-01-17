package com.LOLdaojucheng.controller.protal;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    ICartService iCartService;

    /***
     * 购物车中添加商品
     * @param session
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping("/add.do")
    public ServerResponse add(HttpSession session,
                              Integer productId,
                              Integer count){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.add(userInfo.getId(),productId,count);
        return  serverResponse;
    }

    /***
     * 购物车中添加商品Restful
     * @param session
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping("/add.do/{productId}/{count}")
    public ServerResponse addRestful(HttpSession session,
                              @PathVariable("productId") Integer productId,
                              @PathVariable("count") Integer count){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.add(userInfo.getId(),productId,count);
        return  serverResponse;
    }

    /***
     * 购物车列表
     * @param session
     * @return
     */
    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.list(userInfo.getId());
        return  serverResponse;
    }

    /***
     * 更新购物车某个商品数量
     * @param session
     * @return
     */
    @RequestMapping("/update.do")
    public ServerResponse list(HttpSession session,Integer productId,Integer count){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.update(userInfo.getId(),productId,count);
        return  serverResponse;
    }
    /***
     * 更新购物车某个商品数量Restful
     * @param session
     * @return
     */
    @RequestMapping("/update/{productId}/{count}")
    public ServerResponse listRestful(HttpSession session,
                                      @PathVariable("productId") Integer productId,
                                      @PathVariable("count") Integer count){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.update(userInfo.getId(),productId,count);
        return  serverResponse;
    }
    /***
     * 移除某个商品
     */
    @RequestMapping("/delectProducts.do")
    public ServerResponse delectProducts(HttpSession session,String productIds){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.delectProducts(userInfo.getId(),productIds);
        return  serverResponse;
    }
    /***
     * 移除某个商品Restful
     */
    @RequestMapping("/delectProducts/{productIds}")
    public ServerResponse delectProductsRestful(HttpSession session,
                                                @PathVariable("productIds") String productIds){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.delectProducts(userInfo.getId(),productIds);
        return  serverResponse;
    }

    /***
     * 购物车中选中某个商品
     */
    @RequestMapping("/select.do")
    public ServerResponse select(HttpSession session,Integer productId){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select(userInfo.getId(),productId,Const.CART_CHECK.CATE_CHECKED.getCode());
        return  serverResponse;
    }
    /***
     * 购物车中选中某个商品Restful
     */
    @RequestMapping("/select/{productId}")
    public ServerResponse selectRestful(HttpSession session,
                                 @PathVariable("productId") Integer productId){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select(userInfo.getId(),productId,Const.CART_CHECK.CATE_CHECKED.getCode());
        return  serverResponse;
    }
    /***
     * 购物车取消选中商品
     */
    @RequestMapping("/un_select.do")
    public ServerResponse un_select(HttpSession session,Integer productId){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select(userInfo.getId(),productId,Const.CART_CHECK.CARE_UNCHECKED.getCode());
        return  serverResponse;
    }
    /***
     * 购物车取消选中商品Restful
     */
    @RequestMapping("/un_select/{productId}")
    public ServerResponse un_selectRestful(HttpSession session,
                                           @PathVariable("productId") Integer productId){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select(userInfo.getId(),productId,Const.CART_CHECK.CARE_UNCHECKED.getCode());
        return  serverResponse;
    }
    /***
     * 全选
     */
    @RequestMapping("/select_all.do")
    public ServerResponse select_all(HttpSession session){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select_all(userInfo.getId(),null,Const.CART_CHECK.CATE_CHECKED.getCode());
        return  serverResponse;
    }

    /***
     * 取消全选
     * @param session
     * @return
     */
    @RequestMapping("/un_select_all.do")
    public ServerResponse un_select_all(HttpSession session){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.select_all(userInfo.getId(),null,Const.CART_CHECK.CARE_UNCHECKED.getCode());
        return  serverResponse;
    }
    /***
     * 查询购物车中产品的数量
     */
    @RequestMapping("/get_cart_product_count.do")
    public ServerResponse get_cart_product_count(HttpSession session){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        ServerResponse serverResponse = iCartService.get_cart_product_count(userInfo.getId());
        return  serverResponse;
    }
    /***
     * 查询登录用户的点击商品的购物车中数量是否大于一
     */
    @RequestMapping("/get_quanti")
    public ServerResponse get_quanti(HttpSession session,Integer productId){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return  ServerResponse.serverResponseByError("请先登录");
        }
        Integer userId = userInfo.getId();
        ServerResponse serverResponse = iCartService.get_quanti(userId,productId);
        return serverResponse;
    }
    /***
     * 查询已登录用户购物车中数量
     */
    @RequestMapping("/get_count")
    public ServerResponse get_count(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        Integer userId = userInfo.getId();
        ServerResponse serverResponse = iCartService.get_count(userId);
        return serverResponse;
    }

    /***
     * 增加购物车中商品的数量
     */
    /***
     * 减少购物车中商品的数量
     */
}
