package com.example.myi18n.service.impl;

import com.example.myi18n.dao.ProductsMapper;
import com.example.myi18n.entity.Products;
import com.example.myi18n.entity.vo.ProductsVo;
import com.example.myi18n.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;


    public List<Products> selectList(){
        return productsMapper.selectByExample(null);
    }

    public ProductsVo getProductKeyId(Integer pid){
        return productsMapper.getKeyId(pid);
    }

}
