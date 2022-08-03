DROP TABLE "ROOM" CASCADE CONSTRAINTS;
DROP TABLE "FLIGHT" CASCADE CONSTRAINTS;
DROP TABLE "MEMBER" CASCADE CONSTRAINTS;
DROP TABLE "QNA" CASCADE CONSTRAINTS;
DROP TABLE "PAYMENT" CASCADE CONSTRAINTS;
DROP TABLE "FLIGHT_RESERVATION" CASCADE CONSTRAINTS;
DROP TABLE "RENTAL_CAR" CASCADE CONSTRAINTS;
DROP TABLE "SURVEY" CASCADE CONSTRAINTS;
DROP TABLE "CAR_RESERVATION" CASCADE CONSTRAINTS;
DROP TABLE "ACCOM_RESERVATION" CASCADE CONSTRAINTS;
DROP TABLE "CAR" CASCADE CONSTRAINTS;
DROP TABLE "ACCOM" CASCADE CONSTRAINTS;
DROP TABLE "PERSON_GROUP" CASCADE CONSTRAINTS;
DROP TABLE "FUEL" CASCADE CONSTRAINTS;
DROP TABLE "PURPOSE" CASCADE CONSTRAINTS;
DROP TABLE "ACCOM_AR_INFO" CASCADE CONSTRAINTS;
DROP TABLE "LOCATION" CASCADE CONSTRAINTS;
DROP TABLE "ROOM_VIEW_INFO" CASCADE CONSTRAINTS;
DROP TABLE "CATEGORY" CASCADE CONSTRAINTS;
DROP TABLE "TRAVEL" CASCADE CONSTRAINTS;
DROP TABLE "FLIGHT_TIME" CASCADE CONSTRAINTS;
DROP TABLE "TB_SIZE" CASCADE CONSTRAINTS;

DROP TABLE room;

CREATE TABLE room (
    room_no      NUMBER NOT NULL,
    accom_no     NUMBER NOT NULL,
    room_view    NUMBER NOT NULL,
    room_name    VARCHAR2(30) NOT NULL,
    room_price   NUMBER NOT NULL,
    capacity     NUMBER NOT NULL,
    animal_yn    CHAR(1) DEFAULT 'N' NOT NULL CHECK ( animal_yn IN ( 'Y', 'N' ) ),
    pool_able_yn CHAR(1) NOT NULL CHECK ( pool_able_yn IN ( 'Y', 'N' ) )
);

--ALTER TABLE ROOM MODIFY animal_yn CHECK ( animal_yn IN ( 'Y', 'N' ) );
--ALTER TABLE ROOM MODIFY pool_able_yn CHECK ( pool_able_yn IN ( 'Y', 'N' ) );

COMMENT ON COLUMN room.room_price IS
    '음수 불가능';

COMMENT ON COLUMN room.capacity IS
    '1이상';

DROP TABLE flight;

CREATE TABLE flight (
    flight_no      NUMBER NOT NULL,
    flight_name    CHAR(6) NOT NULL,
    departure_time VARCHAR(10) NOT NULL,
    arrival_time   VARCHAR(10) NOT NULL,
    airline        VARCHAR2(20) NOT NULL,
    flight_price   NUMBER NOT NULL,
    seat           NUMBER NOT NULL,
    dep_airport    VARCHAR(15) NOT NULL,
    arr_airport    VARCHAR(15) NOT NULL
);

--ALTER TABLE FLIGHT MODIFY departure_time VARCHAR(10);
--ALTER TABLE FLIGHT MODIFY arrival_time VARCHAR(10);

COMMENT ON COLUMN flight.flight_no IS
    '시퀀스 관리';

COMMENT ON COLUMN flight.flight_name IS
    'CHAR(6)? VARCHAR2?';

DROP TABLE member;

CREATE TABLE member (
    member_no   NUMBER NOT NULL,
    id          VARCHAR2(20) UNIQUE NOT NULL,
    pwd         VARCHAR2(20) NOT NULL,
    member_name VARCHAR2(20) NOT NULL,
    member_nick VARCHAR2(20) NOT NULL,
    phone       CHAR(13),
    email       VARCHAR2(30),
    enroll_date TIMESTAMP DEFAULT sysdate NOT NULL,
    quit_yn     CHAR(1) DEFAULT 'N' CHECK ( quit_yn IN ( 'Y', 'N' ) ) NOT NULL,
    point       NUMBER DEFAULT 0 NOT NULL
);

COMMENT ON COLUMN member.member_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN member.quit_yn IS
    '제약조건 CHECK';

DROP TABLE travel;

CREATE TABLE travel (
    travel_no      NUMBER NOT NULL,
    recommend_type NUMBER NOT NULL,
    theme          NUMBER NOT NULL,
    category       NUMBER NOT NULL,
    travel_name    VARCHAR2(40) NOT NULL,
    travel_address VARCHAR2(100) NOT NULL,
    activity_yn    CHAR(1) CHECK ( activity_yn IN ( 'Y', 'N' ) ) NOT NULL,
    travel_price   NUMBER NULL,
    animal_yn      CHAR(1) CHECK ( animal_yn IN ( 'Y', 'N' ) ) NOT NULL,
    travel_info    VARCHAR2(200) NOT NULL,
    travel_phone   VARCHAR2(20) NULL,
    open           VARCHAR2(10) NULL,
    closed         VARCHAR2(10) NULL,
    closed_day     VARCHAR2(40) NULL,
    like_cnt       NUMBER DEFAULT 0 NULL,
    delete_yn      CHAR(1) DEFAULT 'N' CHECK ( delete_yn IN ( 'Y', 'N' ) ) NOT NULL,
    delete_date    TIMESTAMP NULL
);

--ALTER TABLE TRAVEL MODIFY travel_name VARCHAR2(40);
--ALTER TABLE TRAVEL MODIFY travel_phone VARCHAR2(20);

COMMENT ON COLUMN travel.travel_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN travel.activity_yn IS
    '제약조건 CHECK';

COMMENT ON COLUMN travel.animal_yn IS
    '제약조건 CHECK
상세페이지에 추가';

COMMENT ON COLUMN travel.travel_phone IS
    '000-0000-0000';

COMMENT ON COLUMN travel.like_cnt IS
    '좋아요는 시퀀스 말고 셀렉해서 +1 하는 걸로 (시퀀스로 하면 내려가는 기능을 쓸 수 없기 때문에 좋아요 눌렀다가 취소 못함)';

DROP TABLE qna;

CREATE TABLE qna (
    question_no    NUMBER NOT NULL,
    member_no      NUMBER NOT NULL,
    question_title VARCHAR(20) NOT NULL,
    question_time  TIMESTAMP DEFAULT sysdate NOT NULL,
    answer_yn      CHAR(1) DEFAULT 'N' CHECK ( answer_yn IN ( 'Y', 'N' ) ) NOT NULL,
    q_content      VARCHAR(4000) NOT NULL,
    a_content      VARCHAR(4000) NULL,
    modify_time    TIMESTAMP NULL,
    delete_yn      CHAR(1) DEFAULT 'N' CHECK ( delete_yn IN ( 'Y', 'N' ) ) NOT NULL,
    delete_date    TIMESTAMP NULL,
    answer_date    TIMESTAMP NULL
);

COMMENT ON COLUMN qna.question_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN qna.answer_yn IS
    'CHECK 제약조건';

COMMENT ON COLUMN qna.delete_yn IS
    'CHECK제약조건';

DROP TABLE payment;

CREATE TABLE payment (
    pay_no      NUMBER NOT NULL,
    flight_no   NUMBER NOT NULL,
    accom_no    NUMBER NOT NULL,
    car_no      NUMBER NULL,
    total       NUMBER NOT NULL,
    point_used  NUMBER DEFAULT 0 NOT NULL,
    cut_price   NUMBER NULL,
    point       NUMBER NOT NULL,
    pay_method  CHAR(1) NOT NULL,
    pay_date    TIMESTAMP DEFAULT sysdate NOT NULL,
    cancel_yn   CHAR(1) DEFAULT 'N' NULL,
    cancel_date TIMESTAMP NULL
);

