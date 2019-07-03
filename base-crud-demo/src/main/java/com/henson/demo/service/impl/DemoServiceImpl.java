package com.henson.demo.service.impl;

import org.springframework.stereotype.Service;

import com.henson.basecrud.common.base.service.ServiceHelper;
import com.henson.demo.dto.DemoDTO;
import com.henson.demo.model.Demo;
import com.henson.demo.service.DemoService;


@Service
public class DemoServiceImpl extends ServiceHelper<Demo, DemoDTO> implements DemoService {

}
