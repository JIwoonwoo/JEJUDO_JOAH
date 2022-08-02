-- �����Ϸ� ������
ALTER TABLE CAR_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE CAR_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE ACCOM_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N'; 
ALTER TABLE ACCOM_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
ALTER TABLE FLIGHT_RESERVATION ADD PAID_YN CHAR(1) DEFAULT 'N' ; 
ALTER TABLE FLIGHT_RESERVATION ADD CHECK (PAID_YN IN('Y','N'));
COMMIT;

-- ���ָ��� ������
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 5, 2, '���� �� ���', '���ּ������� �������� 2','N',4000,'N','���ִ� �༮�鿡 �Ұ��� �ڲ� �������� ���ֵ� ��� ����','064-762-8927','10:00','20:00','�Ͽ���',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���� ���屹', '���� ���ֽ� ����� 11','N',10000,'N','����̽�ȸ�� �Ұ���, ���� ���� �� �߲��� ������ �԰� �ʹٸ� �� ����� �ϴ� ����','064-757-3393','6:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '�÷� ���� ����', '���� ���ֽ� �;ƶ��� 24','N',9000,'N','����̽�ȸ�� �Ұ���, ��� ���� �ϳ��� �º��ϴ� ����','064-742-7355','8:30','15:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '����', '���� �������� ���ּ��� 968-10','N',90000,'N','�������� ���Ĵ� ���� ���� ���� ����� ������ ����','064-738-7060','12:00','21:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, 'ǥ����ġ����', '���� �������� ǥ���� ǥ�������� 27','N',100000,'N','Ǫ���� ��ġ �丮�� ������ �ʹٸ� �� �;��ϴ� ��ġ �丮 ������','064-787-7118','10:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '�海��', '���� ���ֽ� �Ѷ���з� 11','N',18000,'N','���� ���� ���� ���� �������⸦ ���� �� �ִ� ����� ���� ����','064-747-0088','11:00','24:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '�溻����', '���� ���ֽ� ���ؾȷ� 382','N',22000,'N','���ֻ� �������⸦ ���� �� �ִ� ���� �� ����','064-749-7756','13:00','22:30','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '����������', '���� �������� ������ �����ϵ���57���� 35','N',35000,'Y','�ż��� ������ ȸ�� ���� ȸ�� ������ ����','064-782-3450','12:00','23:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���峪����', '���� ���ֽ� ��õ�� �źϷ� 540','N',12000,'N','���� �������� ������ Į���� ����','064-782-9992','10:00','21:00','�����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '���麰��', '���� ���ֽ� ������3�� 44','N',20000,'Y','������ �׸��� �� �پ��� ǻ�� �丮�� ���� �� �ִ� ������ ���� ����','010-8885-7876','17:00','24:00','ȭ����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '513�ٵ�', '���� ���ֽ� �׿��ؾȷ� 46 2��','N',15000,'Y','�ٵ��� ���ְ� �ٴٰ� ���̴� ���� ���� �ϽĴ�','010-9492-4345','11:30','21:30','ȭ����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '������Ź', '���� ���ֽ� �ֿ��� ��������ȭ�� 14-3 1��','N',20000,'Y','�ٴٰ� ���̴� ������ ���� ������ũ ����','064-799-5909','11:30','20:00','������',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���ϴ�', '���� ���ֽ� ������ �����9�� 9-5','N',10000,'Y','Ǫ���� ���� �Ｎ ������ ����','010-4200-1566','11:30','17:30','ȭ����-������',0,'N',NULL);
commit;

