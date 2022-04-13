USE [DAONUSDB_테스트_20211018]
GO

/****** Object:  Table [dbo].[tb_o_household]    Script Date: 2021-12-01 오후 3:48:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tb_o_household](
	[apt_id] [varchar](50) NOT NULL,
	[household_id] [int] NOT NULL,
	[dong] [varchar](50) NULL,
	[ho] [varchar](50) NULL,
	[household_alias] [varchar](50) NULL,
	[household_master_id] [int] NULL,
	[park_assist_yn] [char](1) NULL,
	[park_noti_yn] [char](1) NULL,
	[reg_yn] [char](1) NULL,
	[reg_id] [varchar](50) NULL,
	[reg_dtm] [datetime] NULL,
 CONSTRAINT [PK_tb_o_household] PRIMARY KEY CLUSTERED 
(
	[apt_id] ASC,
	[household_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


