package cn.mchpro.blog.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.Comment;
import cn.mchpro.blog.repository.CommentRepository;
import cn.mchpro.blog.service.CommentService;
/**
 * comment service 接口实现
 * @author Administrator
 *
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;

	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).get();
	}

}
