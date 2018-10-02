package cn.mchpro.blog.repository;

import org.springframework.data.repository.CrudRepository;

import cn.mchpro.blog.domain.User;

/**
 * 用户仓库
 * @author Administrator
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{

//
//	/**
//	 * 新增或者修改用户
//	 * @param user
//	 * @return
//	 */
//	User saveOrUpateUser(User user);
//
//	/**
//	 * 删除用户
//	 * @param id
//	 */
//	void deleteUser(Long id);
//
//	/**
//	 * 根据用户id获取用户
//	 * @param id
//	 * @return
//	 */
//	User getUserById(Long id);
//
//	/**
//	 * 获取所有用户的列表
//	 * @return
//	 */
//	List<User> listUser();

}
