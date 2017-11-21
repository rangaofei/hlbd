DROP TABLE IF EXISTS `teacher_homework`;
CREATE TABLE teacher_homework
(
  id             INT AUTO_INCREMENT
    PRIMARY KEY,
  commited_count INT          NOT NULL,
  correct_rate   FLOAT        NOT NULL,
  created_time   DATETIME     NULL,
  difficult      FLOAT        NOT NULL,
  homework_id    VARCHAR(255) NOT NULL,
  name           VARCHAR(255) NOT NULL,
  question_count INT          NOT NULL,
  state          VARCHAR(255) NULL,
  subject_name   VARCHAR(255) NULL,
  teacher_id     VARCHAR(255) NULL,
  total_student  INT          NOT NULL,
  type           INT          NOT NULL,
  UNIQUE (homework_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;