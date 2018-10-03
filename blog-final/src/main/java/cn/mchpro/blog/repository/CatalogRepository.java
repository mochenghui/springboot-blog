package cn.mchpro.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mchpro.blog.domain.Catalog;
import cn.mchpro.blog.domain.User;

/**
 * Catalog 仓库.
 *
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long>{
	
	/**
	 * 根据用户查询
	 * @param user
	 * @return
	 */
	List<Catalog> findByUser(User user);
	
	/**
	 * 根据用户名和分类名查询
	 * @param user
	 * @param name
	 * @return
	 */
	List<Catalog> findByUserAndName(User user,String name);
}
