package com.daon.host.web.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tb_o_complex")
public class ParkEntity extends BaseTimeEntity{
	
	@Id
	@Column(name = "apt_id")
	private String aptId;
	
	@Column(name = "project_id")
	private String projectId;
	
	@Column(name = "apt_nm")
	private String aptNm;
	
	@Column(name = "apt_address")
	private String aptAddress;
	
	@Column(name = "rep_num")
	private String repNum;
	
	@Column(name = "manager_nm")
	private String managerNm;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "ins_id")
	private String insId;
	
//	@CreationTimestamp
//	@Column(name = "ins_date")
//	private LocalDateTime insDate = LocalDateTime.now();
	
	@Column(name = "upd_id")
	private String updId;
	
//	@UpdateTimestamp
//	@Column(name = "upd_date")
//	private LocalDateTime updDate = LocalDateTime.now();
}
