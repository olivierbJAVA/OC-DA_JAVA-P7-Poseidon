DROP SCHEMA IF EXISTS `PoseidonProd`;
CREATE DATABASE `PoseidonProd`;
USE `PoseidonProd`;

CREATE TABLE BidList (
  BidListId tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidListId)
);

CREATE TABLE Trade (
  TradeId tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

CREATE TABLE CurvePoint (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  curveId tinyint,
  asOfDate TIMESTAMP,
  term DOUBLE ,
  value DOUBLE ,
  creationDate TIMESTAMP ,

  PRIMARY KEY (Id)
);

CREATE TABLE Rating (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber tinyint,

  PRIMARY KEY (Id)
);

CREATE TABLE RuleName (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
);

CREATE TABLE Users (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
);

insert into Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN");
insert into Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");

insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('AAA', 'AA+', 'AA-', 1);
insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('BBB', 'BB+', 'BB-', 2);
insert into Rating(moodysRating, sandPRating, fitchRating, orderNumber) values ('CCC', 'CC+', 'CC-', 3);

insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name1', 'description1', 'json1', 'template1', 'sqlStr1', 'sqlPart1');
insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name2', 'description2', 'json2', 'template2', 'sqlStr2', 'sqlPart2');
insert into RuleName(name, description, json, template, sqlStr, sqlPart) values ('name3', 'description3', 'json3', 'template3', 'sqlStr3', 'sqlPart3');

insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (1, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );
insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (2, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );
insert into CurvePoint(curveId, asOfDate, term, value, creationDate) values (3, '2020-08-12 15:30' , 123.12, 456.45, '2020-08-12 15:30' );
