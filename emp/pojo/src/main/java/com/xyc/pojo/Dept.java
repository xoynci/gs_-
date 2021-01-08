package com.xyc.pojo;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;


/* 
* 
* gen by beetlsql 2020-12-31
*/
@Table(name="emp.dept")
public class Dept implements Serializable {
	
	private Integer id ;
	private String name ;
	
	public Dept() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	

}
