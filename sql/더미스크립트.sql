INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL, 'JEJU', 'KH3', '관리자', '관리자', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
COMMIT;

ALTER TABLE MEMBER DROP UNIQUE(ID);
ALTER TABLE MEMBER ADD UNIQUE(ID, QUIT_YN);

ALTER TABLE QNA MODIFY QUESTION_TITLE VARCHAR(50);

INSERT INTO QNA VALUES(SEQ_QNA.NEXTVAL, 1, '문의작성 테스트', sysdate, default, '문의내역 ㅎㅎ 작성중 ㅎㅎ', null, sysdate, default, null, null);
COMMIT;