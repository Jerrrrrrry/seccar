USE [seccar]
GO

/****** Object:  Table [dbo].[InterestCost]    Script Date: 09/04/2017 14:18:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[InterestCost](
	[vehicleid] [nvarchar](20) NULL,
	[interestcostrate] [numeric](18, 4) NULL,
	[costsettleto] [datetime] NULL,
	[interestcostamount] [numeric](18, 4) NULL,
	[createdts] [datetime] NULL,
	[lastupdatedts] [datetime] NULL,
	[creator] [nvarchar](20) NULL
) ON [PRIMARY]

GO


