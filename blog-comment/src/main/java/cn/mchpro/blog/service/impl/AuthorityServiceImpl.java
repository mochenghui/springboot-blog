package cn.mchpro.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.Authority;
import cn.mchpro.blog.repository.AuthorityRepository;
import cn.mchpro.blog.service.AuthorityService;
/**
 * authority服务接口实现
 * @author Administrator
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findById(id).get();
	}

}
