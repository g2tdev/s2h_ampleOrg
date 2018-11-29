package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lot")
public class Lot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID lot_id;
	
	private UUID lot_number;
	private Date lot_expirydate;
	private Timestamp lot_createdate;
	private Date lot_updatedate;
	private Boolean lot_deleted;
	private UUID lastupdatedby;
	public UUID getLot_id() {
		return lot_id;
	}
	public void setLot_id(UUID lot_id) {
		this.lot_id = lot_id;
	}
	public UUID getLot_number() {
		return lot_number;
	}
	public void setLot_number(UUID lot_number) {
		this.lot_number = lot_number;
	}
	public Date getLot_expirydate() {
		return lot_expirydate;
	}
	public void setLot_expirydate(Date lot_expirydate) {
		this.lot_expirydate = lot_expirydate;
	}
	public Timestamp getLot_createdate() {
		return lot_createdate;
	}
	public void setLot_createdate(Timestamp lot_createdate) {
		this.lot_createdate = lot_createdate;
	}
	public Date getLot_updatedate() {
		return lot_updatedate;
	}
	public void setLot_updatedate(Date lot_updatedate) {
		this.lot_updatedate = lot_updatedate;
	}
	public Boolean getLot_deleted() {
		return lot_deleted;
	}
	public void setLot_deleted(Boolean lot_deleted) {
		this.lot_deleted = lot_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

}
