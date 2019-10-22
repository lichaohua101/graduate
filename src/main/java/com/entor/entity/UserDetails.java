package com.entor.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangfm
 * @since 2019-10-22
 */
@TableName("user_details")
public class UserDetails extends Model<UserDetails> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 创建用户的时间
     */
	private Integer Uid;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 登录时间
     */
	private Date loginTime;
    /**
     * 退出时间
     */
	private Date exitTime;
    /**
     * 0不在线，1在线
     */
	private Integer online;
    /**
     * 登录教室
     */
	private Integer classRoomID;
	private Integer teacherID;


	public Integer getId() {
		return id;
	}

	public UserDetails setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUid() {
		return Uid;
	}

	public UserDetails setUid(Integer Uid) {
		this.Uid = Uid;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public UserDetails setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public UserDetails setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		return this;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public UserDetails setExitTime(Date exitTime) {
		this.exitTime = exitTime;
		return this;
	}

	public Integer getOnline() {
		return online;
	}

	public UserDetails setOnline(Integer online) {
		this.online = online;
		return this;
	}

	public Integer getClassRoomID() {
		return classRoomID;
	}

	public UserDetails setClassRoomID(Integer classRoomID) {
		this.classRoomID = classRoomID;
		return this;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public UserDetails setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
		return this;
	}

	public static final String ID = "id";

	public static final String UID = "Uid";

	public static final String CREATETIME = "createTime";

	public static final String LOGINTIME = "loginTime";

	public static final String EXITTIME = "exitTime";

	public static final String ONLINE = "online";

	public static final String CLASSROOMID = "classRoomID";

	public static final String TEACHERID = "teacherID";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserDetails{" +
			", id=" + id +
			", Uid=" + Uid +
			", createTime=" + createTime +
			", loginTime=" + loginTime +
			", exitTime=" + exitTime +
			", online=" + online +
			", classRoomID=" + classRoomID +
			", teacherID=" + teacherID +
			"}";
	}
}
