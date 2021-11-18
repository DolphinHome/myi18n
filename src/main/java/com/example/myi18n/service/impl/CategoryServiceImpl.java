package com.example.myi18n.service.impl;

import com.example.myi18n.dao.CategoryMapper;
import com.example.myi18n.entity.Category;
import com.example.myi18n.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> selectList(){
        return categoryMapper.selectByExample(null);
    }


}
