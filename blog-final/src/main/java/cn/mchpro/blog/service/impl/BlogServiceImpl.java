package cn.mchpro.blog.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.Blog;
import cn.mchpro.blog.domain.Catalog;
import cn.mchpro.blog.domain.Comment;
import cn.mchpro.blog.domain.User;
import cn.mchpro.blog.domain.Vote;
import cn.mchpro.blog.repository.BlogRepository;
import cn.mchpro.blog.service.BlogService;

/**
 * Blog 服务.
 * 
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	/* (non-Javadoc)
	 */
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	/* (non-Javadoc)
	 */
	@Transactional
	@Override
	public void removeBlog(Long id) {
		blogRepository.deleteById(id);
	}

	/* (non-Javadoc)
	 */
	@Transactional
	@Override
	public Blog updateBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	/* (non-Javadoc)
	 */
	@Override
	public Blog getBlogById(Long id) {
		return blogRepository.findById(id).get();
	}

	@Override
	public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
		return blogs;
	}

	@Override
	public Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
		return blogs;
	}

	@Override
	public void readingIncrease(Long id) {
		Blog blog = blogRepository.findById(id).get();
		blog.setReadSize(blog.getReadSize()+1);
		blogRepository.save(blog);
	}

	@Override
	public Blog createComment(Long blogId, String commentContent) {
		Blog originalBlog = blogRepository.findById(blogId).get();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Comment comment = new Comment(user, commentContent);
		originalBlog.addComment(comment);
		return blogRepository.save(originalBlog);
	}

	@Override
	public void removeComment(Long blogId, Long commentId) {
		Blog originalBlog = blogRepository.findById(blogId).get();
		originalBlog.removeComment(commentId);
		blogRepository.save(originalBlog);
	}

	@Override
	public Blog createVote(Long blogId) {
		Blog blog = blogRepository.findById(blogId).get();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean flag = blog.addVote(new Vote(user));
		if(flag) {
			throw new IllegalArgumentException("您已经点过赞了~~");
		}else {
			return this.saveBlog(blog);
		}
	}

	@Override
	public void removeVote(Long blogId, Long voteId) {
		Blog blog = blogRepository.findById(blogId).get();
		blog.removeVote(voteId);//修改对象
		this.saveBlog(blog);//持久化
	}

	@Override
	public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
		return blogRepository.findByCatalog(catalog, pageable);
	}
 

}
