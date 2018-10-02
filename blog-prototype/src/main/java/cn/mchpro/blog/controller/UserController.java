package cn.mchpro.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.mchpro.blog.domain.User;
import cn.mchpro.blog.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private List<User> getUserList(){
		List<User> list = new ArrayList<>();
		for(User user:userRepository.findAll()) {
			list.add(user);
		}
		return list;
	}
	@GetMapping
	public  ModelAndView list(Model model) {
		model.addAttribute("userList",getUserList());
		model.addAttribute("title","用户管理");
		return new ModelAndView("users/list","userModel",model);
	}
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id,Model model) {
		Optional<User> user = userRepository.findById(id);
		model.addAttribute("user",user.get());
		model.addAttribute("title","查看用户");
		return new ModelAndView("users/view","userModel",model);
	} 
	/**
	 * 获取 form 表单页面
	 * @param user
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("title", "创建用户");
		return new ModelAndView("users/form", "userModel", model);
	}
	/**
	 * 新建用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping
	public ModelAndView create(User user) {
		user = userRepository.save(user);
		return new ModelAndView("redirect:/users");
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, Model model) {
		userRepository.deleteById(id);

		model.addAttribute("userList", getUserList());
		model.addAttribute("title", "删除用户");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Optional<User> user = userRepository.findById(id);
		model.addAttribute("user", user.get());
		model.addAttribute("title", "修改用户");
		return new ModelAndView("users/form", "userModel", model);
	}

}
