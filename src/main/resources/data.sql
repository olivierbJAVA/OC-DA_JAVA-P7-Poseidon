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
