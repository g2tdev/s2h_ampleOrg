package com.ampleexchange.api.page.productcall.model;

import java.sql.Date;

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
public class SpcReturn {	

	private String user_name;
	private String category_name;
	private String subcategory_name;
	private Double productcall_desiredquantity;
	private Date productcall_closingdate;	

}
