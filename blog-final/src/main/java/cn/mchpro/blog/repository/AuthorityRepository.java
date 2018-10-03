package cn.mchpro.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mchpro.blog.domain.Authority;
/**
 * 权限 仓库
 * @author Administrator
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
