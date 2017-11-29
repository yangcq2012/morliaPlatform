package com.morlia.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = -4128256499214057505L;
	
	private Integer uid;
	private String username;
	private String password;
	private String email;
	private String oid;
	private String device;
	private String os;
	private String guest;
	private String facebook;
	private String google;
	private Date created;
	private Date modified;
	private Date accessed;
	private String email2;
	public UserInfo() {
		super();
	}
	public UserInfo(Integer uid, String username, String password, String email, String oid, String device, String os,
			String guest, String facebook, String google, Date created, Date modified, Date accessed, String email2) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.oid = oid;
		this.device = device;
		this.os = os;
		this.guest = guest;
		this.facebook = facebook;
		this.google = google;
		this.created = created;
		this.modified = modified;
		this.accessed = accessed;
		this.email2 = email2;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getGoogle() {
		return google;
	}
	public void setGoogle(String google) {
		this.google = google;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getAccessed() {
		return accessed;
	}
	public void setAccessed(Date accessed) {
		this.accessed = accessed;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", oid=" + oid + ", device=" + device + ", os=" + os + ", guest=" + guest + ", facebook=" + facebook
				+ ", google=" + google + ", created=" + created + ", modified=" + modified + ", accessed=" + accessed
				+ ", email2=" + email2 + "]";
	}
	

}