COMMENT ON COLUMN payment.pay_no IS
    '시퀀스로관리';

COMMENT ON COLUMN payment.cancel_yn IS
    'CHECK제약조건';

DROP TABLE flight_reservation;

CREATE TABLE flight_reservation (
    flight_no        NUMBER NOT NULL,
    departure_flight NUMBER NOT NULL,
    return_flight    NUMBER NOT NULL,
    member_no        NUMBER NOT NULL,
    reserve_date     DATE DEFAULT sysdate NOT NULL,
    cancel_yn        CHAR(1) DEFAULT 'N' CHECK ( cancel_yn IN ( 'Y', 'N' ) ) NOT NULL,
    cancel_date      TIMESTAMP NULL
);

COMMENT ON COLUMN flight_reservation.member_no IS
    '시퀀스로 관리';

DROP TABLE rental_car;

CREATE TABLE rental_car (
    rental_no NUMBER NOT NULL,
    car_no    NUMBER NOT NULL,
    car_uq    VARCHAR(20) NOT NULL,
    day_price NUMBER NOT NULL,
    openable   CHAR(1) NOT NULL CHECK ( openable IN ( 'Y', 'N' ) )
);

--ALTER TABLE rental_car ADD openable   CHAR(1) NOT NULL CHECK ( openable IN ( 'Y', 'N' ) );

DROP TABLE survey;

