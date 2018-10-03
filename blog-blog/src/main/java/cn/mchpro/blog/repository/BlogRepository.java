package cn.mchpro.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.mchpro.blog.domain.Blog;
import cn.mchpro.blog.domain.User;

/**
 * Blog 仓库.
 *
 */
public interface BlogRepository extends JpaRepository<Blog, Long>{
	/**
	 * 根据用户名分页查询用户列表(时间逆序)
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title, Pageable pageable);
	
	/**
	 * 根据用户名分页查询用户列表（最热）
	 * @param user
	 * @param title
	 * @param sort
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);
}
