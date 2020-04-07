-- 마이바티스수업
DROP SCHEMA IF EXISTS mybatis_study;

-- 마이바티스수업
CREATE SCHEMA mybatis_study;

-- 주소
CREATE TABLE mybatis_study.addresses (
	addr_id INT(11)     NOT NULL COMMENT '주소코드', -- 주소코드
	street  VARCHAR(50) NOT NULL COMMENT '도로', -- 도로
	city    VARCHAR(50) NOT NULL COMMENT '시', -- 시
	state   VARCHAR(50) NOT NULL COMMENT '구', -- 구
	zip     VARCHAR(10) NULL     COMMENT '우편번호', -- 우편번호
	country VARCHAR(50) NOT NULL COMMENT '읍' -- 읍
)
COMMENT '주소';

-- 주소
ALTER TABLE mybatis_study.addresses
	ADD CONSTRAINT
		PRIMARY KEY (
			addr_id -- 주소코드
		);

ALTER TABLE mybatis_study.addresses
	MODIFY COLUMN addr_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '주소코드';

ALTER TABLE mybatis_study.addresses
	AUTO_INCREMENT = 5;

-- 과목
CREATE TABLE mybatis_study.courses (
	course_id   INT(11)      NOT NULL COMMENT '과목코드', -- 과목코드
	name        VARCHAR(100) NOT NULL COMMENT '과목명', -- 과목명
	description VARCHAR(512) NULL     COMMENT '설명', -- 설명
	start_date  DATE         NULL     COMMENT '시작일', -- 시작일
	end_date    DATE         NULL     COMMENT '종료일', -- 종료일
	tutor_id    INT(11)      NOT NULL COMMENT '교수번호' -- 교수번호
)
COMMENT '과목';

-- 과목
ALTER TABLE mybatis_study.courses
	ADD CONSTRAINT
		PRIMARY KEY (
			course_id -- 과목코드
		);

ALTER TABLE mybatis_study.courses
	MODIFY COLUMN course_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '과목코드';

ALTER TABLE mybatis_study.courses
	AUTO_INCREMENT = 4;

-- 수강등록
CREATE TABLE mybatis_study.course_enrollment (
	course_id INT(11) NOT NULL COMMENT '과목코드', -- 과목코드
	stud_id   INT(11) NOT NULL COMMENT '학생코드' -- 학생코드
)
COMMENT '수강등록';

-- 수강등록
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT
		PRIMARY KEY (
			course_id, -- 과목코드
			stud_id    -- 학생코드
		);

-- 학생
CREATE TABLE mybatis_study.students (
	stud_id INT(11)     NOT NULL COMMENT '학생코드', -- 학생코드
	name    VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	email   VARCHAR(50) NOT NULL COMMENT '이메일', -- 이메일
	phone   VARCHAR(15) NULL     COMMENT '연락처', -- 연락처
	dob     DATE        NULL     COMMENT '생일', -- 생일
	bio     LONGTEXT    NULL     COMMENT '자기소개', -- 자기소개
	pic     BLOB        NULL     COMMENT '사진', -- 사진
	addr_id INT(11)     NULL     COMMENT '주소코드' -- 주소코드
)
COMMENT '학생';

-- 학생
ALTER TABLE mybatis_study.students
	ADD CONSTRAINT
		PRIMARY KEY (
			stud_id -- 학생코드
		);

ALTER TABLE mybatis_study.students
	MODIFY COLUMN stud_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '학생코드';

ALTER TABLE mybatis_study.students
	AUTO_INCREMENT = 4;

-- 교수
CREATE TABLE mybatis_study.tutors (
	tutor_id INT(11)     NOT NULL COMMENT '교수번호', -- 교수번호
	name     VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
	email    VARCHAR(50) NOT NULL COMMENT '이메일', -- 이메일
	phone    VARCHAR(15) NULL     COMMENT '연락처', -- 연락처
	dob      DATE        NULL     COMMENT '생일', -- 생일
	bio      LONGTEXT    NULL     COMMENT '자기소개', -- 자기소개
	pic      BLOB        NULL     COMMENT '사진', -- 사진
	addr_id  INT(11)     NULL     COMMENT '주소코드' -- 주소코드
)
COMMENT '교수';