CREATE TABLE survey (
    survey_no   NUMBER NOT NULL,
    member_no   NUMBER NOT NULL,
    purpose     NUMBER NOT NULL,
    purpose2    NUMBER NOT NULL,
    location    NUMBER NOT NULL,
    person_group       NUMBER NOT NULL,
    budget      CHAR(1) NOT NULL,
    animal_yn   CHAR(1) NOT NULL,
    survey_date TIMESTAMP DEFAULT sysdate NOT NULL,
    delete_yn   CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON COLUMN survey.survey_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN survey.member_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN survey.delete_yn IS
    'CHECK 제약조건';

DROP TABLE car_reservation;

CREATE TABLE car_reservation (
    car_no       NUMBER NOT NULL,
    rental_no    NUMBER NOT NULL,
    member_no    NUMBER NOT NULL,
    rental_date  DATE NOT NULL,
    return_date  DATE NOT NULL,
    reserve_date DATE DEFAULT sysdate NOT NULL,
    insurance    VARCHAR(20) NULL,
    cancel_date  TIMESTAMP NULL,
    cancel_yn    CHAR(1) DEFAULT 'N' CHECK ( cancel_yn IN ( 'Y', 'N' ) ) NULL
);

COMMENT ON COLUMN car_reservation.member_no IS
    '시퀀스로 관리';

DROP TABLE accom_reservation;

CREATE TABLE accom_reservation (
    accom_no     NUMBER NOT NULL,
    room_no      NUMBER NOT NULL,
    member_no    NUMBER NOT NULL,
    check_in     TIMESTAMP NOT NULL,
    check_out    TIMESTAMP NOT NULL,
    reserve_date TIMESTAMP DEFAULT sysdate NOT NULL,
    cancel_yn    CHAR(1) DEFAULT 'N' NOT NULL CHECK ( cancel_yn IN ( 'N', 'Y' ) ),
    cancel_date  TIMESTAMP NULL
);

COMMENT ON COLUMN accom_reservation.member_no IS
    '시퀀스로 관리';

DROP TABLE car;

CREATE TABLE car (
    car_no     NUMBER NOT NULL,
    fuel       NUMBER NOT NULL,
    car_name   VARCHAR2(20) NOT NULL,
    car_size   VARCHAR2(20) NOT NULL,
    car_person NUMBER NOT NULL
);

--ALTER TABLE CAR DROP COLUMN openable;

DROP TABLE accom;

CREATE TABLE accom (
    accom_no      NUMBER NOT NULL,
    accom_around  NUMBER NOT NULL,
    accom_name    VARCHAR2(40) NOT NULL,
    accom_address VARCHAR(100) NOT NULL,
    pool_yn       CHAR(1) DEFAULT 'N' NOT NULL CHECK ( pool_yn IN ( 'Y', 'N' ) ),
    type          CHAR(1) NOT NULL CHECK ( type IN ( 'H', 'G' ) )
);

--ALTER TABLE accom MODIFY pool_yn CHECK ( pool_yn IN ( 'Y', 'N' ) );

COMMENT ON COLUMN accom.accom_no IS
    '시퀀스로 관리';

COMMENT ON COLUMN accom.accom_address IS
    '제주시, 서귀포시 구분';

COMMENT ON COLUMN accom.type IS
    '호텔 H, 게스트하우스 G';

DROP TABLE person_group;

CREATE TABLE person_group (
    no    NUMBER NOT NULL,
    person_group VARCHAR2(30) NOT NULL
);

DROP TABLE fuel;

CREATE TABLE fuel (
    fuel_no NUMBER NOT NULL,
    fuel    VARCHAR(10) NOT NULL
);

DROP TABLE purpose;

CREATE TABLE purpose (
    no      NUMBER NOT NULL,
    purpose VARCHAR2(10) NOT NULL
);

DROP TABLE accom_ar_info;

CREATE TABLE accom_ar_info (
    accom_ar_no NUMBER NOT NULL,
    accom_ar    VARCHAR2(10) NULL
);

COMMENT ON COLUMN accom_ar_info.accom_ar IS
    '논밭, 바닷가, 도시, 숲속';

DROP TABLE location;

CREATE TABLE location (
    no       NUMBER NOT NULL,
    location VARCHAR2(20) NOT NULL
);

--ALTER TABLE LOCATION MODIFY LOCATION VARCHAR2(20);

DROP TABLE room_view_info;

CREATE TABLE room_view_info (
    room_view_no   NUMBER NOT NULL,
    room_view_info VARCHAR2(20) NULL
);

--ALTER TABLE ROOM_VIEW_INFO MODIFY ROOM_VIEW_INFO VARCHAR2(20);

COMMENT ON COLUMN room_view_info.room_view_info IS
    '오션, 마운틴, 시티';

DROP TABLE category;

CREATE TABLE category (
    no       NUMBER NOT NULL,
    category VARCHAR(20) NOT NULL
);

ALTER TABLE room ADD CONSTRAINT pk_room PRIMARY KEY ( room_no );

ALTER TABLE flight ADD CONSTRAINT pk_flight PRIMARY KEY ( flight_no );

ALTER TABLE member ADD CONSTRAINT pk_member PRIMARY KEY ( member_no );

ALTER TABLE travel ADD CONSTRAINT pk_travel PRIMARY KEY ( travel_no );

ALTER TABLE qna ADD CONSTRAINT pk_qna PRIMARY KEY ( question_no );

ALTER TABLE payment ADD CONSTRAINT pk_payment PRIMARY KEY ( pay_no );

ALTER TABLE flight_reservation ADD CONSTRAINT pk_flight_reservation PRIMARY KEY ( flight_no );

ALTER TABLE rental_car ADD CONSTRAINT pk_rental_car PRIMARY KEY ( rental_no );

ALTER TABLE survey ADD CONSTRAINT pk_survey PRIMARY KEY ( survey_no );

ALTER TABLE car_reservation ADD CONSTRAINT pk_car_reservation PRIMARY KEY ( car_no );

ALTER TABLE accom_reservation ADD CONSTRAINT pk_accom_reservation PRIMARY KEY ( accom_no );

ALTER TABLE car ADD CONSTRAINT pk_car PRIMARY KEY ( car_no );

ALTER TABLE accom ADD CONSTRAINT pk_accom PRIMARY KEY ( accom_no );

ALTER TABLE person_group ADD CONSTRAINT pk_group PRIMARY KEY ( no );

ALTER TABLE fuel ADD CONSTRAINT pk_fuel PRIMARY KEY ( fuel_no );

ALTER TABLE purpose ADD CONSTRAINT pk_purpose PRIMARY KEY ( no );

ALTER TABLE accom_ar_info ADD CONSTRAINT pk_accom_ar_info PRIMARY KEY ( accom_ar_no );

ALTER TABLE location ADD CONSTRAINT pk_location PRIMARY KEY ( no );

ALTER TABLE room_view_info ADD CONSTRAINT pk_room_view_info PRIMARY KEY ( room_view_no );

ALTER TABLE category ADD CONSTRAINT pk_category PRIMARY KEY ( no );

ALTER TABLE room
    ADD CONSTRAINT fk_accom_to_room_1 FOREIGN KEY ( accom_no )
        REFERENCES accom ( accom_no );

ALTER TABLE room
    ADD CONSTRAINT fk_room_view_info_to_room_1 FOREIGN KEY ( room_view )
        REFERENCES room_view_info ( room_view_no );

ALTER TABLE travel
    ADD CONSTRAINT fk_group_to_travel_1 FOREIGN KEY ( recommend_type )
        REFERENCES person_group ( no );

ALTER TABLE travel
    ADD CONSTRAINT fk_purpose_to_travel_1 FOREIGN KEY ( theme )
        REFERENCES purpose ( no );

ALTER TABLE travel
    ADD CONSTRAINT fk_category_to_travel_1 FOREIGN KEY ( category )
        REFERENCES category ( no );

ALTER TABLE qna
    ADD CONSTRAINT fk_member_to_qna_1 FOREIGN KEY ( member_no )
        REFERENCES member ( member_no );

ALTER TABLE payment
    ADD CONSTRAINT fk_flight_reservation_to_payment_1 FOREIGN KEY ( flight_no )
        REFERENCES flight_reservation ( flight_no );

ALTER TABLE payment
    ADD CONSTRAINT fk_accom_reservation_to_payment_1 FOREIGN KEY ( accom_no )
        REFERENCES accom_reservation ( accom_no );

ALTER TABLE payment
    ADD CONSTRAINT fk_car_reservation_to_payment_1 FOREIGN KEY ( car_no )
        REFERENCES car_reservation ( car_no );

ALTER TABLE flight_reservation
    ADD CONSTRAINT fk_flight_to_flight_reservation_1 FOREIGN KEY ( departure_flight )
        REFERENCES flight ( flight_no );

ALTER TABLE flight_reservation
    ADD CONSTRAINT fk_flight_to_flight_reservation_2 FOREIGN KEY ( return_flight )
        REFERENCES flight ( flight_no );

ALTER TABLE flight_reservation
    ADD CONSTRAINT fk_member_to_flight_reservation_1 FOREIGN KEY ( member_no )
        REFERENCES member ( member_no );

ALTER TABLE rental_car
    ADD CONSTRAINT fk_car_to_rental_car_1 FOREIGN KEY ( car_no )
        REFERENCES car ( car_no );

ALTER TABLE survey
    ADD CONSTRAINT fk_member_to_survey_1 FOREIGN KEY ( member_no )
        REFERENCES member ( member_no );

ALTER TABLE survey
    ADD CONSTRAINT fk_purpose_to_survey_1 FOREIGN KEY ( purpose )
        REFERENCES purpose ( no );

ALTER TABLE survey
    ADD CONSTRAINT fk_purpose_to_survey_2 FOREIGN KEY ( purpose2 )
        REFERENCES purpose ( no );

ALTER TABLE survey
    ADD CONSTRAINT fk_location_to_survey_1 FOREIGN KEY ( location )
        REFERENCES location ( no );

ALTER TABLE survey
    ADD CONSTRAINT fk_group_to_survey_1 FOREIGN KEY ( person_group )
        REFERENCES person_group ( no );

ALTER TABLE car_reservation
    ADD CONSTRAINT fk_rental_car_to_car_reservation_1 FOREIGN KEY ( rental_no )
        REFERENCES rental_car ( rental_no );

ALTER TABLE car_reservation
    ADD CONSTRAINT fk_member_to_car_reservation_1 FOREIGN KEY ( member_no )
        REFERENCES member ( member_no );

ALTER TABLE accom_reservation
    ADD CONSTRAINT fk_room_to_accom_reservation_1 FOREIGN KEY ( room_no )
        REFERENCES room ( room_no );

ALTER TABLE accom_reservation
    ADD CONSTRAINT fk_member_to_accom_reservation_1 FOREIGN KEY ( member_no )
        REFERENCES member ( member_no );

ALTER TABLE car
    ADD CONSTRAINT fk_fuel_to_car_1 FOREIGN KEY ( fuel )
        REFERENCES fuel ( fuel_no );

ALTER TABLE accom
    ADD CONSTRAINT fk_accom_ar_info_to_accom_1 FOREIGN KEY ( accom_around )
        REFERENCES accom_ar_info ( accom_ar_no );

DROP SEQUENCE SEQ_ROOM;
CREATE SEQUENCE SEQ_ROOM NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_FLIGHT;
CREATE SEQUENCE SEQ_FLIGHT NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_MEMBER;
CREATE SEQUENCE SEQ_MEMBER NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_TRAVEL;
CREATE SEQUENCE SEQ_TRAVEL NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_QnA;
CREATE SEQUENCE SEQ_QnA NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_PAYMENT;
CREATE SEQUENCE SEQ_PAYMENT NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_FLIGHT_RESERVATION;
CREATE SEQUENCE SEQ_FLIGHT_RESERVATION NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_RENTAL_CAR;
CREATE SEQUENCE SEQ_RENTAL_CAR NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_SURVEY;
CREATE SEQUENCE SEQ_SURVEY NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_CAR_RESERVATION;
CREATE SEQUENCE SEQ_CAR_RESERVATION NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_ACCOM_RESERVATION;
CREATE SEQUENCE SEQ_ACCOM_RESERVATION NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_CAR;
CREATE SEQUENCE SEQ_CAR NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_ACCOM;
CREATE SEQUENCE SEQ_ACCOM NOCACHE NOCYCLE;

--고정값 시퀀스 굳이...?
--DROP SEQUENCE SEQ_FUEL;
--CREATE SEQUENCE SEQ_FUEL NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_ACCOM_AR_INFO;
--CREATE SEQUENCE SEQ_ACCOM_AR_INFO NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_ROOM_VIEW_INFO;
--CREATE SEQUENCE SEQ_ROOM_VIEW_INFO NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_CATEGORY;
--CREATE SEQUENCE SEQ_CATEGORY NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_PURPOSE;
--CREATE SEQUENCE SEQ_PURPOSE NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_GROUP;
--CREATE SEQUENCE SEQ_GROUP NOCACHE NOCYCLE;
--
--DROP SEQUENCE SEQ_LOCATION;
--CREATE SEQUENCE SEQ_LOCATION NOCACHE NOCYCLE;

--그룹(인원정보?), 목적, 카테고리
INSERT INTO person_group VALUES(1, '가족');
INSERT INTO person_group VALUES(2, '연인');
INSERT INTO person_group VALUES(3, '친구');
INSERT INTO person_group VALUES(4, '혼자');
INSERT INTO person_group VALUES(5, '연인, 친구');
INSERT INTO person_group VALUES(6, '가족, 연인, 친구');
INSERT INTO person_group VALUES(7, '가족, 연인, 친구, 혼자');
INSERT INTO person_group VALUES(8, '친구, 혼자');

INSERT INTO PURPOSE VALUES(1, '쇼핑');
INSERT INTO PURPOSE VALUES(2, '휴식');
INSERT INTO PURPOSE VALUES(3, '관광');
INSERT INTO PURPOSE VALUES(4, '경험');
INSERT INTO PURPOSE VALUES(5, '식도락');

INSERT INTO CATEGORY VALUES(1, '관광지');
INSERT INTO CATEGORY VALUES(2, '맛집');
INSERT INTO CATEGORY VALUES(3, '엑티비티');
INSERT INTO CATEGORY VALUES(4, '카페');

INSERT INTO ACCOM_AR_INFO VALUES(1, '바다');
INSERT INTO ACCOM_AR_INFO VALUES(2, '산');
INSERT INTO ACCOM_AR_INFO VALUES(3, '도시');
INSERT INTO ACCOM_AR_INFO VALUES(4, '공항');

INSERT INTO ROOM_VIEW_INFO VALUES(1, '시티뷰');
INSERT INTO ROOM_VIEW_INFO VALUES(2, '오션뷰');
INSERT INTO ROOM_VIEW_INFO VALUES(3, '마운틴뷰');
INSERT INTO ROOM_VIEW_INFO VALUES(4, '파크뷰');

INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '마리나제주', '제주특별자치도 제주시 특별자치도, 금능남로 127', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '엠버서터 호텔', '제주 서귀포시 중문관광로72번길 75', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '더퍼스트 제주', '제주 제주시 도령로 83', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '스카이브 호텔', '제주특별자치도 제주시 노연로 12', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '브릿지 호텔', '제주 서귀포시 1100로 453-95 WE호텔', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '나이스웨더', '제주 서귀포시 중문관광로72번길 100', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '블루아일랜드', '제주 서귀포시 안덕면 산록남로 863', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '마운틴제주', '제주 서귀포시 막숙포로 118', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '라온제나', '제주 제주시 애월읍 애월해안로 394', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '수피아', '제주 서귀포시 동홍로 7', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '그랜트 인 제주', '제주 서귀포시 동문로 42', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '제이로인', '제주 제주시 애월읍 애월해안로 554-6', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '제주로올래', '제주 서귀포시 태평로 363 헤이 서귀포', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '만다린러브', '제주 서귀포시 성산읍 고성오조로 94', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '그랜드 하얏트', '제주시 그랜읍 하얏리 443', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, 'KH호텔', '제주시 테헤란로 123-2', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '1DRAGON', '제주시 드라곤로 1', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '칼 호텔', '서귀포시 표선면 813', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '토스카나 호텔', '서귀포시 안덕면 29', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '댕냥호텔', '서귀포시 꼬리면 귀엽리 486', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '제주 배드라이도', '제주시 구좌읍 293', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '혼저옵서예', '제주시 조천읍 003', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '예스존', '제주시 추자면 3', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '바우잰', '제주시 한림읍 12', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '미르가온', '제주시 미르로 2', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '투용', '제주시 투용동 22', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '재주좋조', '서귀포시 우리읍 잘하리', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '가온누리', '서귀포시 성주청 32', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '백패커스', '서귀포시 남원읍 200', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '올래스테이', '서귀포시 대정읍 30', 'N', 'G');

INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 4, '스탠다드싱글룸', 180000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '킹룸', 320000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '패밀리룸', 430000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '더블룸', 290000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '스탠다드더블룸', 68000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '디럭스 트윈룸', 89000, 3, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '디럭스 트리플룸', 110000, 3, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '디럭스 패밀리룸', 150000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '수페리어 싱글룸', 580000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '수페리어 트윈룸', 730000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 3, '디럭스 킹룸', 810000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 3, '수페리어 패밀리룸', 1100000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '프리미어 패밀리룸', 1390000, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '디럭스 더블룸', 120000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 4, '디럭스 트윈룸', 150000, 3, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 2, '스위트 패밀리룸', 230000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '슈퍼 패밀리룸', 410000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '스탠다드 싱글룸', 28000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '스탠다드 트윈룸', 35000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 2, '디럭스 킹룸', 45000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 4, '스위트 패밀리룸', 53000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 4, '1인실', 23000, 1, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 7, 2, '1인실', 50000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 8, 3, '1인실', 38000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 9, 4, '2인실', 62000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 10, 1, '1인실', 19000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 11, 2, '1인실', 89000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 12, 3, '2인실', 47000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 13, 1, '1인실', 99000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 14, 2, '1인실', 41000, 1, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '트윈 룸 시티뷰', 500000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '트윈 룸 오션뷰', 550000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '킹 룸 시티뷰', 600000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '킹 룸 오션뷰', 650000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '그랜드 스위트 트윈', 900000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '352강의장', 400000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '541강의장', 400000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '상담실', 200000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '취업반', 800000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '1용', 600000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '2용', 800000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '3용', 1000000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '스탠다드 온돌', 300000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 1, '스탠다드 트윈', 300000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '디럭스 트윈', 400000, 2, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '디럭스 패밀리', 600000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 2, '리트리버는 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 3, '비숑도 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 1, '코숏은 더 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 2, '러시안블루도 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 7, 3, '6인실', 100000, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 8, 1, '6인실', 200000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 9, 1, '6인실', 50000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 10, 1, '4인실', 30000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 11, 3, '4인실', 100000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 12, 2, '4인실', 222222, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 13, 2, '6인실', 333333, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 14, 2, '6인실', 30000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 15, 1, '6인실', 30000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 16, 1, '4인실', 40000, 4, 'Y', 'N');

INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '성산일출봉', '제주 서귀포시 성산읍 성산리 1', 'N', 5000, 'N', '유네스코 세계 자연 유산에 등재된, 제주 최고의 일출 명소',  '064-783-0959', '07:00', '20:00', '매월 첫번째 월', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 1, 1, '동문재래시장', '제주 제주시 관덕로14길 20', 'N', NULL, 'Y', '먹거리와 생활용품을 판매하는, 제주에서 가장 큰 시장',  '064-752-3001', '08:00', '00:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 2, 1, '카멜리아 힐', '제주 서귀포시 안덕면 병악로 166', 'N', 9000, 'Y', '세계 80개국의 동백나무를 볼 수 있는 동백 수목원',  '0507-1340-0175', '09:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '함덕 해수욕장', '제주 제주시 조천읍 조함해안로 525', 'Y', NULL, 'Y', '에메랄드빛 바다와 백사장을 갖춘 한국의 몰디브',  '064-728-3989', NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 1, '아르떼 뮤지엄', '제주 제주시 애월읍 어림비로 478', 'N', 17000, 'N', '시각적 강렬함을 선사하는 국내 최대 규모의 미디어 아트 전시관',  '1899-5008', '10:00', '20:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '새별 오름', '제주 제주시 애월읍 봉성리 산59-8', 'Y', NULL, 'Y', '황금빛 억새가 휘날리는 아름다운 풍경을 마주할 수 있는 오름',  NULL, NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 1, '오설록 티 뮤지엄', '제주 서귀포시 안덕면 신화역사로 15 오설록', 'N', NULL, 'N', '우리나라의 전통차 문화를 소개하는 녹차 박물관',  '064-794-5312', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '사려니 숲길', '제주 제주시 조천읍 교래리 산137-1', 'N', NULL, 'N', '시원한 나무 그늘 아래, 맑은 공기를 마시며 걷기 좋은 숲길',  '064-900-8800', '09:00', '17:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 1, '아쿠아 플라넷 제주', '제주 서귀포시 성산읍 섭지코지로 95 아쿠아플라넷 제주', 'N', 40000, 'N', '아시아 최대 규모를 자랑하는 해양 테마 파크',  '1833-7001', '10:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '금능 해수욕장', '제주 제주시 한림읍 금능리', 'Y', NULL, 'Y', '투명한 바다에서 물놀이와 스노쿨링을 즐길 수 있는 해수욕장',  '064-728-3983', NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '중문 대포 주상절리대', '제주 서귀포시 이어도로 36-24', 'N', 2000, 'N', '대한민국 천연기념물 제 443호로 지정된 대규모 주상절리대',  '064-738-1521', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 2, 1, '안돌 오름 편백나무 숲', '제주 제주시 구좌읍 송당리 산66-2', 'N', 3000, 'N', '상쾌한 피톤치드 향기를 맡으며 힐링하기 좋은 숲',  NULL, '09:00', '18:30', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 2, 1, '보롬왓', '제주 서귀포시 표선면 번영로 2350-104', 'N', 5000, 'Y', '예쁜 꽃밭을 구경하며 자연 속에서 힐링할 수 있는 공간',  '010-7362-2345', '09:00', '18:00', '동절기', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '동백 포레스트', '제주 서귀포시 남원읍 생기악로 53-38', 'N', 4000, 'Y', '매년 겨울 따듯한 커피와 함께 동백꽃을 감상할 수 있는 곳',  '0507-1331-2102', '09:00', '18:00', '월요일', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '쇠소깍', '제주 서귀포시 쇠소깍로 104', 'Y', NULL, 'Y', '맑은 물과 기암괴석이 만들어낸 풍경을 볼 수 있는 하구',  '064-732-9998', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 1, 1, '서귀포 매일 올레시장', '제주 서귀포시 서귀동 340', 'N', NULL, 'Y', '다양한 농수산물,잡화,먹거리를 판매하는 재래 시장',  '0507-1353-1949', '07:00', '21:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '도두동 무지개 해안도로', '제주특별자치도 제주시 도두일동  1734', 'N', NULL, 'Y', '무지개색으로 칠해진 방호벽이 자리한 해안도로',  NULL, NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 1, 4, 1, '스누피 가든', '제주 제주시 구좌읍 금백조로 930', 'N', 18000, 'N', '귀여운 캐릭터 스누피로 가득 찬 아기자기한 테마 가든',  '064-903-1111', '09:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '송악산 둘레길', '제주특별자치도 서귀포시 대정읍 상모리 245', 'N', NULL, 'Y', '독특한 화산 지형과 아름다운 경치를 감상할 수 있는 곳',  NULL, NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '천지연 폭포', '제주 서귀포시 천지동 667-7', 'N', 2000, 'N', '웅장한 자연에 둘러싸여 거센 물줄기를 내뿜는 폭포',  '064-733-1528', '09:00', '22:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '산굼부리', '제주 제주시 조천읍 비자림로 768', 'N', 6000, 'N', '여러 희귀 식물이 서식하는, 천연기념물로 지정된 분화구',  '064-783-9900', '09:00', '18:40', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '월정리 해변', '제주 제주시 구좌읍 월정리 33-3', 'Y', NULL, 'Y', '달이 머무는 곳 월정리에 자리한 해변',  NULL, NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '정방폭포', '제주 서귀포시 칠십리로214번길 37', 'N', 2000, 'N', '시원한 물줄기를 자랑하는 동양 유일의 해안 폭포',  '064-733-1530', '09:00', '18:20', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 1, '빛의 벙커', '제주 서귀포시 성산읍 고성리 2039-22', 'N', 18000, 'N', '몰입형 미디어 전시가 열리는, 지하 벙커를 개조한 전시관',  '1522-2653', '10:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 3, 1, '한라산 국립 공원', '제주 서귀포시 토평동 산15-1', 'N', NULL, 'N', '유네스코 세계 자연 유산에 등재된, 대한민국에서 가장 높은 산',  '064-713-9950', NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 2, 1, '협재 해수욕장', '제주 제주시 한림읍 협재리 2497-1', 'Y', NULL, 'Y', '올레 14코스에 속하는,일몰이 아름다운 해수욕장',  '064-728-3981', NULL, NULL, NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 4, 3, '월정투명카약', '제주시 구좌읍 월정리 1400-33', 'Y', 15000, 'Y', '애완견도 함께 탈수있는 투명카약',  '010-8638-6491', '10:00', '17:00', '화', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 4, 3, '제주서핑스쿨', '서귀포시 색달동 2272', 'Y', 60000, 'N', '초보자도 쉽게 즐길 수 있는 서핑',  '010-4267-2567', '08:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 4, 3, '로로패들보드', '제주시 구좌읍 월정리 33', 'Y', 40000, 'Y', '잔잔한 바다에서 즐기는 패들보트',  '010-2893-2938', '08:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 4, 3, '차귀도 선상낚시', '제주시 한경면 노을해안로 1160', 'Y', 12000, 'N', '차귀도에서 즐기는 낚시체험',  '010-9727-3782', '09:00', '17:00', '월', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '새별레져 ATV', '제주시 한림읍 금악리 산30-4', 'Y', 40000, 'N', '오프로드를 따라 달리는 ATV',  '010-3829-4567', '10:00', '18:00', '수', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 4, 3, '세화꽃길만걷차 전기자전거', '제주시 구좌읍 해맞이해안로 1382', 'Y', 15000, 'N', '해안가를 따라 자전거 여행',  '010-7683-7766', '10:00', '20:00', '화', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 4, 4, 3, '한라하이킹 스쿠터', '제주시 용두암길 50-1', 'Y', 40000, 'N', '해안가따라 스쿠터 여행',  '010-0393-0099', '08:00', '20:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 4, 3, '오쉐어 한라산등반', '제주시 용문로 4 1층 오쉐어', 'Y', 18000, 'N', '영실코스 장비 대여',  '010-0288-4445', '06:00', '16:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '제주 레일바이크', '제주시 구좌읍 용눈이오름로 641', 'Y', 20000, 'N', '용눈이 오름이 보이는 레일바이크 코스',  '010-3678-9298', '09:00', '18:00', '수', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '윈드1947 카트', '서귀포시 토평공단로 78-27', 'Y', 25000, 'N', '코스를 따라 도는 카트',  '010-4783-8889', '10:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '981파크 레이싱', '제주시 애월읍 천덕로 880-24', 'Y', 30000, 'N', '경사를 따라 내려가는 카트',  '010-7329-4789', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 4, 3, '바다하늘 패러글라이딩', '제주시 한림읍 한창로 1295', 'Y', 89000, 'N', '금오름이 보이는 패러글라이딩',  '010-3728-9292', '10:00', '18:00', '화', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 4, 3, '법환해녀체험센터', '서귀포시 최영로 10', 'Y', 40000, 'N', '각종 해산물을 채취할 수 있는 기회',  '010-2898-0377', '09:00', '15:00', '수', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 4, 4, 3, '조르다 요가', '제주시 조천읍 조함해안로 586', 'Y', 40000, 'Y', '노을과 해변이 보이는 곳에서의 요가',  '010-9090-4376', '17:00', '21:00', '일', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '더퍼플 스쿠버다이빙', '서귀포시 이어도로 1028-14', 'Y', 50000, 'N', '아름다운 바닷속 탐험',  '010-2442-9949', '08:00', '20:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '하례감귤체험농장', '서귀포시 남원읍 중산간동로 6596-3', 'Y', 9900, 'N', '감귤농장에서 직접따먹는 신선한 귤',  '010-2902-1112', '09:30', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '우도 모터보트', '제주시 우도면 연평리 2562', 'Y', 15000, 'N', '모터보트타고 신비한 동굴탐험',  '010-5289-9992', '10:00', '16:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '쇠와꽃 승마장', '서귀포시 성산읍 섭지코지로25번길 88-17', 'Y', 18000, 'N', '말타고 따그닥따그닥',  '010-3268-3439', '09:00', '17:30', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '왕복짚라인 제주', '제주시 해안마을북길 14-5', 'Y', 28000, 'N', '멋진 경치와 시원한 바람의 짚라인',  '010-2589-2366', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 4, 3, '메이즈랜드 미로공원', '제주시 구좌읍 비자림로 2134-47 메이즈랜드', 'Y', 11000, 'N', '돌,바람,여자로 구성된 미로공원',  '010-4720-1577', '09:00', '18:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 4, '새빌', '제주 제주시 애월읍 평화로 1529', 'N', 5500, 'Y', '네이버 선정 대한민국 6대 이색 카페에 선정된 새별 오름의 아름다운 자연 경관을 볼 수 있는 카페',  '064-794-0073', '09:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 4, '몽상 드 애월', '제주 제주시 애월읍 애월북서길 56-1', 'N', 8000, 'Y', '지드래곤 카페로 유명한 통유리창을 통해 오션 뷰를 바라볼 수 있는 전망 좋은 카페',  '064-799-8900', '09:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '카페 델문도', '제주 제주시 조천읍 조함해안로 519-10', 'N', 7000, 'N', '함덕 해수욕장 근처 카페로 오션 뷰를 바라볼 수 있는 분위기 좋은 카페',  '064-702-0007', '07:00', '00:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '베케', '제주 서귀포시 효돈로 54', 'N', 5500, 'N', '숲속에 온 듯한 느낌을 자아내는 혼자만 알고 싶은 가든 뷰 카페',  '064-732-3828', '10:00', '18:00', '화요일', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 4, '귤꽃다락', '제주 서귀포시 이어도로1072번길 34', 'N', 5500, 'Y', '70년대 창고를 개조한 복고 감성의 예쁜 카페',  '064-739-2323', '11:30', '18:30', '수요일', 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 4, '랜디스 도넛', '제주 제주시 애월읍 애월로 27-1', 'N', 4800, 'N', '제주 유명한 도넛 전문점',  '064-799-0610', '10:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '원 앤 온리', '제주 서귀포시 안덕면 산방로 141', 'N', 7000, 'N', '배틀트립에서 다녀간 산방산의 뷰를 감상할 수 있는 이국적인 느낌의 카페',  '010-9910-2527', '09:00', '20:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 5, 4, '우무 본점', '제주 제주시 한림읍 한림로 542-1', 'N', 6300, 'N', '우뭇가사리로 만든 수제 푸딩이 유명한 제주 유명 디저트 카페',  '010-4471-0064', '10:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '오드랑 베이커리', '제주 제주시 조천읍 조함해안로 552-3', 'N', 3500, 'N', '각양각색의 다양한 빵을 판매하는 베이커리',  '064-784-5404', '07:00', '22:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 4, '오설록 티 하우스', '제주 서귀포시 안덕면 신화역사로 15', 'N', 6000, 'N', '제주산 유기농 녹차로 만든 다양한 디저트와 음료를 맛볼 수 있는 카페',  '064-794-5312', '09:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '봄날', '제주 제주시 애월읍 애월로1길 25', 'N', 5500, 'N', '드라마 맨도롱 또똣의 촬영지로 유명한 바다가 보이는 전망 좋은 카페',  '064-799-4999', '09:00', '21:30', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 4, '마노르 블랑', '제주 서귀포시 안덕면 일주서로2100번길 46', 'N', 7000, 'N', '꽃으로 가득한 정원 속의 예쁜 카페',  '064-794-0999', '10:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 4, '명월 국민학교', '제주 제주시 한림읍 명월로 48', 'N', 5000, 'Y', '폐교를 개조한 이색 카페',  '070-8803-1955', '11:00', '19:00', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 4, 5, 4, 'KH카페', '제주 제주시 요정로', 'N', NULL, 'Y', '공부가 잘 되는 카페', '064-111-2222', '09:00', '15:20', NULL, 0, 'N', NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 5, 2, '오는 정 김밥', '제주서귀포시 동문동로 2','N',4000,'N','맛있는 녀석들에 소개된 자꾸 생각나는 제주도 김밥 맛집','064-762-8927','10:00','20:00','일요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '우진 해장국', '제주 제주시 서사로 11','N',10000,'N','수요미식회에 소개된, 제주 여행 시 뜨끈한 국물이 먹고 싶다면 꼭 들려야 하는 맛집','064-757-3393','6:00','22:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '올래 국수 본점', '제주 제주시 귀아랑길 24','N',9000,'N','수요미식회에 소개된, 고기 국수 하나로 승부하는 맛집','064-742-7355','8:30','15:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '연돈', '제주 서귀포시 일주서로 968-10','N',90000,'N','백종원의 골목식당 에서 극찬 받은 흑돼지 돈가스 맛집','064-738-7060','12:00','21:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '표선갈치어장', '제주 서귀포시 표선면 표선당포로 27','N',100000,'N','푸짐한 갈치 요리를 맛보고 싶다면 꼭 와야하는 갈치 요리 전문점','064-787-7118','10:00','22:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '흑돈가', '제주 제주시 한라대학로 11','N',18000,'N','숯불 향을 가득 입힌 흑돼지고기를 맛볼 수 있는 흑돼지 전문 맛집','064-747-0088','11:00','24:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '흑본오겹', '제주 제주시 서해안로 382','N',22000,'N','제주산 흑돼지고기를 맛볼 수 있는 오션 뷰 맛집','064-749-7756','13:00','22:30','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '섭지코지로', '제주 서귀포시 성산읍 시흥하동로57번길 35','N',35000,'Y','신선한 딱새우 회와 고등어 회로 유명한 맛집','064-782-3450','12:00','23:00',NULL,0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '버드나무집', '제주 제주시 조천읍 신북로 540','N',12000,'N','도민 맛집으로 인정된 칼국수 맛집','064-782-9992','10:00','21:00','목요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '동백별장', '제주 제주시 원노형3길 44','N',20000,'Y','동백을 테마로 한 다양한 퓨전 요리를 맛볼 수 있는 분위기 좋은 주점','010-8885-7876','17:00','24:00','화요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '513텐동', '제주 제주시 테우해안로 46 2층','N',15000,'Y','텐동이 맛있고 바다가 보이는 전망 좋은 일식당','010-9492-4345','11:30','21:30','화요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '봉구식탁', '제주 제주시 애월읍 유수암평화길 14-3 1층','N',20000,'Y','바다가 보이는 분위기 좋은 스테이크 맛집','064-799-5909','11:30','20:00','월요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '떡하니', '제주 제주시 구좌읍 행원로9길 9-5','N',10000,'Y','푸짐한 문어 즉석 떡볶이 맛집','010-4200-1566','11:30','17:30','화요일-수요일',0,'N',NULL);
commit;

INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0001','06:30','07:20','요정에어', 60000,80,'김포공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0001','09:45','10:45','인어항공', 264110,120,'김포공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0001','14:00','15:00','제주피어', 224870,100,'김포공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0002','18:00','19:00','인어항공', 269850,120,'김포공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0005','20:15','21:15','요정에어', 154110,80,'김포공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0007','15:30','16:40','인어항공', 256980,120,'김해공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0008','17:15','18:45','요정에어', 170050,80,'김해공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0009','10:30','11:30','제주피어', 145200,100,'김해공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0010','16:45','18:00','제주피어', 121150,100,'김해공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0011','01:10','02:00','요정에어', 50000,80,'김해공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0013','12:00','13:00','제주피어', 164320,100,'청주공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0014','11:15','12:15','인어항공', 301000,120,'청주공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0015','13:30','14:40','요정에어', 124100,80,'청주공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0016','19:00','20:00','제주피어', 140600,100,'청주공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0017','08:45','09:45','인어항공', 201100,120,'청주공항','제주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0018','07:30','08:30','요정에어', 70000,80,'제주공항','김해공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0019','10:40','11:40','인어항공', 180000,120,'제주공항','김해공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0020','14:00','15:00','제주피어', 222040,100,'제주공항','김해공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0021','18:00','19:00','인어항공', 285420,120,'제주공항','김해공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0022','20:20','21:20','요정에어', 172540,80,'제주공항','김해공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0023','15:30','16:30','인어항공', 300420,120,'제주공항','청주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0025','17:15','18:15','요정에어', 175785,80,'제주공항','청주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0028','10:30','11:30','제주피어', 143785,100,'제주공항','청주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0031','16:45','17:45','제주피어', 129860,100,'제주공항','청주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0032','01:30','02:50','요정에어', 94870,80,'제주공항','청주공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0033','12:00','13:00','제주피어', 186250,100,'제주공항','김포공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0035','11:15','12:15','인어항공', 264780,120,'제주공항','김포공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'FR0037','13:30','14:30','요정에어', 123480,80,'제주공항','김포공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'JP0039','19:00','20:00','제주피어', 142365,100,'제주공항','김포공항');
INSERT INTO FLIGHT VALUES(SEQ_FLIGHT.NEXTVAL,'MM0040','08:50','10:00','인어항공', 213650,120,'제주공항','김포공항');

