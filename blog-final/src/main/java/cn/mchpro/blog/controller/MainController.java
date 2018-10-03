package cn.mchpro.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mchpro.blog.domain.Authority;
import cn.mchpro.blog.domain.User;
import cn.mchpro.blog.service.AuthorityService;
import cn.mchpro.blog.service.UserService;
import cn.mchpro.blog.util.ConstraintViolationExceptionHandler;
import cn.mchpro.blog.vo.Response;

/**
 * 主页控制器
 * @author mch
 *
 */
@Controller
public class MainController {
	private static final Long ROLE_USER_AUTHORITY_ID = 2L;//权限id,普通博主
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登陆失败，用户名或者密码错误！");
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	/**
	 * 注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping("/register")
//	@ResponseBody
	public ResponseEntity<Response> registerUser(User user) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);//添加角色
		user.setEncodePassword(user.getPassword()); // 加密密码
		try {
			userService.saveUser(user);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "注册成功，请前往登录", user));
	}
	
	@GetMapping("/search")
	public String search() {
		return "search";
	}
}
