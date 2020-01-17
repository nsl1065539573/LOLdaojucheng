package com.LOLdaojucheng.service.Impl;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.dao.ShoppingMapper;
import com.LOLdaojucheng.pojo.Shopping;
import com.LOLdaojucheng.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShippingServiceImpl implements IShippingService {
    @Autowired
    ShoppingMapper shoppingMapper;
    @Override
    public ServerResponse add_Shipping(Integer userId,Shopping shopping) {
        int check = shoppingMapper.checkJueSeName(shopping.getReceiverDistrict(),shopping.getReceiverAddress());
        if (check>=1){
            return  ServerResponse.serverResponseByError("该角色已经被绑定过了");
        }
        //添加地址会自动设置为默认地址，所以需要将上一个默认地址取消掉
        Shopping shopping1 = shoppingMapper.selectByUserId(userId);
        int res = shoppingMapper.revokeChecked(shopping1.getId());
        if (res<1){
            return ServerResponse.serverResponseByError("将默认地址取消失败");
        }
        Integer result = shoppingMapper.insert(shopping);
        if (result<1){
            return ServerResponse.serverResponseByError("添加地址失败");
        }else {
            return ServerResponse.serverResponseBySuccess("成功添加了收货地址");
        }
    }
    @Override
    public  ServerResponse selectById(Integer userId){
        Shopping shopping = shoppingMapper.selectByUserId(userId);
        if (shopping!=null){
            return  ServerResponse.serverResponseBySuccess(shopping);
        }else
            return ServerResponse.serverResponseByError("当前用户没有添加任何收货地址");
    }

    @Override
    public  ServerResponse deleteAddress(Integer userId,Integer id){
        Shopping shopping = shoppingMapper.selectByPrimaryKey(id);
        Integer result = shoppingMapper.deleteByPrimaryKey(id);
        if (result<1){
            return ServerResponse.serverResponseByError("删除该地址失败");
        }else {
            if (shopping.getChecked()==1){
                //删除了默认地址，将该用户的第一条收货地址设为默认地址
                Shopping shopping1 = shoppingMapper.getFirst(userId);
                shoppingMapper.checked(shopping1.getId());

            }
            return ServerResponse.serverResponseBySuccess("成功删除该地址");
        }
    }

    @Override
    public ServerResponse updateShipping(Shopping shipping){
        int check = shoppingMapper.checkJueSeName(shipping.getReceiverDistrict(),shipping.getReceiverAddress());
        if (check>=1){
            return  ServerResponse.serverResponseByError("该角色已经被绑定过了");
        }
        Integer result = shoppingMapper.updateByPrimaryKey(shipping);
        if (result<1){
            return  ServerResponse.serverResponseByError("更新地址信息失败");
        }else {
            return ServerResponse.serverResponseBySuccess("成功更新地址信息");
        }
    }

    @Override
    public ServerResponse selectAllByUserId(Integer userId){
        List<Shopping> shoppings = shoppingMapper.SelectAllByUserId(userId);
        return ServerResponse.serverResponseBySuccess(shoppings);
    }

    @Override
    public ServerResponse changeChecked(Integer userId,Integer id){
        //更改默认地址
        //首先将上一个默认地址设置为不默认
        Shopping shopping = shoppingMapper.selectByUserId(userId);
        int result = 0;
        if (shopping!=null){
            result = shoppingMapper.revokeChecked(shopping.getId()); if (result<1){
                return ServerResponse.serverResponseByError("更换默认地址失败");
            }

        }
        //将选中的地址设置为默认地址
        int result1 = shoppingMapper.checked(id);
        if (result1<1){
            return ServerResponse.serverResponseByError("更换您的默认地址失败了");
        }else {
            return ServerResponse.serverResponseBySuccess("成功更换了地址");
        }


    }
}
