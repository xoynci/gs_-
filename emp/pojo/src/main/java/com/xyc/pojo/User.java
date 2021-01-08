package com.xyc.pojo;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;


/* 
* 
* gen by beetlsql 2020-12-31
*/
@Table(name="emp.user")
public class User implements Serializable {
	
	private Integer id ;
	private Integer deptId ;
	private String name ;
	
	public User() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getDeptId(){
		return  deptId;
	}
	public void setDeptId(Integer deptId ){
		this.deptId = deptId;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	

}