-- 교수
ALTER TABLE mybatis_study.tutors
	ADD CONSTRAINT
		PRIMARY KEY (
			tutor_id -- 교수번호
		);

ALTER TABLE mybatis_study.tutors
	MODIFY COLUMN tutor_id INT(11) NOT NULL AUTO_INCREMENT COMMENT '교수번호';

ALTER TABLE mybatis_study.tutors
	AUTO_INCREMENT = 5;

-- user_pics
CREATE TABLE mybatis_study.user_pics (
	id   INT(11)     NOT NULL COMMENT 'id', -- id
	name VARCHAR(50) NULL     COMMENT 'name', -- name
	pic  BLOB        NULL     COMMENT 'pic', -- pic
	bio  LONGTEXT    NULL     COMMENT 'bio' -- bio
)
COMMENT 'user_pics';

-- user_pics
ALTER TABLE mybatis_study.user_pics
	ADD CONSTRAINT
		PRIMARY KEY (
			id -- id
		);

ALTER TABLE mybatis_study.user_pics
	MODIFY COLUMN id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id';

ALTER TABLE mybatis_study.user_pics
	AUTO_INCREMENT = 37;

-- 과목
ALTER TABLE mybatis_study.courses
	ADD CONSTRAINT FK_COURSE_TUTOR -- FK_COURSE_TUTOR
		FOREIGN KEY (
			tutor_id -- 교수번호
		)
		REFERENCES mybatis_study.tutors ( -- 교수
			tutor_id -- 교수번호
		),
	ADD INDEX FK_COURSE_TUTOR (
		tutor_id -- 교수번호
	);

-- 수강등록
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT FK_ENROLLMENT_STUD -- FK_ENROLLMENT_STUD
		FOREIGN KEY (
			stud_id -- 학생코드
		)
		REFERENCES mybatis_study.students ( -- 학생
			stud_id -- 학생코드
		),
	ADD INDEX FK_ENROLLMENT_STUD (
		stud_id -- 학생코드
	);

-- 학생
ALTER TABLE mybatis_study.students
	ADD CONSTRAINT FK_STUDENTS_ADDR -- FK_STUDENTS_ADDR
		FOREIGN KEY (
			addr_id -- 주소코드
		)
		REFERENCES mybatis_study.addresses ( -- 주소
			addr_id -- 주소코드
		),
	ADD INDEX FK_STUDENTS_ADDR (
		addr_id -- 주소코드
	);

-- 교수
ALTER TABLE mybatis_study.tutors
	ADD CONSTRAINT FK_TUTORS_ADDR -- FK_TUTORS_ADDR
		FOREIGN KEY (
			addr_id -- 주소코드
		)
		REFERENCES mybatis_study.addresses ( -- 주소
			addr_id -- 주소코드
		),
	ADD INDEX FK_TUTORS_ADDR (
		addr_id -- 주소코드
	);

-- 수강등록
ALTER TABLE mybatis_study.course_enrollment
	ADD CONSTRAINT FK_ENROLLMENT_COURSE -- FK_ENROLLMENT_COURSE
		FOREIGN KEY (
			course_id -- 과목코드
		)
		REFERENCES mybatis_study.courses ( -- 과목
			course_id -- 과목코드
		);
		
	
-- user_mybatis_study localhost, % 계정 추가
drop user if exists 'user_mybatis_study'@'localhost';
drop user if exists 'user_mybatis_study'@'%';

grant all privileges on mybatis_study.* to 'user_mybatis_study'@'localhost' identified by 'rootroot';
grant all privileges on mybatis_study.* to 'user_mybatis_study'@'%' identified by 'rootroot';



-- 열거형 컬럼 추가(gender)
ALTER TABLE students add gender tinyint(1) unsigned;


ALTER TABLE students add gender tinyint unsigned;
alter table students modify gender tinyint(1) unsigned;


CREATE TABLE user_pics (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  name varchar(50) not NULL COMMENT 'name',
  pic longblob COMMENT 'pic',
  bio longtext COLLATE utf8_unicode_ci COMMENT 'bio',
  PRIMARY KEY (id)
);

-- procedure 

