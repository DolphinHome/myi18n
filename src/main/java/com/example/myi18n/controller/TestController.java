package com.example.myi18n.controller;

import com.example.myi18n.common.base.BaseController;
import com.example.myi18n.common.base.ResultVO;
import com.example.myi18n.entity.Category;
import com.example.myi18n.entity.Products;
import com.example.myi18n.entity.vo.ProductsVo;
import com.example.myi18n.service.CategoryService;
import com.example.myi18n.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Test")
public class TestController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductsService productsService;

    @RequestMapping("test")
    public ResultVO insertCategory(){
        List<Products> list = new ArrayList<>();
        Products products1 =new Products(1,"${category.99999}",1.2,"1",1);
        Products products2 =new Products(1,"${category.88888}",1.2,"1",1);
        list.add(products1);
        list.add(products2);
        return new ResultVO(list);
    }

    @RequestMapping("getCategoryList")
    public ResultVO getCategoryList(){
        List<Category> categories = categoryService.selectList();
        return new ResultVO(categories);
    }

    @RequestMapping("getProductsList")
    public ResultVO getProductsList(){
        List<Products> products = productsService.selectList();
        return new ResultVO(products);
    }


    @RequestMapping("getProductsKey")
    public ResultVO getProductsKey(Integer pid){
        ProductsVo products = productsService.getProductKeyId(pid);
        return new ResultVO(products);
    }


    @RequestMapping("exception")
    public ResultVO exception(){
        //异常处理国际化问题
        return new ResultVO(500,"${exception.@1}",null);
    }







}
