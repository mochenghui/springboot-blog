package cn.mchpro.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.mchpro.blog.domain.User;

/**
 * 用户仓库
 * @author Administrator
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * 根据用户名分页查询用户列表
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name, Pageable pageable);
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @param pageable
	 * @return
	 */
	User findByUsername(String username);

}
