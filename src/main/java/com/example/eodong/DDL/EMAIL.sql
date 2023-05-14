CREATE TABLE EMAIL(
    email_idx NUMBER(9) PRIMARY KEY,
    email_number VARCHAR2(20) NOT NULL,
    email_mail VARCHAR2(50)  NOT NULL,
    email_date DATE NOT NULL
);

drop table EMAIL;
insert INTO EMAIL VALUES(EMAIL_SEQ.nextval,'11111','rkdeown10@naver.com',sysdate);
commit;
CREATE SEQUENCE Email_seq START WITH 0 MINVALUE 0 maxvalue 999999999;
