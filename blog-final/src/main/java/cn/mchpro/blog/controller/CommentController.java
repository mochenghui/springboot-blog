package cn.mchpro.blog.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 评论控制器
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestParam;

import cn.mchpro.blog.domain.Blog;
import cn.mchpro.blog.domain.Comment;
import cn.mchpro.blog.domain.User;
import cn.mchpro.blog.service.BlogService;
import cn.mchpro.blog.service.CommentService;
import cn.mchpro.blog.util.ConstraintViolationExceptionHandler;
import cn.mchpro.blog.vo.Response;
@Controller
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 获取评论列表
	 * @return
	 */
	@GetMapping
	public String listComments(@RequestParam(value="blogId",required=true)Long blogId,Model model) {
		Blog blog = blogService.getBlogById(blogId);
		List<Comment> comments = blog.getComments();
		//判断操作用户是否是评论的所有者
		String commentOwner = "";
		if(SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() 
				&&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal != null) {
				commentOwner = principal.getUsername();
			}
		}
		model.addAttribute("comments",comments);
		model.addAttribute("commentOwner",commentOwner);
		return "/userspace/blog :: #mainContainerRepleace";
	}
	/**
	 * 发表评论
	 * @return
	 */
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")//指定角色才能使用此方法
	public ResponseEntity<Response> createComment(Long blogId,String commentContent){
		try {
		blogService.createComment(blogId, commentContent);
		}catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		
		return ResponseEntity.ok().body(new Response(true,"评论成功！",null));
	}
	/**
	 * 删除评论
	 * @param id
	 * @param blogId
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Response> deleteBlog(@PathVariable("id") Long id, Long blogId){
		boolean isOwner = false;
		User user = commentService.getCommentById(id).getUser();
		//判断操作用户是否是博客的所有者
		if(SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal != null && user.getUsername().equals(principal.getUsername())) {
				isOwner = true;
			}
		}
		
		if(!isOwner) {
			return ResponseEntity.ok().body(new Response(false, "没有操作权限"));
		}
		
		try {
			blogService.removeComment(blogId, id);
			commentService.removeComment(id);
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		
		return ResponseEntity.ok().body(new Response(true, "处理成功", null));
	}
	
}
