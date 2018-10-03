package cn.mchpro.blog.service;

import cn.mchpro.blog.domain.Comment;

/**
 * comment service 接口
 * @author Administrator
 *
 */
public interface CommentService {
	/**
	 * 根据id获取 Comment
	 * @param id
	 * @return
	 */
	Comment getCommentById(Long id);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	void removeComment(Long id);
}
