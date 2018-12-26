package com.zsm.mini.vo;

import java.io.Serializable;

public class UserVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String openId;
	private String session_key;
	private String nickname;
    private String avatarurl;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatarurl() {
		return avatarurl;
	}
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	
	
	
}
