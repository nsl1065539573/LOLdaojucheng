package com.LOLdaojucheng.service;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IproductService {
    /***
     * 增加或者更新商品
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /***
     * 更改商品状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse set_sale_status(Integer productId, Integer status);

    /***
     * 商品详情
     * @param productId
     * @return
     */
    ServerResponse detail(Integer productId);

    /***
     * 后台 --商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse list(Integer pageNum, Integer pageSize);

    /***
     * 后台--查询商品
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse serach(String productName, Integer productId, Integer pageNum, Integer pageSize);

    /***
     * 前台-- 获取商品详情
     * @param productId
     * @return
     */
    ServerResponse detail_portal(Integer productId);

    /***
     * 前台----商品搜索
     */
    ServerResponse list_protal(Integer categoryId,String keyword,Integer pageNum,Integer pageSize,String orderBy);
    /***
     * 函数重载---将所有商品搜索列表
     */
    ServerResponse list_protal(Integer pageNum,Integer pageSize,String OrderBy);
    /***
     * 后台-->图片上传
    */
    ServerResponse upload(MultipartFile file,String path);

    /***
     * 前台-->获得热门商品
     */
    ServerResponse hotProduct();

    /***
     * 前台-->获得打折商品
     */
    ServerResponse rebate();

    /***
     * 前台热门排行那一列
     */
    ServerResponse selectHot();
    /***
     * 前台搜索框模糊查询
     */
    ServerResponse mohuchaxun(String word);
    /***
     * 手办
     */
    ServerResponse shouban();

    /***
     * 更新商品数量
     */
    ServerResponse updateStock(Integer productId,Integer stock);
    /***
     * 更新商品描述
     */
    ServerResponse updateDetail(String detail,Integer productId);
}
