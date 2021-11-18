package com.example.myi18n.service;

import com.example.myi18n.entity.Products;
import com.example.myi18n.entity.vo.ProductsVo;

import java.util.List;

public interface ProductsService {
     List<Products> selectList();

     ProductsVo getProductKeyId(Integer pid);
}
