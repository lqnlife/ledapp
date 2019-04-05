package com.bb.model;



public class Shoucang implements java.io.Serializable
{
	public String id;

	public String  yonghu ; 
	public String  biaoti ; 
	public String  neirong ; 
	public String  shijian ; 
 
	public Shoucang()
	{
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getYonghu()
	{
		return this.yonghu;
	} 
	public void setYonghu(String yonghu)
	{
		this.yonghu = yonghu;
	} 
	public String getBiaoti()
	{
		return this.biaoti;
	} 
	public void setBiaoti(String biaoti)
	{
		this.biaoti = biaoti;
	} 
	public String getNeirong()
	{
		return this.neirong;
	} 
	public void setNeirong(String neirong)
	{
		this.neirong = neirong;
	} 
	public String getShijian()
	{
		return this.shijian;
	} 
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	} 


}