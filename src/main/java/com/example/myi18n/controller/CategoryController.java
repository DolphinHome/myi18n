package com.example.myi18n.controller;

import com.example.myi18n.common.base.BaseController;
import com.example.myi18n.common.base.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("Category")
public class CategoryController  extends BaseController {

    @RequestMapping("test")
    public ResultVO insertCategory(){
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        return new ResultVO(map);
    }


}
