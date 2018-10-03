package cn.mchpro.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mchpro.blog.domain.Vote;

/**
 * Vote 仓库.
 *
 */
public interface VoteRepository extends JpaRepository<Vote, Long>{
 
}
