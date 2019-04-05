package com.bb.model;



public class Gonggao implements java.io.Serializable
{
	public String id;

	public String  biaoti ; 
	public String  tupian ; 
	public String  neirong ; 
	public String  shijian ; 
	public String  leibie ; 
 
	public Gonggao()
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

	public String getBiaoti()
	{
		return this.biaoti;
	} 
	public void setBiaoti(String biaoti)
	{
		this.biaoti = biaoti;
	} 
	public String getTupian()
	{
		return this.tupian;
	} 
	public void setTupian(String tupian)
	{
		this.tupian = tupian;
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
	public String getLeibie()
	{
		return this.leibie;
	} 
	public void setLeibie(String leibie)
	{
		this.leibie = leibie;
	} 


}