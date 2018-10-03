package cn.mchpro.blog.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.User;
import cn.mchpro.blog.repository.UserRepository;
import cn.mchpro.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService ,UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void removeUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		name = "%"+name+"%";
		Page<User> users = userRepository.findByNameLike(name, pageable);
		return users;
	}
	
	@Override
	public void removeUsersInBatch(List<User> users) {
		// TODO Auto-generated method stub

	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}


}
