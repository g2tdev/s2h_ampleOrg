package com.ampleexchange.api.page.productcall.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BpcReturn {	

	private String productcall_title;
	private String category_name;
	private String subcategory_name;	
	private String productcall_status;	
	private Timestamp productcall_createdate;

}