-- ������ ������
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 8, 5, 2, '���� �� ���', '���ּ������� �������� 2','N',4000,'N','���ִ� �༮�鿡 �Ұ��� �ڲ� �������� ���ֵ� ��� ����','064-762-8927','10:00','20:00','�Ͽ���',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���� ���屹', '���� ���ֽ� ����� 11','N',10000,'N','����̽�ȸ�� �Ұ���, ���� ���� �� �߲��� ������ �԰� �ʹٸ� �� ����� �ϴ� ����','064-757-3393','6:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '�÷� ���� ����', '���� ���ֽ� �;ƶ��� 24','N',9000,'N','����̽�ȸ�� �Ұ���, ��� ���� �ϳ��� �º��ϴ� ����','064-742-7355','8:30','15:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '����', '���� �������� ���ּ��� 968-10','N',90000,'N','�������� ���Ĵ� ���� ���� ���� ����� ������ ����','064-738-7060','12:00','21:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, 'ǥ����ġ����', '���� �������� ǥ���� ǥ�������� 27','N',100000,'N','Ǫ���� ��ġ �丮�� ������ �ʹٸ� �� �;��ϴ� ��ġ �丮 ������','064-787-7118','10:00','22:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '�海��', '���� ���ֽ� �Ѷ���з� 11','N',18000,'N','���� ���� ���� ���� �������⸦ ���� �� �ִ� ����� ���� ����','064-747-0088','11:00','24:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 6, 5, 2, '�溻����', '���� ���ֽ� ���ؾȷ� 382','N',22000,'N','���ֻ� �������⸦ ���� �� �ִ� ���� �� ����','064-749-7756','13:00','22:30','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '����������', '���� �������� ������ �����ϵ���57���� 35','N',35000,'Y','�ż��� ������ ȸ�� ���� ȸ�� ������ ����','064-782-3450','12:00','23:00','NULL',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���峪����', '���� ���ֽ� ��õ�� �źϷ� 540','N',12000,'N','���� �������� ������ Į���� ����','064-782-9992','10:00','21:00','�����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 5, 5, 2, '���麰��', '���� ���ֽ� ������3�� 44','N',20000,'Y','������ �׸��� �� �پ��� ǻ�� �丮�� ���� �� �ִ� ������ ���� ����','010-8885-7876','17:00','24:00','ȭ����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '513�ٵ�', '���� ���ֽ� �׿��ؾȷ� 46 2��','N',15000,'Y','�ٵ��� ���ְ� �ٴٰ� ���̴� ���� ���� �ϽĴ�','010-9492-4345','11:30','21:30','ȭ����',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '������Ź', '���� ���ֽ� �ֿ��� ��������ȭ�� 14-3 1��','N',20000,'Y','�ٴٰ� ���̴� ������ ���� ������ũ ����','064-799-5909','11:30','20:00','������',0,'N',NULL);
INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL, 7, 5, 2, '���ϴ�', '���� ���ֽ� ������ �����9�� 9-5','N',10000,'Y','Ǫ���� ���� �Ｎ ������ ����','010-4200-1566','11:30','17:30','ȭ����-������',0,'N',NULL);
commit;

-- ������̺� ���� ������ �߰�
-- �����ȣ
ALTER TABLE MEMBER ADD PAY_NO NUMBER;
COMMIT;

-- �����Ϸ� ��ȣ
ALTER TABLE MEMBER ADD FOREIGN KEY(PAY_NO) REFERENCES PAYMENT(PAY_NO);

-- ���� ���̺� �������� ����
ALTER TABLE ACCOM_RESERVATION DROP CONSTRAINT SYS_C008684;
ALTER TABLE ACCOM_RESERVATION ADD CHECK ( cancel_yn IN ( 'N', 'Y' ) );

-- ���� ���̺� �߰�
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

INSERT INTO insurance (insurance_no,insurance) VALUES (0, '�������');
INSERT INTO insurance (insurance_no,insurance) VALUES (1, '�Ϲ�����');
INSERT INTO insurance (insurance_no,insurance) VALUES (2, '��������');
INSERT INTO insurance (insurance_no,insurance) VALUES (3, '��������');

-- ����ð� Į�� ������ ����
ALTER TABLE FLIGHT_TIME MODIFY DEPARTURE_DATE DATE;
ALTER TABLE FLIGHT_TIME MODIFY ARRIVAL_DATE DATE;
COMMIT;

-- ���� �����̼� ������
INSERT INTO LOCATION VALUES (1, '���ֽ�');
INSERT INTO LOCATION VALUES (2, '��������');
INSERT INTO LOCATION VALUES (3, '������ü');

-- �� ���� �������� ����
ALTER TABLE ACCOM_RESERVATION DROP CONSTRAINT SYS_C008962;

-- �ſ�ī��, ������ü ���� ���̺� ����, �ѹ��� ����
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

INSERT INTO PAY_METHOD_NO VALUES (1, '�ſ�ī��');
INSERT INTO PAY_METHOD_NO VALUES (2, '������ü');

