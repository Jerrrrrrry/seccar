USE [seccar]
GO

/****** Object:  Table [dbo].[Parking]    Script Date: 09/22/2017 19:52:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Parking](
	[vehicleid] [nvarchar](20) NOT NULL,
	[period] [nvarchar](20) NULL,
	[customer] [nvarchar](20) NULL,
	[cardescription] [nvarchar](50) NULL,
	[licenseno] [nvarchar](20) NULL,
	[inventoryints] [datetime] NULL,
	[inventoryoutts] [datetime] NULL,
	[parkingfee] [numeric](18, 4) NULL,
	[comments] [nvarchar](50) NULL,
	[createdts] [datetime] NULL,
	[lastupdatedts] [datetime] NULL,
	[creator] [nvarchar](20) NULL,
	[customermobile] [nvarchar](50) NULL,
	[issold] [nvarchar](10) NULL
) ON [PRIMARY]

GO


