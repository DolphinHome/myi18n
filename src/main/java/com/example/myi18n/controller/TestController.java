package com.example.myi18n.controller;

import com.example.myi18n.common.base.BaseController;
import com.example.myi18n.common.base.I18nTemplateContainer;
import com.example.myi18n.common.base.ResultVO;
import com.example.myi18n.common.enums.ExceptionEnums;
import com.example.myi18n.entity.Category;
import com.example.myi18n.entity.I18nAllocate;
import com.example.myi18n.entity.Products;
import com.example.myi18n.entity.vo.ProductsVo;
import com.example.myi18n.service.CategoryService;
import com.example.myi18n.service.I18nAllocateService;
import com.example.myi18n.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("Test")
public class TestController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private I18nTemplateContainer i18nTemplateContainer;
    @Autowired
    private I18nAllocateService i18nAllocateService;

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
        return new ResultVO(500, ExceptionEnums.SERVER_EXCEPTION,null);
    }


    @RequestMapping("response")
    public String response(){
        return ExceptionEnums.SERVER_EXCEPTION.getSign();
    }

    @RequestMapping("exceptionMsg")
    public String exceptionMsg()  {
        categoryService.exceptionMsg();
        return "错了错了";
    }

    @RequestMapping("getI18nBag")
    public ResultVO getI18nBag(String key){
        String value = i18nTemplateContainer.getValue(key);
        return ResultVO.success(value);
    }

    @RequestMapping("getMobelBag")
    public ResultVO getMobelBag(String model){
        List<I18nAllocate> mobelBag = i18nAllocateService.getMobelBag(model);
        return ResultVO.success(mobelBag);
    }

}
