package cn.mchpro.blog.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.Blog;
import cn.mchpro.blog.domain.User;
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
		blog.setReading(blog.getReading()+1);
		blogRepository.save(blog);
	}
 

}
