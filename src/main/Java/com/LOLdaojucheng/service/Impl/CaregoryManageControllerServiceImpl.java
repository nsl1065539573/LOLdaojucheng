package com.LOLdaojucheng.service.Impl;

import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.dao.CategoryMapper;
import com.LOLdaojucheng.pojo.Category;
import com.LOLdaojucheng.service.CategoryManageControllerService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CaregoryManageControllerServiceImpl implements CategoryManageControllerService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public ServerResponse get_category(Integer categoryid) {
        //非空校验
        if (categoryid==null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        Category category =  categoryMapper.selectByPrimaryKey(categoryid);
        if (category==null){
            return  ServerResponse.serverResponseByError("该编号的商品不存在");
        }
        List<Category> list = categoryMapper.select_catgory(categoryid);
        ServerResponse serverResponse = ServerResponse.serverResponseBySuccess(list);
        return serverResponse;
    }

    @Override
    public ServerResponse add_category(Integer parentId, String categoryName) {
        //非空校验
        if (categoryName==null||categoryName.equals("")){
            return ServerResponse.serverResponseByError("不能不设置品类节点名");
        }
        //添加节点
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(categoryName);
        category.setStatus(1);
        int result = categoryMapper.insert(category);
        if (result==0){
            return  ServerResponse.serverResponseByError("添加失败");
        }
        return ServerResponse.serverResponseBySuccess("添加成功");
    }

    @Override
    public ServerResponse change_category(Integer categorytId, String categoryName) {
        //参数非空校验
        if (categorytId ==null){
            return  ServerResponse.serverResponseByError("品类id不能为空");
        }
        if (categoryName == null){
            return  ServerResponse.serverResponseByError("品类名称不能为空");
        }
        //根据categoryId查询品类
        Category category = new Category();
        category = categoryMapper.selectByPrimaryKey(categorytId);

        if (category==null){
            return ServerResponse.serverResponseByError("品类id输入错误");
        }

        //更新品类名

        category.setName(categoryName);
        int result = categoryMapper.updateByPrimaryKey(category);
        if (result>0){
            return  ServerResponse.serverResponseBySuccess("修改成功");
        }
        return ServerResponse.serverResponseByError("修改失败");
    }

    @Override
    public ServerResponse get_every_category(Integer categorytId) {
        //参数的非空校验
        if (categorytId==null){
            return  ServerResponse.serverResponseByError("参数为空");
        }
        //查询
        Set<Category> set = Sets.newHashSet();
        set = findAllChildCategory(set,categorytId);
        Set<Integer> IntegerSet = Sets.newHashSet();
        Iterator<Category> iterator = set.iterator();
        while (iterator.hasNext()){
            Category category = iterator.next();
            IntegerSet.add(category.getId());
        }
        return ServerResponse.serverResponseBySuccess(IntegerSet);
    }
    private Set<Category> findAllChildCategory(Set<Category> set,Integer categroyId){
        Category category =  categoryMapper.selectByPrimaryKey(categroyId);
        if (category!=null){
            set.add(category);
        }
        //查找categoryId下的所有子类
        List<Category> list = categoryMapper.findChildCategory(categroyId);
        if (list!=null&&list.size()>0){
            for (Category category1:list){
                findAllChildCategory(set,category1.getId());
            }
        }
        return set;
    }
    public ServerResponse selectAll(){
        List<Category> list = categoryMapper.selectAll();
        if(list.size()>=0) {
            ServerResponse serverResponse = ServerResponse.serverResponseBySuccess(list);
            return serverResponse;
        }else {
            ServerResponse serverResponse = ServerResponse.serverResponseByError("服务器出错");
            return serverResponse;
        }

    }
}
