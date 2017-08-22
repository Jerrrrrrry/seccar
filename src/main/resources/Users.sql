USE [seccar]
GO

/****** Object:  Table [dbo].[Users]    Script Date: 08/21/2017 21:10:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Users](
	[id] [nvarchar](20) NOT NULL,
	[userid] [nvarchar](20) NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](20) NULL,
	[accesstype] [nvarchar](20) NULL,
	[islocked] [nvarchar](10) NULL,
	[creator] [nvarchar](20) NULL,
	[createdts] [datetime] NULL,
	[lastupdatedts] [datetime] NULL,
	[userdesc] [nvarchar](100) NULL,
	[comments] [nvarchar](100) NULL,
	[sbcol1] [nvarchar](20) NULL,
	[sbcol2] [nvarchar](20) NULL,
	[sbcol3] [nvarchar](20) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


