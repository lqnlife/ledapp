package com.bb.model;



public class Dingyue implements java.io.Serializable
{
	public String id;

	public String  lanmu ; 
	public String  yonghu ; 
 
	public Dingyue()
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

	public String getLanmu()
	{
		return this.lanmu;
	} 
	public void setLanmu(String lanmu)
	{
		this.lanmu = lanmu;
	} 
	public String getYonghu()
	{
		return this.yonghu;
	} 
	public void setYonghu(String yonghu)
	{
		this.yonghu = yonghu;
	} 


}