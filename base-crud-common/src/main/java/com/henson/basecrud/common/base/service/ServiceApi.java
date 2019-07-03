package com.henson.basecrud.common.base.service;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.henson.basecrud.common.base.dto.Search;
import com.henson.basecrud.common.exception.AppRunTimeException;

/**
 * 
 * @Title: ServiceApi.java
 * @Package com.henson.basecrud.common.base.service
 * @Description: 方法定义，可自行进行扩展，目前只有单表支持(为了让业务接口能得到定义的方法，适配模式的意思）
 * @Author hensonzhang
 * @Email hensonzhangzs@gmail.com
 * @Date 2018年7月6日 下午8:50:29
 * @Version V1.0
 *
 */
public interface ServiceApi<DTO> extends Serializable{

	/**
	 * dto条件查询（依靠tkMapper自动构建）
	 * 
	 * @param qdto
	 * @return
	 */
	List<DTO> query(DTO dto);

	/**
	 * dto条件查询
	 * 
	 * @param qdto
	 * @return
	 */
	DTO get(DTO dto);

	/**
	 * search条件查询（依靠Example进行构建）
	 * @param search
	 * @return
	 */
	List<DTO> search(Search search);

	/**
	 * 添加
	 * @param qdto
	 */
	void save(DTO dto) throws AppRunTimeException;

	/**
	 * 单表修改
	 * 
	 * @param qdto
	 */
	void update(DTO dto) throws AppRunTimeException;

	/**
	 * 删除
	 * 
	 * @param qdto
	 */
	void delete(DTO dto) throws AppRunTimeException;

	PageInfo<DTO> searchPage(Integer pageNum, Integer pageSize, String orderBy, DTO dto);

}
