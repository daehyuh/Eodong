Create table comment2(
    id number,
    contant varchar2(10),
    now date
);

insert into comment2 values (10000,'ㅎㅇ',sysdate);
insert into comment2 values (11000,'ㅎㅇ1',sysdate);
insert into comment2 values (11000,'ㅎㅇ2',sysdate);
insert into comment2 values (20000,'ㄶㅇ',sysdate);
insert into comment2 values (21000,'ㄶㅇ2',sysdate);

commit;

select * from comment2 order by id, now;