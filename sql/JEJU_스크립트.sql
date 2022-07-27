DROP TABLE room;

CREATE TABLE room (
    room_no      NUMBER NOT NULL,
    accom_no     NUMBER NOT NULL,
    room_view    NUMBER NOT NULL,
    room_name    VARCHAR2(30) NOT NULL,
    room_price   NUMBER NOT NULL,
    capacity     NUMBER NOT NULL,
    animal_yn    CHAR(1) DEFAULT 'N' NOT NULL CHECK ( animal_yn IN ( 'M', 'F' ) ),
    pool_able_yn CHAR(1) NOT NULL CHECK ( pool_able_yn IN ( 'M', 'F' ) )
);

COMMENT ON COLUMN room.room_price IS
    '음수 불가능';

COMMENT ON COLUMN room.capacity IS
    '1이상';

DROP TABLE flight;

CREATE TABLE flight (
    flight_no      NUMBER NOT NULL,
    flight_name    CHAR(6) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time   TIMESTAMP NOT NULL,
    airline        VARCHAR2(20) NOT NULL,
    flight_price   NUMBER NOT NULL,
    seat           NUMBER NOT NULL,
    dep_airport    VARCHAR(15) NOT NULL,
    arr_airport    VARCHAR(15) NOT NULL
);

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
    travel_name    VARCHAR2(20) NOT NULL,
    travel_address VARCHAR2(100) NOT NULL,
    activity_yn    CHAR(1) CHECK ( activity_yn IN ( 'Y', 'N' ) ) NOT NULL,
    travel_price   NUMBER NOT NULL,
    animal_yn      CHAR(1) CHECK ( animal_yn IN ( 'Y', 'N' ) ) NOT NULL,
    travel_info    VARCHAR2(200) NOT NULL,
    travel_phone   VARCHAR2(13) NOT NULL,
    open           TIMESTAMP NULL,
    closed         TIMESTAMP NULL,
    closed_day     VARCHAR2(20) NULL,
    like_cnt       NUMBER DEFAULT 0 NULL,
    delete_yn      CHAR(1) DEFAULT 'N' CHECK ( delete_yn IN ( 'Y', 'N' ) ) NOT NULL,
    delete_date    TIMESTAMP NULL
);

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
    day_price NUMBER NOT NULL
);

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
    cancel_yn    CHAR(1) DEFAULT 'N' NOT NULL CHECK ( cancel_yn IN ( 'M', 'F' ) ),
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
    car_person NUMBER NOT NULL,
    openable   CHAR(1) NOT NULL CHECK ( openable IN ( 'Y', 'N' ) )
);

DROP TABLE accom;

CREATE TABLE accom (
    accom_no      NUMBER NOT NULL,
    accom_around  NUMBER NOT NULL,
    accom_name    VARCHAR2(40) NOT NULL,
    accom_address VARCHAR(100) NOT NULL,
    pool_yn       CHAR(1) DEFAULT 'N' NOT NULL CHECK ( pool_yn IN ( 'M', 'F' ) ),
    type          CHAR(1) NOT NULL CHECK ( type IN ( 'H', 'G' ) )
);

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
    location VARCHAR2(10) NOT NULL
);

DROP TABLE room_view_info;

CREATE TABLE room_view_info (
    room_view_no   NUMBER NOT NULL,
    room_view_info VARCHAR2(10) NULL
);

COMMENT ON COLUMN room_view_info.room_view_info IS
    '오션, 마운틴, 시티';

DROP TABLE category;

CREATE TABLE category (
    no       NUMBER NOT NULL,
    category VARCHAR(10) NOT NULL
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