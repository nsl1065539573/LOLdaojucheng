package com.LOLdaojucheng.service.Impl;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.dao.CategoryMapper;
import com.LOLdaojucheng.dao.ProductMapper;
import com.LOLdaojucheng.pojo.Category;
import com.LOLdaojucheng.pojo.Product;
import com.LOLdaojucheng.service.CategoryManageControllerService;
import com.LOLdaojucheng.service.IproductService;
import com.LOLdaojucheng.utils.DateUtils;
import com.LOLdaojucheng.utils.PropertiesUtils;
import com.LOLdaojucheng.vo.ProductDetailVO;
import com.LOLdaojucheng.vo.ProductVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


@Service
public class IproductServiceImpl implements IproductService {
@Autowired
    ProductMapper productMapper;
@Autowired
    CategoryMapper categoryMapper;
@Autowired
    CategoryManageControllerService categoryManageControllerService;

    @Override
    /***
     * 添加或更新商品
     */
    public ServerResponse saveOrUpdateProduct(Product product) {
        //进行参数的非空校验
        if (product==null){
            return  ServerResponse.serverResponseByError("参数必须");
        }
        //设置商品主图
        String subImages = product.getSubImages();
        if (subImages!=null&&!subImages.equals("")){
            String[] subImagesArr = subImages.split(",");
            if (subImagesArr.length>0){
                product.setMainImage(subImagesArr[0]);
            }
        }
        //添加或者更新
        if (product.getId()==null){//添加
            int result = productMapper.insert(product);
            if (result>0){
                return ServerResponse.serverResponseBySuccess("添加成功");
            }else {
                return  ServerResponse.serverResponseByError("添加失败");
            }
        }else {//更新
            BigDecimal price = product.getPrice();
            Integer rebate = product.getRebate();
            Integer status = product.getStatus();
            int result = productMapper.updateProduct(product.getId(),rebate,price,status);
            if (result>0){
                return  ServerResponse.serverResponseBySuccess("更新成功");
            }else {
                return  ServerResponse.serverResponseByError("更新失败");
            }

        }

    }



    /***
     * 增加或更新商品
     * @param productId
     * @param status
     * @return
     */
    @Override
    public ServerResponse set_sale_status(Integer productId, Integer status) {
        //进行参数的非空校验
        if (productId==null){
            return  ServerResponse.serverResponseByError("商品id为空");
        }
        if (status==null){
            return  ServerResponse.serverResponseByError("商品状态为空");
        }
        //进行status的更改
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int result = productMapper.set_sale_status(product);
        if (result>0){
            return  ServerResponse.serverResponseBySuccess("更新成功");
        }
        return ServerResponse.serverResponseByError("添加失败");
    }

    @Override
    public ServerResponse detail(Integer productId) {
        //非空校验
        if (productId==null){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        //查询product
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return  ServerResponse.serverResponseByError("商品不存在");
        }
        //product-->productDetailVO
        ProductDetailVO productDetailVO = assembleProductDetailVO(product);
        return ServerResponse.serverResponseBySuccess(productDetailVO);
    }
    private ProductDetailVO assembleProductDetailVO(Product product) {

        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setRebate(product.getRebate());
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setCreateTime(DateUtils.DateToString(product.getCreateTime()));
        productDetailVO.setUpdateTime(DateUtils.DateToString(product.getUpdateTime()));
        productDetailVO.setImageHost(PropertiesUtils.readByKey("imageHost"));
        productDetailVO.setName(product.getName());
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setId(product.getId());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setStock(product.getStock());
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category != null) {
            productDetailVO.setParentCategoryId(category.getParentId());
        } else {
            productDetailVO.setParentCategoryId(0);
        }

