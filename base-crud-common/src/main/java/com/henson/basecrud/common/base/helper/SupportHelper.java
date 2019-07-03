package com.henson.basecrud.common.base.helper;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.henson.basecrud.common.base.BaseMapper;
import com.henson.basecrud.common.base.helper.id.IdGenerator;
import com.henson.basecrud.common.base.service.ServiceApi;

/**
 * 
 * @Title: SupportHelper.java
 * @Package com.henson.basecrud.common.base.helper
 * @Description: 抽象帮助，负责实例化tblclss,dtoclass
 * @Author hensonzhang
 * @Email hensonzhangzs@gmail.com
 * @Date 2018年7月3日 下午3:27:09
 * @Version V1.0
 *
 */
public abstract class SupportHelper<TBL_PO, DTO> extends IdGenerator<DTO> implements ServiceApi<DTO> {

	private static final Logger logger = LoggerFactory.getLogger(SupportHelper.class);
	
	protected Class<TBL_PO> tblClass;
	protected Class<DTO> dtoClass;

	@Autowired
	protected BaseMapper<TBL_PO> mapper;

	public SupportHelper() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.tblClass = (Class<TBL_PO>) type.getActualTypeArguments()[0];
		this.dtoClass = (Class<DTO>) type.getActualTypeArguments()[1];
		logger.debug("Implementation class initialization.Class name is {} and DTO {}", tblClass, dtoClass);
	}

}
