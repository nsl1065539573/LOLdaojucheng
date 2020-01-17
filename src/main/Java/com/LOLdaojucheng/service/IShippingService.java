package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.Shopping;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

public interface IShippingService {
    /***
     * 添加收货地址
     */
    ServerResponse add_Shipping(Integer userId,Shopping shopping);

    /***
     * 查询已登录用户的选中的收货地址
     */
    ServerResponse selectById(Integer userId);

    /***
     * 删除收货地址
     */
    ServerResponse deleteAddress(Integer userId,Integer id);

    /***
     * 更改地址信息
     */
    ServerResponse updateShipping(Shopping shopping);
    /***
     * 查询已登录用户的所有地址信息
     */
    ServerResponse selectAllByUserId(Integer userId);
    /***
     * 将上一个默认地址改为不默认，并且使被选中的地址成为默认地址
     */
    ServerResponse changeChecked(Integer userId,Integer id);
}
