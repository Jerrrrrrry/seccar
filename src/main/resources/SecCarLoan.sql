USE [seccar]
GO

/****** Object:  Table [dbo].[SecCarLoan]    Script Date: 09/21/2017 18:44:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[SecCarLoan](
	[vehicleid] [nvarchar](20) NOT NULL,
	[VIN] [nvarchar](20) NULL,
	[licenseno] [nvarchar](20) NULL,
	[vehicledesc] [nvarchar](100) NULL,
	[ownerid] [nvarchar](50) NULL,
	[ownername] [nvarchar](50) NULL,
	[ownerdesc] [nvarchar](200) NULL,
	[borrowdate] [datetime] NULL,
	[returndate] [datetime] NULL,
	[periodmonths] [numeric](18, 4) NULL,
	[traderid] [nvarchar](20) NULL,
	[tradername] [nvarchar](20) NULL,
	[borrowamount] [numeric](18, 4) NULL,
	[interestrate] [numeric](18, 4) NULL,
	[interest] [numeric](18, 4) NULL,
	[interestpaid] [numeric](18, 4) NULL,
	[totalinterest] [numeric](18, 4) NULL,
	[midinterestrate] [numeric](18, 4) NULL,
	[midinterest] [numeric](18, 4) NULL,
	[parkingfee] [numeric](18, 4) NULL,
	[otherfee] [numeric](18, 4) NULL,
	[comments] [nvarchar](100) NULL,
	[actualloan] [numeric](18, 4) NULL,
	[actualreturn] [numeric](18, 4) NULL,
	[actualreturndate] [datetime] NULL,
	[othercost] [numeric](18, 4) NULL,
	[profit] [numeric](18, 4) NULL,
	[vehicletype] [nvarchar](20) NULL,
	[settlement] [nvarchar](20) NULL,
	[settlementdate] [datetime] NULL,
	[totalprofit] [numeric](18, 4) NULL,
	[traderprofit] [numeric](18, 4) NULL,
	[picturepath] [nvarchar](500) NULL,
	[isdeleted] [nvarchar](10) NULL,
	[isreturned] [nvarchar](10) NULL,
	[isabandon] [nvarchar](10) NULL,
	[comments2] [nvarchar](100) NULL,
	[interestpaidto] [datetime] NULL,
	[nextpaymentdate] [datetime] NULL,
	[createdts] [datetime] NULL,
	[lastupdatedts] [datetime] NULL,
	[interestcost] [numeric](18, 4) NULL,
	[earnest] [numeric](18, 4) NULL,
	[mobileno] [nvarchar](50) NULL,
	[sbcol3] [nvarchar](20) NULL,
 CONSTRAINT [PK_SecCarPledge] PRIMARY KEY CLUSTERED 
(
	[vehicleid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


