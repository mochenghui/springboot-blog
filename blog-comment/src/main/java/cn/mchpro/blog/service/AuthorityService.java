package cn.mchpro.blog.service;

import cn.mchpro.blog.domain.Authority;

/**
 * authority服务接口
 * @author Administrator
 *
 */
public interface AuthorityService {
	/**
	 * 根据id获取 Authority
	 * @param Authority
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