        return productDetailVO;
    }
    /***
     * 后台--分页查询商品列表
     */
    @Override
    public  ServerResponse list(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> list = productMapper.selectAll();
        PageInfo<Product> productPageInfo = new PageInfo<>(list);
        System.out.println(productPageInfo.isIsLastPage());
        List<ProductVO> productVOlist = Lists.newArrayList();
        if (list!=null){
            for (Product product:
                 list) {
                        ProductVO productVO = productToProductVO(product);
                        productVOlist.add(productVO);
            }
        }
        PageInfo<ProductVO> pageInfo = new PageInfo<>(productVOlist);
        boolean isFirstPage = productPageInfo.isIsFirstPage();
        boolean isLastPage = productPageInfo.isIsLastPage();
        boolean hasNextPage = productPageInfo.isHasNextPage();
        long total = productPageInfo.getTotal();
        pageInfo.setTotal(total);
        pageInfo.setIsFirstPage(isFirstPage);
        pageInfo.setIsLastPage(isLastPage);
        pageInfo.setHasNextPage(hasNextPage);

        return  ServerResponse.serverResponseBySucess(productPageInfo,"查询成功");
    }
    private ProductVO productToProductVO(Product product) {
        ProductVO productVO = new ProductVO();
        productVO.setId(product.getId());
        productVO.setCategoryId(product.getId());
        productVO.setMainImage(product.getMainImage());
        productVO.setName(product.getName());
        productVO.setSubtitle(product.getSubtitle());
        productVO.setPrice(product.getPrice());
        return productVO;
    }

    /***
     * 根据商品Id Name 查询
     */
    @Override
    public ServerResponse serach(String productName,Integer productId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if (productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
        }
        List<Product> list = productMapper.search(productName,productId);
        PageInfo<Product> productPageInfo = new PageInfo<>(list);
        List<ProductVO> productVOList = Lists.newArrayList();
        if (list!=null) {
            for (Product product:
             list){
                ProductVO productVO = productToProductVO(product);
                productVOList.add(productVO);
            }

        }
        PageInfo<ProductVO> productVOPageInfo = new PageInfo<>(productVOList);
        long total = productPageInfo.getTotal();

        return  ServerResponse.serverResponseBySucess(productVOPageInfo,"成功");
    }

    @Override
    public ServerResponse detail_portal(Integer productId){
        //参数的非空校验
        if (productId==null){
            return ServerResponse.serverResponseByError("参数为空");
        }
        //查询product
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.serverResponseByError("商品不存在");
        }
        //查询商品状态
        if(product.getStatus()==Const.Product_state.STATE_OFFLINE.getCode()){
            return  ServerResponse.serverResponseByError("商品已下架");
        }
        if (product.getStatus()==Const.Product_state.STATE_DELECT.getCode()){
            return  ServerResponse.serverResponseByError("商品已被删除");
        }
        //获取productDetailVO
        ProductDetailVO productDetailVO = assembleProductDetailVO(product);
        return  ServerResponse.serverResponseBySucess(productDetailVO,"查询成功");
    }

    /***
     * 前台--商品搜索
     * @param categoryId
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @Override
    public ServerResponse list_protal(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        //参数校验  categoryId & keyword 不能同时为空
        if (categoryId==null&&(keyword==null||keyword.equals(""))){
            return ServerResponse.serverResponseByError("参数不能全是空");
        }
        //categoryId
        Set<Integer> set = Sets.newHashSet();
        if (categoryId!=null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if (category==null&&(keyword==null||keyword.equals(""))){
                //说明没有商品数据
                PageHelper.startPage(pageNum,pageSize);
                List<ProductVO> productVOList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productVOList);
                return ServerResponse.serverResponseBySucess(pageInfo,"查询出为空");
            }
        }
        ServerResponse serverResponse = categoryManageControllerService.get_every_category(categoryId);

        if (serverResponse.isSuccess()){
            set = (Set<Integer>) serverResponse.getData();
        }
        if (keyword!=null&&!keyword.equals("")){
            keyword="%"+keyword+"%";
        }
        if (orderBy.equals("")){
            PageHelper.startPage(pageNum,pageSize);
        }else {
            String[] orderByArr = orderBy.split("_");
            if (orderByArr.length>1){
                PageHelper.startPage(pageNum,pageSize,orderByArr[0]+""+orderByArr[1]);
            }else {
                PageHelper.startPage(pageNum,pageSize);
            }
        }
        List<Product> productList = productMapper.findProduct(set,keyword);
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        List<ProductVO> productVOList = Lists.newArrayList();
        if (productList!=null&&productList.size()>0){
            for (Product product:
                 productList) {
                ProductVO productVO = productToProductVO(product);
                productVOList.add(productVO);
            }
        }
        PageInfo pageInfo = new PageInfo(productVOList);
        boolean hasPreviousPage = productPageInfo.isHasPreviousPage();
        boolean isFirstPage = productPageInfo.isIsFirstPage();
        boolean isLastPage = productPageInfo.isIsLastPage();
        boolean hasNextPage = productPageInfo.isHasNextPage();
        long total = productPageInfo.getTotal();
        int pages = productPageInfo.getPages();
        pageInfo.setPages(pages);
        pageInfo.setHasPreviousPage(hasPreviousPage);
        pageInfo.setTotal(total);
        pageInfo.setIsFirstPage(isFirstPage);
        pageInfo.setIsLastPage(isLastPage);
        pageInfo.setHasNextPage(hasNextPage);
        return  ServerResponse.serverResponseBySucess(pageInfo,"成功");
    }

    @Override
    public ServerResponse list_protal(Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectAll();
            PageInfo<Product> productPageInfo = new PageInfo<>(productList);
            List<ProductVO> productVOList = Lists.newArrayList();
            if (productList!=null&&productList.size()>0){
                for (Product product:
                        productList) {
                    ProductVO productVO = productToProductVO(product);
                    productVOList.add(productVO);
                }
            }
            PageInfo pageInfo = new PageInfo(productVOList);
            int pagenum = productPageInfo.getPageNum();
            boolean hasPreviousPage = productPageInfo.isHasPreviousPage();
            boolean isFirstPage = productPageInfo.isIsFirstPage();
            boolean isLastPage = productPageInfo.isIsLastPage();
            boolean hasNextPage = productPageInfo.isHasNextPage();
            long total = productPageInfo.getTotal();
            int pages = productPageInfo.getPages();
            pageInfo.setPages(pages);
            pageInfo.setHasPreviousPage(hasPreviousPage);
            pageInfo.setPageNum(pagenum);
            pageInfo.setTotal(total);
            pageInfo.setIsFirstPage(isFirstPage);
            pageInfo.setIsLastPage(isLastPage);
            pageInfo.setHasNextPage(hasNextPage);
            return  ServerResponse.serverResponseBySucess(pageInfo,"成功");


    }

    /***
     * 图片上传
     * @param file
     * @param path
     * @return
     */
    @Override
    public ServerResponse upload(MultipartFile file,String path){
        //非空校验
        if (file==null){
            return ServerResponse.serverResponseByError("图片为空");
        }
        //获取图片名称
        String originalFileName = file.getOriginalFilename();
        //获取图片扩展名
        String exName = originalFileName.substring(originalFileName.lastIndexOf("."));
        //生成图片新名字
        String fileNewName = UUID.randomUUID().toString()+exName;
        File pathFile = new File(path);
        if (pathFile.exists()){
            pathFile.setWritable(true);
            pathFile.mkdirs();
        }
        File file1 = new File(path,fileNewName);
        try {
            file.transferTo(file1);
            //将图片上传到图片服务器
            Map<String,String> map = Maps.newHashMap();
            map.put("uri",fileNewName);
            map.put("url",PropertiesUtils.readByKey("imageHost")+"/"+fileNewName);
            return ServerResponse.serverResponseBySuccess(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServerResponse hotProduct() {
        List<Product> list = productMapper.selectHotProduct(Const.Product_state.STATE_HOTPRODUCT.getCode());
        ServerResponse serverResponse = ServerResponse.serverResponseBySuccess(list);
        return serverResponse;
    }

    @Override
    public  ServerResponse rebate(){
        List<Product> list = productMapper.selectRebateProduct(Const.Product_state.STATE_REBATEPRODUCT.getCode());
        return ServerResponse.serverResponseBySuccess(list);
    }
    @Override
    public ServerResponse selectHot(){
        List<Product> list = productMapper.selectHot(Const.Product_state.STATE_HOT.getCode());
        return ServerResponse.serverResponseBySuccess(list);
    }
    @Override
    public  ServerResponse mohuchaxun(String word){
        List<Product> list = productMapper.mohuchaxun(word);
        return ServerResponse.serverResponseBySuccess(list);
    }

    /***
     * 手办
     */
    public  ServerResponse shouban(){
        List<Product> list = productMapper.shouban(Const.Product_state.STATE_DELECT.getCode(),
                                                    Const.Product_state.STATE_OFFLINE.getCode(),
                                                    Const.Category_Id.STATE_SHOUBAN.getCode());
        ServerResponse serverResponse = ServerResponse.serverResponseBySuccess(list);
        return  serverResponse;
    }

    @Override
    public ServerResponse updateStock(Integer productId, Integer stock) {
        int result = productMapper.updateStock(productId,stock);
        if (result>=1){
            return ServerResponse.serverResponseBySuccess("更新成功");
        }
        return ServerResponse.serverResponseByError("更新失败");
    }

    @Override
    public ServerResponse updateDetail(String detail, Integer productId) {
        int result = productMapper.updateDetails(productId,detail);
        if (result>=1){
            return ServerResponse.serverResponseBySuccess("更新成功");
        }
        return ServerResponse.serverResponseByError("更新失败");
    }
}
