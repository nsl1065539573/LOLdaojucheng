package com.LOLdaojucheng.service.Impl;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.dao.CartMapper;
import com.LOLdaojucheng.dao.ProductMapper;
import com.LOLdaojucheng.pojo.Cart;
import com.LOLdaojucheng.pojo.Product;
import com.LOLdaojucheng.service.ICartService;
import com.LOLdaojucheng.utils.BigDeccimalUtils;
import com.LOLdaojucheng.vo.CartProductVO;
import com.LOLdaojucheng.vo.CartVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    /***
     * 购物车添加商品
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    @Override
    public ServerResponse add(Integer userId, Integer productId, Integer count) {
        //参数的非空校验
        if (productId==null||count==null){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        Product product =productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.serverResponseByError("商品不存在");
        }
        if (product.getStatus()==Const.Product_state.STATE_OFFLINE.getCode()||product.getStatus()==Const.Product_state.STATE_DELECT.getCode()){
            return ServerResponse.serverResponseByError("商品已下架或者被删除");
        }
        //根据用户ID查询购物车
        Cart cart = cartMapper.selectByUserIdAndProductId(userId,productId);
        if (cart==null){//往购物车里添加商品
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(count);
            cart1.setChecked(Const.CART_CHECK.CATE_CHECKED.getCode());
            cartMapper.insert(cart1);
        }else {//在购物车里更新商品
            Cart cart1 = new Cart();
            cart1.setId(cart.getId());
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(count);
            cart1.setChecked(cart.getChecked());
            cartMapper.updateByPrimaryKey(cart1);
        }
        CartVO cartVO = getCartVOlimit(userId);
        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    /***
     * 购物车列表
     * @return
     */
    @Override
    public ServerResponse list(Integer userId) {
        CartVO cartVO = getCartVOlimit(userId);
         if (cartVO.getCartProductVOList()==null){
             cartVO.setAllChecked(false);
         }

        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    /***
     * 更新商品数量
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer count) {
        //参数判定
        if (productId==null||count==null){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        //查询购物车中商品 根据用户id以及商品id
        Cart cart = cartMapper.selectByUserIdAndProductId(userId,productId);
        if (cart!=null){
            //更新数量
            cart.setQuantity(count);
            cart.setUserId(userId);
            cart.setProductId(productId);
//            boolean a = cart.getQuantity()==count;
//            System.out.print(a);
            cartMapper.updateByPrimaryKey(cart);
        }
        //返回
        return ServerResponse.serverResponseBySuccess(getCartVOlimit(userId));
    }

    @Override
    public ServerResponse delectProducts(Integer userId, String productIds) {
        //参数判定
        if (productIds==null||productIds.equals("")){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        //products-->List<Integer>
        List<Integer> productIdList = Lists.newArrayList();
        String[] productIdsArr = productIds.split(",");
        if (productIdsArr!=null&&productIdsArr.length>0){
            for (String productIdstr:
                 productIdsArr) {
                Integer productId = Integer.parseInt(productIdstr);
                productIdList.add(productId);
            }
        }
        //调用Dao
        cartMapper.delectByUserIdAndProductIdList(userId,productIdList);
        return ServerResponse.serverResponseBySuccess(getCartVOlimit(userId));
    }

    /***
     * 选中某个商品

     */
    @Override
    public ServerResponse select(Integer userId, Integer productId,Integer check) {
        //参数非空校验
        if (productId==null){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        //调用Dao接口
        cartMapper.selectOrUnSelectProduct(userId,productId,check);
        //返回结果

        return ServerResponse.serverResponseBySuccess(getCartVOlimit(userId));
    }

    /***
     * 查询购物车中产品的数量
     * @param id
     * @return
     */
    @Override
    public ServerResponse get_cart_product_count(Integer id) {
        int quantity = cartMapper.get_cart_product_count(id);
        return ServerResponse.serverResponseBySuccess(quantity);
    }

    @Override
    public ServerResponse get_quanti(Integer userId,Integer productId) {
        Integer quantity  =  cartMapper.get_quanti(userId,productId);
        if (quantity==null||quantity<1){
            return ServerResponse.serverResponseBySuccess("数量不大于一");
        }else {
            return  ServerResponse.serverResponseByError("不能多于一");
        }

    }

    @Override
    public ServerResponse select_all(Integer userId, Integer productId, Integer check) {
        //调用Dao接口
        cartMapper.selectOrUnSelectProduct(userId,productId,check);
        //返回结果

        return ServerResponse.serverResponseBySuccess(getCartVOlimit(userId));
    }

    @Override
    public ServerResponse get_count(Integer userId) {
        Integer count = cartMapper.get_count(userId);
        return  ServerResponse.serverResponseBySuccess(count);
    }

    private CartVO getCartVOlimit(Integer userId){
        //
        CartVO cartVO = new CartVO();
        //根据userId查询购物信息
        List<Cart> cartList = cartMapper.selectByUserId(userId);
        //List<Cart>-->List<CartProductVO>
        List<CartProductVO> cartProductVOList = Lists.newArrayList();
        //购物车总价格
        BigDecimal carttotalprice = new BigDecimal(0);
        if (cartList!=null&&cartList.size()>0){
            for (Cart cart:
                 cartList) {
                CartProductVO cartProductVO = new CartProductVO();
                cartProductVO.setId(cart.getId());
                cartProductVO.setProductChecked(cart.getChecked());
                cartProductVO.setUserId(cart.getUserId());
                cartProductVO.setQuantity(cart.getQuantity());
                //查询商品
                Product product = productMapper.selectByPrimaryKey(cart.getProductId());
                if (product != null) {
                    cartProductVO.setProductId(cart.getProductId());
                    cartProductVO.setProductMainImage(product.getMainImage());
                    cartProductVO.setProductSubImages(product.getSubImages());
                    cartProductVO.setProductSubtitle(product.getSubtitle());
                    cartProductVO.setProductCategoryId(product.getCategoryId());
                    cartProductVO.setRebate(product.getRebate());
                    cartProductVO.setProductName(product.getName());
                    cartProductVO.setProductPrice(product.getPrice());
                    cartProductVO.setProductStatus(product.getStatus());
                    cartProductVO.setProductStock(product.getStock());

                    int stock = product.getStock();
                    int limitProductCount = 0;
                    if (stock > cart.getQuantity()) {
                        limitProductCount = cart.getQuantity();
                        cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
                    } else {//商品库存不足
                        limitProductCount = stock;
                        //更新购物车中商品的数量
                        Cart cart1 = new Cart();
                        cart1.setId(cart.getId());
                        cart1.setQuantity(stock);
                        cart1.setProductId(cart.getProductId());
                        cart1.setChecked(cart.getChecked());
                        cart1.setUserId(cart.getUserId());
                        cartMapper.updateByPrimaryKey(cart1);
                        cartProductVO.setLimitQuantity("LIMIT_NUM_FAIL");

                    }
                    cartProductVO.setQuantity(limitProductCount);
                    cartProductVO.setProductTotalPrice(BigDeccimalUtils.mul(product.getPrice().doubleValue(), cartProductVO.getQuantity().doubleValue()));
                }
                carttotalprice = BigDeccimalUtils.add(carttotalprice.doubleValue(),cartProductVO.getProductPrice().doubleValue());
                cartProductVOList.add(cartProductVO);
            }

        }
        cartVO.setCartProductVOList(cartProductVOList);
        //计算总价格
        cartVO.setCarttotalprice(carttotalprice);
        //判断购物车是否全选
        int count = cartMapper.isCheckedAll(userId);

        if (count>0||cartMapper.get_cart_product_count(userId)==0){
            cartVO.setAllChecked(false);
        }else {
            cartVO.setAllChecked(true);
        }
        return cartVO;
    }
}
