package edu.self.model;

import java.io.Serializable;

/**
 * 商品信息
 * @author mzba
 *
 */
public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rid;
	private String resourceName;
	private String resourceDescribe;
	private String status;
	private int uid;
	private String userId;
	private String userName;
	private String address;
	private String phone; 

	private String resource_pic_path, resource_date   ; 
	
	public String  resource_type  , resource_price  ;
	
//	resource_pic_path   , resource_buy_date ;
	 
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceDescribe() {
		return resourceDescribe;
	}
	public void setResourceDescribe(String resourceDescribe) {
		this.resourceDescribe = resourceDescribe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getResource_pic_path() {
		return resource_pic_path;
	}
	public void setResource_pic_path(String resource_pic_path) {
		this.resource_pic_path = resource_pic_path;
	}
 
	public String getResource_date() {
		return resource_date;
	}
	public void setResource_date(String resource_date) {
		this.resource_date = resource_date;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_price() {
		return resource_price;
	}
	public void setResource_price(String resource_price) {
		this.resource_price = resource_price;
	}
 
	
	
}
