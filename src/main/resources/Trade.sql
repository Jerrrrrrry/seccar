USE [seccar]
GO

/****** Object:  Table [dbo].[SecCarTrade]    Script Date: 09/04/2017 14:04:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[SecCarTrade](
	[vehicleid] [nvarchar](20) NOT NULL,
	[VIN] [nvarchar](20) NULL,
	[licenseno] [nvarchar](20) NULL,
	[vehicledesc] [nvarchar](100) NULL,
	[traderid] [nvarchar](20) NULL,
	[tradername] [nvarchar](20) NULL,
	[purchaseprice] [numeric](18, 4) NULL,
	[purchasedate] [datetime] NULL,
	[ownerid] [nvarchar](50) NULL,
	[ownername] [nvarchar](50) NULL,
	[ownerdesc] [nvarchar](200) NULL,
	[interestrate] [numeric](18, 4) NULL,
	[interest] [numeric](18, 4) NULL,
	[actualloan] [numeric](18, 4) NULL,
	[spareloan] [numeric](18, 4) NULL,
	[earnest] [numeric](18, 4) NULL,
	[sellprice] [numeric](18, 4) NULL,
	[selldate] [datetime] NULL,
	[pricediff] [numeric](18, 4) NULL,
	[tradecost] [numeric](18, 4) NULL,
	[profit] [numeric](18, 4) NULL,
	[vehicletype] [nvarchar](20) NULL,
	[settlement] [nvarchar](20) NULL,
	[settlementdate] [datetime] NULL,
	[totalprofit] [numeric](18, 4) NULL,
	[traderprofit] [numeric](18, 4) NULL,
	[picturepath] [nvarchar](500) NULL,
	[isdeleted] [nvarchar](10) NULL,
	[issold] [nvarchar](10) NULL,
	[comments] [nvarchar](100) NULL,
	[createdts] [datetime] NULL,
	[lastupdatedts] [datetime] NULL,
	[interestcost] [numeric](18, 4) NULL,
	[buyerid] [nvarchar](50) NULL,
	[buyername] [nvarchar](50) NULL,
	[sbcol3] [nvarchar](20) NULL,
 CONSTRAINT [PK_SecCarTrade] PRIMARY KEY CLUSTERED 
(
	[vehicleid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


