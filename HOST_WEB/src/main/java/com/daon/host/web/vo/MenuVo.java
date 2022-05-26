package com.daon.host.web.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
public class MenuVo {
	
	@Data
	public static class Menu{
		private String menuId;
		private String menuNm;
		private String description;
		private String menuIcon;
		private String menuRouterTo;
		private String menuShowYn;
		private String menuOrder;
		private String menuRouteName;
		private String menuRouteComponents;
		private String menuType;
	}
	
	
	@Data
	public static class CRUD{
		private String id;
		private String regType;
		private String menuId;
		private String menuNm;
		private String description;
		private String menuIcon;
		private String menuRouterTo;
		private String menuShowYn;
		private String menuOrder;
		private String menuRouteName;
		private String menuType;
		private String insId;
		private String insDate;
		private String updId;
		private String updDate;
	}
	
}
