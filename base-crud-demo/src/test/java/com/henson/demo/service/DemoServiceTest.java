package com.henson.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.henson.basecrud.common.base.dto.Search;
import com.henson.basecrud.common.base.dto.Search.Operation;
import com.henson.basecrud.common.base.enums.OpEnum;
import com.henson.demo.dto.DemoDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:demo/applicationContext.xml")
public class DemoServiceTest {

	@Autowired
	DemoService demoService;

	@Ignore
	@Test
	public void save() {
		DemoDTO demo = new DemoDTO();
		demo.setName("jack");
		demo.setId(4);
		demoService.save(demo);
	}

	@Ignore
	@Test
	public void update() {
		DemoDTO demo = new DemoDTO();
		demo.setName("jack4");
		demo.setId(4);
		demoService.update(demo);
	}

	@Ignore
	@Test
	public void get() {
		DemoDTO demo = new DemoDTO();
		demo.setId(1);
		System.out.println(demoService.get(demo).getName());
	}


	@Test
	public void query() {
		DemoDTO demo = new DemoDTO();
		demo.setName("aaa");
		System.out.println(JSON.toJSON(demoService.query(demo)));
	}

	@Ignore
	@Test
	public void search() {
		DemoDTO demo = new DemoDTO();
		demo.setName("jack");
		Search search = new Search();
		Operation oName = new Operation();
		oName.setFieldName("name");
		oName.setOp(OpEnum.EQ);
		oName.setValue("jack");
		List<Operation> operations = new ArrayList<>();
		operations.add(oName);
		search.setOperations(operations);
		System.out.println(JSON.toJSON(demoService.search(search)));
	}

	@Ignore
	@Test
	public void page() {
		DemoDTO demo = new DemoDTO();
		demo.setName("jack");
		System.out.println(JSON.toJSON(demoService.searchPage(1, 2, null, demo)));
	}

	@Ignore
	@Test
	public void delete() {
		DemoDTO demo = new DemoDTO();
		demo.setName("jack");
		demoService.delete(demo);
	}

}
