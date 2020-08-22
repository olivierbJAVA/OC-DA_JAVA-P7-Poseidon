insert into Users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$pf0c3EX5/UqBLVZsj5BjoufMz1Aa03EHrbJaSci/1eWSkM7Zk7nNm', 'ADMIN');
insert into Users(fullname, username, password, role) values('Utilisateur', 'utilisateur', '$2a$10$pf0c3EX5/UqBLVZsj5BjoufMz1Aa03EHrbJaSci/1eWSkM7Zk7nNm', 'USER');

insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('AAA', 'AA+', 'AA-', 1);
insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('BBB', 'BB+', 'BB-', 2);
insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('CCC', 'CC+', 'CC-', 3);

insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name1', 'description1', 'json1', 'template1', 'sqlStr1', 'sqlPart1');
insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name2', 'description2', 'json2', 'template2', 'sqlStr2', 'sqlPart2');
insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name3', 'description3', 'json3', 'template3', 'sqlStr3', 'sqlPart3');

insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (1, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );
insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (2, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );
insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (3, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );

insert into BidList(account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, security, status, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account1', 'Type1', 10, 1, 123.00, 321.00, 'Benchmark1', '2020-08-10 10:20:30.0', 'Commentary1', 'Security1', 'Status1', 'Trader1', 'Book1', 'CreationName1', '2020-07-23 10:20:30.0', 'RevisionName1', '2020-08-10 09:10:23.0', 'DealName1', 'DealType1', 'SourceListId1', 'Side1');
insert into BidList(account,type, bidQuantity, askQuantity, bid,  ask, benchmark, bidListDate, commentary, security, status, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account2','Type2', 20, 2, 123.00, 321.00, 'Benchmark2', '2020-08-10 10:20:30.0', 'Commentary2', 'Security2', 'Status2', 'Trader2', 'Book2', 'CreationName2', '2020-07-23 10:20:30.0', 'RevisionName2', '2020-08-10 09:10:23.0', 'DealName2', 'DealType2', 'SourceListId2', 'Side2');
insert into BidList(account,type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, security, status, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account3','Type3', 30, 3, 123.00, 321.00, 'Benchmark3', '2020-08-10 10:20:30.0', 'Commentary3', 'Security3', 'Status3', 'Trader3', 'Book3', 'CreationName3', '2020-07-23 10:20:30.0', 'RevisionName3', '2020-08-10 09:10:23.0', 'DealName3', 'DealType3', 'SourceListId3', 'Side3');

insert into Trade(account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account1','Type1', 10, 1, 123.00, 321.00, '2020-08-10 10:20:30.0', 'Security1', 'Status1', 'Trader1', 'Benchmark1', 'Book1', 'CreationName1', '2020-07-23 10:20:30.0', 'RevisionName1', '2020-08-10 09:10:23.0', 'DealName1', 'DealType1', 'SourceListId1', 'Side1');
insert into Trade(account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account2','Type2', 20, 2, 123.00, 321.00, '2020-08-10 10:20:30.0', 'Security2', 'Status2', 'Trader2', 'Benchmark2', 'Book2', 'CreationName2', '2020-07-23 10:20:30.0', 'RevisionName2', '2020-08-10 09:10:23.0', 'DealName2', 'DealType2', 'SourceListId2', 'Side2');
insert into Trade(account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
values ('Account3','Type3', 30, 3, 123.00, 321.00, '2020-08-10 10:20:30.0', 'Security3', 'Status3', 'Trader3', 'Benchmark3', 'Book3', 'CreationName3', '2020-07-23 10:20:30.0', 'RevisionName3', '2020-08-10 09:10:23.0', 'DealName3', 'DealType3', 'SourceListId3', 'Side3');
