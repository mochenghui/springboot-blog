package cn.mchpro.blog.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mchpro.blog.domain.Vote;
import cn.mchpro.blog.repository.VoteRepository;
import cn.mchpro.blog.service.VoteService;

/**
 * Vote 服务.
 * 
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.deleteById(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findById(id).get();
	}

}
