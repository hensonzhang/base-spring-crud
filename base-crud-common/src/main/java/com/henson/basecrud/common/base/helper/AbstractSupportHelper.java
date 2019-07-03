package com.henson.basecrud.common.base.helper;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.henson.basecrud.common.base.dto.Search;
import com.henson.basecrud.common.exception.AppRunTimeException;
import com.henson.basecrud.common.util.Beans;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @Title: AbstractSupportHelper.java
 * @Package com.henson.basecrud.common.base.helper
 * @Description: 抽象帮助
 * @Author hensonzhang
 * @Email hensonzhangzs@gmail.com
 * @Date 2018年7月3日 下午2:47:02
 * @Version V1.0
 *
 */
public abstract class AbstractSupportHelper<TBL_PO, DTO> extends SupportHelper<TBL_PO, DTO> {

	public PageInfo<DTO> searchPage(Integer pageNum, Integer pageSize, String orderBy, DTO dto) {
		Page page = PageHelper.startPage(pageNum, pageSize, orderBy);
		mapper.select(Beans.newObj(dto, tblClass));
		PageInfo<DTO> pageInfo = new PageInfo<DTO>(page.getResult(), page.getPages());
		return pageInfo;
	}


	@Transactional(readOnly = false)
	public void save(DTO dto) throws AppRunTimeException {
		mapper.insert(Beans.newObj(dto, this.tblClass));
	}

	/**
	 * 查询
	 *
	 * @param qdto
	 * @return
	 */
	public List<DTO> query(DTO dto) {
		return query(dto, this.dtoClass);
	}

	public DTO get(DTO dto) {
		return get(dto, this.dtoClass);
	}

	public <T> T get(DTO dto, Class<T> reClass) {
		if (reClass == null) {
			return null;
		}
		return Beans.newObj(mapper.selectOne(Beans.newObj(dto, tblClass)), reClass);
	}

	/**
	 * 自定义查询，返回多列
	 * 
	 * @param qdto
	 * @param reClass
	 * @return
	 */

	public <T> List<T> query(DTO dto, Class<T> reClass) {
		if (reClass == null) {
			return null;
		}
		return Beans.newObj(mapper.select(Beans.newObj(dto, tblClass)), reClass);
	}

	/**
	 * 统计
	 * 
	 * @param qdto
	 * @return
	 */
	protected long count(DTO qdto) {
		return mapper.selectCount(Beans.newObj(qdto, tblClass));
	}

	/**
	 * 单表查询
	 *
	 * @param search
	 * @return
	 */
	public List<DTO> search(Search search) {
		return search(search, this.dtoClass);
	}

	/**
	 * 自定义条件查询；（单表）
	 * 
	 * @param search
	 * @param reClass
	 * @param <T>
	 * @return
	 */
	public <T> List<T> search(Search search, Class<T> reClass) {
		if (reClass == null) {
			return null;
		}
		Example example = buildExample(search);
		return Beans.newObj(mapper.selectByExample(example), reClass);
	}

	/**
	 * 构建请求
	 * 
	 * @param search
	 * @return
	 */
	protected Example buildExample(Search search) {
		if (search == null || CollectionUtils.isEmpty(search.getOperations())) {
			return null;
		}
		Example example = new Example(tblClass);
		Example.Criteria criteria = example.createCriteria();
		for (Search.Operation operation : search.getOperations()) {
			if (!StringUtils.isEmpty(operation.getValue())) {
				switch (operation.getOp()) {
				case EQ: {
					criteria.andEqualTo(operation.getFieldName(), operation.getValue());
				}
					break;
				case GT: {
					criteria.andGreaterThanOrEqualTo(operation.getFieldName(), operation.getValue());
				}
					break;
				case LT: {
					criteria.andLessThanOrEqualTo(operation.getFieldName(), operation.getValue());
				}
					break;
				case N_EQ: {
					criteria.andNotEqualTo(operation.getFieldName(), operation.getValue());
				}
					break;
				case LIKE: {
					criteria.andLike(operation.getFieldName(), "%" + operation.getValue().toString() + "%");
				}
					break;
				default: {
					criteria.andEqualTo(operation.getFieldName(), operation.getValue());
				}
				}
			}

		}
		return example;
	}

	/**
	 * 修改
	 */

	@Transactional(readOnly = false)
	public void update(DTO dto) throws AppRunTimeException {
		mapper.updateByPrimaryKeySelective(Beans.newObj(dto, this.tblClass));
	}

	/**
	 * 删除
	 * 
	 * @param qdto
	 */
	@Transactional(readOnly = false)
	public void delete(DTO dto) throws AppRunTimeException {
		mapper.delete(Beans.newObj(dto, this.tblClass));
	}
}
