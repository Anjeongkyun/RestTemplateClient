USE [DAONUSDB_테스트_20211018]
GO

/****** Object:  Table [dbo].[tb_o_master]    Script Date: 2021-12-01 오후 3:45:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tb_o_master](
	[park_id] [char](3) NOT NULL,
	[local_id] [char](2) NOT NULL,
	[apt_id] [varchar](50) NOT NULL,
	[project_id] [varchar](20) NULL,
	[apt_nm] [varchar](50) NOT NULL,
	[apt_address] [varchar](100) NULL,
	[rep_num] [varchar](50) NULL,
	[manager_nm] [varchar](256) NULL,
	[latitude] [varchar](20) NULL,
	[longitude] [varchar](20) NULL,
	[reg_id] [varchar](50) NULL,
	[reg_dtm] [datetime] NULL,
 CONSTRAINT [PK_tb_o_master] PRIMARY KEY CLUSTERED 
(
	[park_id] ASC,
	[local_id] ASC,
	[apt_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


