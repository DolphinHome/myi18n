package com.example.myi18n.service.impl;

import com.example.myi18n.dao.I18nAllocateMapper;
import com.example.myi18n.entity.I18nAllocate;
import com.example.myi18n.service.I18nAllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class I18nAllocateServiceImpl implements I18nAllocateService {
    @Autowired
    private I18nAllocateMapper i18nAllocateMapper;
    @Autowired
    private MapSessionMapperImpl mapSessionMapper;

    @Override
    public Map<String,Object> buildLangToWeb() {
        Map<String, Object> map = mapSessionMapper.selectCountryMap();
        return  map;
    }

    @Override
    public List<I18nAllocate> buildLangToJava() {
        return i18nAllocateMapper.buildLangToJava();
    }
}
