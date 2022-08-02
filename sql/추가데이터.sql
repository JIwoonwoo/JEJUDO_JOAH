-- 결제완료 데이터
ALTER TABLE CAR_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE CAR_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE ACCOM_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE ACCOM_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE FLIGHT_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N' ; 
ALTER TABLE FLIGHT_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
COMMIT;

-- 제주맛집 데이터
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

-- 여행지 데이터
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


-- 숙소 테이블 제약조건 수정
ALTER TABLE ACCOM_RESERVATION DROP CONSTRAINT SYS_C008684;
ALTER TABLE ACCOM_RESERVATION ADD CHECK ( cancel_yn IN ( 'N', 'Y' ) );

-- 보험 테이블 추가
ALTER TABLE car_reservation MODIFY insurance NUMBER;
DROP TABLE insurance;

CREATE TABLE insurance (
    insurance_no NUMBER NOT NULL,
    insurance    VARCHAR(20) NOT NULL
);

ALTER TABLE insurance ADD CONSTRAINT pk_insurance PRIMARY KEY ( insurance_no );
ALTER TABLE car_reservation
    ADD CONSTRAINT fk_insurance_to_car_reservation FOREIGN KEY (insurance)
            REFERENCES insurance ( insurance_no );

INSERT INTO insurance (insurance_no,insurance) VALUES (0, '보험안함');
INSERT INTO insurance (insurance_no,insurance) VALUES (1, '일반자차');
INSERT INTO insurance (insurance_no,insurance) VALUES (2, '완전자차');
INSERT INTO insurance (insurance_no,insurance) VALUES (3, '슈퍼자차');

-- 비행시간 칼럼 데이터 변경
ALTER TABLE FLIGHT_TIME MODIFY DEPARTURE_DATE DATE;
ALTER TABLE FLIGHT_TIME MODIFY ARRIVAL_DATE DATE;
COMMIT;

-- 지역 로케이션 데이터
INSERT INTO LOCATION VALUES (1, '제주시');
INSERT INTO LOCATION VALUES (2, '서귀포시');
INSERT INTO LOCATION VALUES (3, '제주전체');

-- 방 예약 제약조건 삭제
ALTER TABLE ACCOM_RESERVATION DROP CONSTRAINT SYS_C008962;

-- 신용카드, 계좌이체 결제 테이블 생성, 넘버로 변경
ALTER TABLE PAYMENT MODIFY PAY_METHOD NUMBER;

DROP TABLE PAY_METHOD_NO;
CREATE TABLE PAY_METHOD_NO (
    METHOD_NO NUMBER PRIMARY KEY,
    METHOD    VARCHAR(12) NOT NULL
);
COMMIT;
ALTER TABLE PAYMENT
    ADD CONSTRAINT FK_PAY_METHOD_NO_TO_PAYMENT FOREIGN KEY (PAY_METHOD)
            REFERENCES PAY_METHOD_NO ( METHOD_NO );

INSERT INTO PAY_METHOD_NO VALUES (1, '신용카드');
INSERT INTO PAY_METHOD_NO VALUES (2, '계좌이체');

