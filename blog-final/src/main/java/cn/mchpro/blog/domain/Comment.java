package cn.mchpro.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

/**
 * comment 实体
 * @author Administrator
 *
 */
@Entity
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增长
	private Long id;
	@NotEmpty(message="评论内容不能为空")
	@Size(min=2,max=500)
	@Column(nullable=false)//映射为字段，不能为空
	private String content;
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	@Column(nullable=false)//映射字段不能为空
	@CreationTimestamp//数据库自动创建时间
	private Timestamp createTime;
	
	protected Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(User user, String content) {
		this.content = content;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
	public Timestamp getCreateTime() {
		return createTime;
	}
	
	
	
}
