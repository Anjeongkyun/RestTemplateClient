USE [DAONUSDB_테스트_20211018]
GO

/****** Object:  Table [dbo].[tb_o_member]    Script Date: 2021-12-01 오후 3:46:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tb_o_member](
	[apt_id] [varchar](50) NOT NULL,
	[member_id] [int] NOT NULL,
	[member_app_id] [varchar](100) NULL,
	[household_master_id] [int] NULL,
	[member_nm] [varchar](50) NULL,
	[member_birth] [varchar](10) NULL,
	[member_mobile] [varchar](50) NULL,
	[member_email] [varchar](100) NULL,
	[car_no] [varchar](20) NULL,
	[car_alias] [varchar](50) NULL,
	[dong] [varchar](50) NULL,
	[ho] [varchar](50) NULL,
	[reg_id] [varchar](50) NULL,
	[reg_dtm] [datetime] NULL,
 CONSTRAINT [PK_tb_o_member] PRIMARY KEY CLUSTERED 
(
	[apt_id] ASC,
	[member_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


