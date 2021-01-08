package com.xyc.temp;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2021-01-04
*/
@Table(name="emp.user")
public class User   {
	
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
