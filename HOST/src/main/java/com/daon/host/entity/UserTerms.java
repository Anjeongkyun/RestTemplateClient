package com.daon.host.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_o_terms_history")
public class UserTerms {
	@Id
	private String terms_id;
	private String login_id;
	private String agree_yn;
	private String agree_date;
	private String ins_id;
	private String upd_id;
}
