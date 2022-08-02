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