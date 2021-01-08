package com.xyc.util.filterDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 可无需继承,jackson过滤属性也能生效
 * @author dell
 *
 */
//@Data
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = {"autoMark"})
public class BaseEntity {
	
	@JsonView(FilterView.OutputAutoMark.class)  
    String autoMark;
	
}
