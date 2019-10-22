package com.entor.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangfm
 * @since 2019-10-14
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户的帐号
     */
	private String username;
    /**
     * 用户的密码
     */
	private String password;
	/**
	 * 用户角色
	 */
	private Integer rid;
	/**
	 * 用户的全部登录信息
	 */
	private UserDetails lUsers;

	

	public User() {
		super();
	}


	public User(Integer id, String username, String password, Integer rid, UserDetails lUsers) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rid = rid;
		this.lUsers = lUsers;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getRid() {
		return rid;
	}


	public void setRid(Integer rid) {
		this.rid = rid;
	}


	public UserDetails getlUsers() {
		return lUsers;
	}


	public void setlUsers(UserDetails lUsers) {
		this.lUsers = lUsers;
	}


	public static final String ID = "id";

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String RID = "rid";
	

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", rid=" + rid + ", lUsers="
				+ lUsers + "]";
	}

	
	
}
