package cn.mchpro.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.mchpro.blog.domain.Comment;
/**
 * comment repository 接口
 * @author Administrator
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
