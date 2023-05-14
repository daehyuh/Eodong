CREATE TABLE MEMBER
(
    member_idx    NUMBER(9) PRIMARY KEY,
    member_major  VARCHAR(50) REFERENCES MEMBERMAJOR(MEMBER_MAJOR) NOT NULL,
    member_id     VARCHAR2(20)                                     NOT NULL UNIQUE,
    member_pw     VARCHAR2(200)                                    NOT NULL,
    member_name   VARCHAR2(20)                                     NOT NULL,
    member_uni_id VARCHAR2(15)                                     NOT NULL UNIQUE,
    member_phone  VARCHAR2(20)                                     NOT NULL UNIQUE,
    member_email  VARCHAR2(50)                                     NOT NULL UNIQUE,
    member_date   DATE DEFAULT SYSDATE                             NOT NULL
);
commit;

INSERT INTO MEMBER
VALUES (Member_seq.nextval, '컴퓨터공학부', '1', '1', '1', 1, 1, '1', SYSDATE);

SELECT *
FROM MEMBER;

CREATE SEQUENCE Member_seq START WITH 0 MINVALUE 0 maxvalue 999999999;

DROP TABLE MEMBER;

DROP SEQUENCE MEMBER_SEQ;

CREATE TABLE MemberMajor
(
    MEMBER_MAJOR VARCHAR2(50) PRIMARY KEY
);


select * from MemberMajor;

commit;

SELECT * FROM MemberMajor;


drop table MemberMajor CASCADE CONSTRAINTS ;

CREATE TABLE CLUB
(
    CLUB_IDX            NUMBER(9) PRIMARY KEY,
    MEMBER_IDX          NUMBER(9) REFERENCES MEMBER (MEMBER_IDX)                         NOT NULL,
    CLUB_CAMPUS         NUMBER(1) CHECK (CLUB_CAMPUS IN (1, 2, -1))                      NOT NULL,
    CLUB_NAME           VARCHAR2(40)                                                     NOT NULL,
    CLUB_CLASSIFICATION VARCHAR2(40) REFERENCES CLUBCLASSIFICATION (CLUB_CLASSIFICATION) NOT NULL,
    CLUB_FIELD          VARCHAR2(40) REFERENCES CLUBFIELD (CLUB_FIELD)                   NOT NULL,
    CLUB_ROOM           VARCHAR(40),
    CLUB_INFO           VARCHAR(500),
    CLUB_DATE           DATE DEFAULT SYSDATE                                             NOT NULL
);

CREATE SEQUENCE CLUB_SEQ START WITH 0 MINVALUE 0;

-- INSERT INTO CLUB VALUES (CLUB_SEQ.nextval, 16, 2, '대현소프트', '일반동아리', '개발', '', '같이 프로젝트 할 팀원들 구합니다.', SYSDATE);
SELECT *
FROM CLUB;

CREATE SEQUENCE CLUBSEQ START WITH 0 MINVALUE 0;

CREATE TABLE CLUBCLASSIFICATION
(
    CLUB_CLASSIFICATION VARCHAR2(40) PRIMARY KEY
);
-- INSERT INTO CLUB_CLASSIFICATION VALUES ('일반동아리');

CREATE TABLE CLUBFIELD
(
    CLUB_FIELD VARCHAR2(40) PRIMARY KEY
);
-- INSERT INTO CLUB_FIELD VALUES ('개발');

CREATE TABLE CLUBCOMMUNITY
(
    CC_IDX     NUMBER(9) PRIMARY KEY,
    MEMBER_IDX NUMBER(9) REFERENCES MEMBER (MEMBER_IDX) NOT NULL,
    CLUB_IDX   NUMBER(9) REFERENCES CLUB (CLUB_IDX)     NOT NULL,
    CC_TITLE   VARCHAR2(40),
    CC_CONTENT VARCHAR2(500),
    CC_DATE    DATE DEFAULT SYSDATE                     NOT NULL
);

CREATE SEQUENCE CC_SEQ START WITH 0 MINVALUE 0;