package com.entor.entity;

import java.io.Serializable;

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
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	private Integer roleid;


	public Integer getId() {
		return id;
	}

	public Permission setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Permission setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public Permission setRoleid(Integer roleid) {
		this.roleid = roleid;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String ROLEID = "roleid";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Permission{" +
			", id=" + id +
			", name=" + name +
			", roleid=" + roleid +
			"}";
	}
}