CREATE TABLE FLIGHT_TIME(
    FLIGHT_TIME_NO NUMBER PRIMARY KEY
    , FLIGHT_NO NUMBER NOT NULL
    , DEPARTURE_DATE TIMESTAMP NOT NULL
    , ARRIVAL_DATE TIMESTAMP NOT NULL
    , FOREIGN KEY (FLIGHT_NO) REFERENCES FLIGHT(FLIGHT_NO)
);

ALTER TABLE FLIGHT_RESERVATION DROP CONSTRAINT FK_FLIGHT_TO_FLIGHT_RESERVATION_1;

ALTER TABLE FLIGHT_RESERVATION
    ADD CONSTRAINT FK_FLIGHT_TO_FLIGHT_RESERVATION_1 FOREIGN KEY ( DEPARTURE_FLIGHT )
        REFERENCES FLIGHT_TIME ( FLIGHT_TIME_NO );
        
ALTER TABLE FLIGHT_RESERVATION DROP CONSTRAINT FK_FLIGHT_TO_FLIGHT_RESERVATION_2;

ALTER TABLE FLIGHT_RESERVATION
    ADD CONSTRAINT FK_FLIGHT_TO_FLIGHT_RESERVATION_2 FOREIGN KEY ( RETURN_FLIGHT )
        REFERENCES FLIGHT_TIME ( FLIGHT_TIME_NO );
        
DROP SEQUENCE SEQ_FLIGHT_TIME;
CREATE SEQUENCE SEQ_FLIGHT_TIME NOCACHE NOCYCLE;


INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE, SYSDATE);
INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+1, SYSDATE+1);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+2, SYSDATE+2);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+3, SYSDATE+3);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+4, SYSDATE+4);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+5, SYSDATE+5);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+6, SYSDATE+6);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+7, SYSDATE+7);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+8, SYSDATE+8);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+9, SYSDATE+9);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+10, SYSDATE+10);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+11, SYSDATE+11);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+12, SYSDATE+12);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+13, SYSDATE+13);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+14, SYSDATE+14);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+15, SYSDATE+15);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+16, SYSDATE+16);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+17, SYSDATE+17);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+18, SYSDATE+18);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+19, SYSDATE+19);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+20, SYSDATE+20);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+21, SYSDATE+21);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+22, SYSDATE+22);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+23, SYSDATE+23);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+24, SYSDATE+24);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+25, SYSDATE+25);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+26, SYSDATE+26);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+27, SYSDATE+27);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+28, SYSDATE+28);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+29, SYSDATE+29);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+30, SYSDATE+30);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+30, SYSDATE+30);



INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+31, SYSDATE+31);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+32, SYSDATE+32);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+33, SYSDATE+33);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+34, SYSDATE+34);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+35, SYSDATE+35);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+36, SYSDATE+36);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+37, SYSDATE+37);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+38, SYSDATE+38);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+39, SYSDATE+39);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+40, SYSDATE+40);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+41, SYSDATE+41);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+42, SYSDATE+42);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+43, SYSDATE+43);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+44, SYSDATE+44);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+45, SYSDATE+45);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+46, SYSDATE+46);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+47, SYSDATE+47);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+48, SYSDATE+48);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+49, SYSDATE+49);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+50, SYSDATE+50);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+51, SYSDATE+51);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+52, SYSDATE+52);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+53, SYSDATE+53);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+54, SYSDATE+54);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+55, SYSDATE+55);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+56, SYSDATE+56);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+57, SYSDATE+57);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+58, SYSDATE+58);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+59, SYSDATE+59);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+60, SYSDATE+60);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+61, SYSDATE+61);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+62, SYSDATE+62);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+63, SYSDATE+63);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+64, SYSDATE+64);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+65, SYSDATE+65);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+66, SYSDATE+66);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+67, SYSDATE+67);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+68, SYSDATE+68);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+69, SYSDATE+69);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+70, SYSDATE+70);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+71, SYSDATE+71);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+72, SYSDATE+72);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+73, SYSDATE+73);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+74, SYSDATE+74);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+75, SYSDATE+75);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+76, SYSDATE+76);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+77, SYSDATE+77);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+78, SYSDATE+78);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+79, SYSDATE+79);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+80, SYSDATE+80);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+81, SYSDATE+81);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+82, SYSDATE+82);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+83, SYSDATE+83);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+84, SYSDATE+84);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+85, SYSDATE+85);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+86, SYSDATE+86);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+87, SYSDATE+87);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+88, SYSDATE+88);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+89, SYSDATE+89);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 1, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 2, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 3, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 4, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 5, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 6, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 7, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 8, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 9, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 10, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 11, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 12, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 13, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 14, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 15, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 16, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 17, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 18, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 19, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 20, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 21, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 22, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 23, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 24, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 25, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 26, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 27, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 28, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 29, SYSDATE+90, SYSDATE+90);

INSERT INTO FLIGHT_TIME VALUES(SEQ_FLIGHT_TIME.NEXTVAL, 30, SYSDATE+90, SYSDATE+90);

commit;

INSERT INTO FUEL VALUES(1, '휘발유');
INSERT INTO FUEL VALUES(2, '경유');
INSERT INTO FUEL VALUES(3, '전기');

ALTER TABLE CAR MODIFY CAR_SIZE NUMBER;

DROP TABLE tb_size; 
CREATE TABLE tb_size (
    size_no NUMBER NOT NULL,
    car_size VARCHAR2(10) NOT NULL
);

ALTER TABLE tb_size ADD CONSTRAINT pk_size PRIMARY KEY ( size_no );
ALTER TABLE car
    ADD CONSTRAINT fk_size_to_car_1 FOREIGN KEY ( car_size )
        REFERENCES tb_size ( size_no );
        
INSERT INTO tb_size VALUES(1, '소형');
INSERT INTO tb_size VALUES(2, '중형');
INSERT INTO tb_size VALUES(3, '대형');

DROP SEQUENCE SEQ_CAR;
CREATE SEQUENCE SEQ_CAR NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_RENTAL_CAR;
CREATE SEQUENCE SEQ_RENTAL_CAR NOCACHE NOCYCLE;

        
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 1, '스파크', 1, '4');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 1, '아반떼', 1, '5');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 1, '소나타', 2, '5');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 1, '제네시스', 2, '5');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 2, '카니발', 3, '9');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 2, '스타렉스', 3, '12');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 3, '코나', 1, '5');
INSERT INTO CAR VALUES(SEQ_CAR.NEXTVAL, 1, '벤츠 컨버터블', 1, '4');

INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1001', 44000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1002', 44000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1003', 44000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1004', 44000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1005', 44000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 1, '허1006', 44000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2001', 60000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2002', 60000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2003', 60000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2004', 60000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2005', 60000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 2, '허2006', 60000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3001', 85000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3002', 85000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3003', 85000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3004', 85000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3005', 85000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 3, '허3006', 85000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4001', 150000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4002', 150000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4003', 150000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4004', 150000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4005', 150000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 4, '허4006', 150000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5001', 110000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5002', 110000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5003', 110000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5004', 110000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5005', 110000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 5, '허5006', 110000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6001', 125000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6002', 125000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6003', 125000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6004', 125000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6005', 125000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 6, '허6006', 125000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7001', 80000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7002', 80000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7003', 80000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7004', 80000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7005', 80000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 7, '허7006', 80000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8001', 320000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8002', 320000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8003', 320000, 'Y');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8004', 320000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8005', 320000, 'N');
INSERT INTO RENTAL_CAR VALUES(SEQ_RENTAL_CAR.NEXTVAL, 8, '허8006', 320000, 'N');

COMMIT;

<<<<<<< HEAD
DELETE FROM ROOM;
DELETE FROM ACCOM;

    
DROP SEQUENCE SEQ_ACCOM;
CREATE SEQUENCE SEQ_ACCOM NOCACHE NOCYCLE;

DROP SEQUENCE SEQ_ROOM;
CREATE SEQUENCE SEQ_ROOM NOCACHE NOCYCLE;

INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '마리나제주', '제주 제주시 특별자치도, 금능남로 127', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '엠버서터 호텔', '제주 서귀포시 중문관광로72번길 75', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '더퍼스트 제주', '제주 제주시 도령로 83', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '스카이브 호텔', '제주 제주시 노연로 12', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '브릿지 호텔', '제주 서귀포시 1100로 453-95 WE호텔', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '나이스웨더', '제주 서귀포시 중문관광로72번길 100', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '블루아일랜드', '제주 서귀포시 안덕면 산록남로 863', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '마운틴제주', '제주 서귀포시 막숙포로 118', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '라온제나', '제주 제주시 애월읍 애월해안로 394', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '수피아', '제주 서귀포시 동홍로 7', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 4, '그랜트 인 제주', '제주 서귀포시 동문로 42', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '제이로인', '제주 제주시 애월읍 애월해안로 554-6', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '제주로올래', '제주 서귀포시 태평로 363 헤이 서귀포', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '만다린러브', '제주 서귀포시 성산읍 고성오조로 94', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '그랜드 하얏트', '제주 제주시 그랜읍 하얏리 443', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, 'KH호텔', '제주 제주시 테헤란로 123-2', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '1DRAGON', '제주 제주시 드라곤로 1', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '칼 호텔', '제주 서귀포시 표선면 813', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '토스카나 호텔', '제주 서귀포시 안덕면 29', 'N', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '댕냥호텔', '제주 서귀포시 꼬리면 귀엽리 486', 'Y', 'H');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '제주 배드라이도', '제주 제주시 구좌읍 293', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '혼저옵서예', '제주 제주시 조천읍 003', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '예스존', '제주 제주시 추자면 3', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '바우잰', '제주 제주시 한림읍 12', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 2, '미르가온', '제주 제주시 미르로 2', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '투용', '제주 제주시 투용동 22', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '재주좋조', '제주 서귀포시 우리읍 잘하리', 'Y', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 1, '가온누리', '제주 서귀포시 성주청 32', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '백패커스', '제주 서귀포시 남원읍 200', 'N', 'G');
INSERT INTO ACCOM VALUES(SEQ_ACCOM.NEXTVAL, 3, '올래스테이', '제주 서귀포시 대정읍 30', 'N', 'G');

INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 4, '스탠다드싱글룸', 180000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '킹룸', 320000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '패밀리룸', 430000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '더블룸', 290000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '스탠다드더블룸', 68000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '디럭스 트윈룸', 89000, 3, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '디럭스 트리플룸', 110000, 3, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 1, '디럭스 패밀리룸', 150000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '수페리어 싱글룸', 580000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '수페리어 트윈룸', 730000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 3, '디럭스 킹룸', 810000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 3, '수페리어 패밀리룸', 1100000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '프리미어 패밀리룸', 1390000, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '디럭스 더블룸', 120000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 4, '디럭스 트윈룸', 150000, 3, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 2, '스위트 패밀리룸', 230000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '슈퍼 패밀리룸', 410000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '스탠다드 싱글룸', 28000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '스탠다드 트윈룸', 35000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 2, '디럭스 킹룸', 45000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 4, '스위트 패밀리룸', 53000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 4, '1인실', 23000, 1, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 7, 2, '1인실', 50000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 8, 3, '1인실', 38000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 9, 4, '2인실', 62000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 10, 1, '1인실', 19000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 11, 2, '1인실', 89000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 12, 3, '2인실', 47000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 13, 1, '1인실', 99000, 1, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 14, 2, '1인실', 41000, 1, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '트윈 룸 시티뷰', 500000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '트윈 룸 오션뷰', 550000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 1, '킹 룸 시티뷰', 600000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '킹 룸 오션뷰', 650000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 1, 2, '그랜드 스위트 트윈', 900000, 4, 'N', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '352강의장', 400000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '541강의장', 400000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '상담실', 200000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 2, 3, '취업반', 800000, 1, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '1용', 600000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '2용', 800000, 3, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 3, 2, '3용', 1000000, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 3, '스탠다드 온돌', 300000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 4, 1, '스탠다드 트윈', 300000, 2, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '디럭스 트윈', 400000, 2, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 5, 1, '디럭스 패밀리', 600000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 2, '리트리버는 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 3, '비숑도 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 1, '코숏은 더 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 6, 2, '러시안블루도 귀여워', 400000, 2, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 7, 3, '6인실', 100000, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 8, 1, '6인실', 200000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 9, 1, '6인실', 50000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 10, 1, '4인실', 30000, 4, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 11, 3, '4인실', 100000, 4, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 12, 2, '4인실', 222222, 4, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 13, 2, '6인실', 333333, 6, 'Y', 'Y');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 14, 2, '6인실', 30000, 6, 'Y', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 15, 1, '6인실', 30000, 6, 'N', 'N');
INSERT INTO ROOM VALUES(SEQ_ROOM.NEXTVAL, 16, 1, '4인실', 40000, 4, 'Y', 'N');


INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 5, 2, '오는 정 김밥', '제주서귀포시 동문동로 2','N',4000,'N','맛있는 녀석들에 소개된 자꾸 생각나는 제주도 김밥 맛집','064-762-8927','10:00','20:00','일요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '우진 해장국', '제주 제주시 서사로 11','N',10000,'N','수요미식회에 소개된, 제주 여행 시 뜨끈한 국물이 먹고 싶다면 꼭 들려야 하는 맛집','064-757-3393','6:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '올래 국수 본점', '제주 제주시 귀아랑길 24','N',9000,'N','수요미식회에 소개된, 고기 국수 하나로 승부하는 맛집','064-742-7355','8:30','15:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '연돈', '제주 서귀포시 일주서로 968-10','N',90000,'N','백종원의 골목식당 에서 극찬 받은 흑돼지 돈가스 맛집','064-738-7060','12:00','21:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '표선갈치어장', '제주 서귀포시 표선면 표선당포로 27','N',100000,'N','푸짐한 갈치 요리를 맛보고 싶다면 꼭 와야하는 갈치 요리 전문점','064-787-7118','10:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '흑돈가', '제주 제주시 한라대학로 11','N',18000,'N','숯불 향을 가득 입힌 흑돼지고기를 맛볼 수 있는 흑돼지 전문 맛집','064-747-0088','11:00','24:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '흑본오겹', '제주 제주시 서해안로 382','N',22000,'N','제주산 흑돼지고기를 맛볼 수 있는 오션 뷰 맛집','064-749-7756','13:00','22:30','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '섭지코지로', '제주 서귀포시 성산읍 시흥하동로57번길 35','N',35000,'Y','신선한 딱새우 회와 고등어 회로 유명한 맛집','064-782-3450','12:00','23:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '버드나무집', '제주 제주시 조천읍 신북로 540','N',12000,'N','도민 맛집으로 인정된 칼국수 맛집','064-782-9992','10:00','21:00','목요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '동백별장', '제주 제주시 원노형3길 44','N',20000,'Y','동백을 테마로 한 다양한 퓨전 요리를 맛볼 수 있는 분위기 좋은 주점','010-8885-7876','17:00','24:00','화요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '513텐동', '제주 제주시 테우해안로 46 2층','N',15000,'Y','텐동이 맛있고 바다가 보이는 전망 좋은 일식당','010-9492-4345','11:30','21:30','화요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '봉구식탁', '제주 제주시 애월읍 유수암평화길 14-3 1층','N',20000,'Y','바다가 보이는 분위기 좋은 스테이크 맛집','064-799-5909','11:30','20:00','월요일',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '떡하니', '제주 제주시 구좌읍 행원로9길 9-5','N',10000,'Y','푸짐한 문어 즉석 떡볶이 맛집','010-4200-1566','11:30','17:30','화요일-수요일',0,'N',NULL);
commit;
=======
ALTER TABLE CAR_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE CAR_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE ACCOM_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE ACCOM_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE FLIGHT_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N' ; 
ALTER TABLE FLIGHT_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));


ALTER TABLE ACCOM_RESERVATION DROP CONSTRAINT SYS_C008684;
ALTER TABLE ACCOM_RESERVATION ADD CHECK ( cancel_yn IN ( 'N', 'Y' ) );
>>>>>>> origin/parkS2_2
