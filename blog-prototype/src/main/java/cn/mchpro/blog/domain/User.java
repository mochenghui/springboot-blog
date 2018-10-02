package cn.mchpro.blog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User. 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月24日
 */
//@XmlRootElement // mediatype 转为xml
@Entity//实体
public class User {
	
	@Id//主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增长
	private long id; // 用户的唯一标识
 	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
