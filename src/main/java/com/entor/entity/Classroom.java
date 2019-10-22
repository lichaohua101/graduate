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
 * @since 2019-10-22
 */
public class Classroom extends Model<Classroom> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String className;
    /**
     * 0可用，1不可以
     */
	private Integer state;


	public Integer getId() {
		return id;
	}

	public Classroom setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getClassName() {
		return className;
	}

	public Classroom setClassName(String className) {
		this.className = className;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public Classroom setState(Integer state) {
		this.state = state;
		return this;
	}

	public static final String ID = "id";

	public static final String CLASSNAME = "className";

	public static final String STATE = "state";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Classroom{" +
			", id=" + id +
			", className=" + className +
			", state=" + state +
			"}";
	}
}
