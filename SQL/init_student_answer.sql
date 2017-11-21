DROP TABLE IF EXISTS `student_answer`;
CREATE TABLE student_answer
(
  id                     INT AUTO_INCREMENT
    PRIMARY KEY,
  answer_id              VARCHAR(255) NOT NULL,
  answer_name            VARCHAR(255) NULL,
  commited_student_count INT          NOT NULL,
  correct_rate           FLOAT        NOT NULL,
  cost_time              VARCHAR(255) NULL,
  created_time           DATETIME     NULL,
  difficult              FLOAT        NOT NULL,
  finish_time            VARCHAR(255) NULL,
  homework_id            VARCHAR(255) NOT NULL,
  question_count         INT          NOT NULL,
  seconds                INT          NOT NULL,
  state                  VARCHAR(255) NULL,
  student_count          INT          NOT NULL,
  student_id             VARCHAR(255) NOT NULL,
  student_name           VARCHAR(255) NOT NULL,
  subject_name           VARCHAR(255) NULL,
  team_id                VARCHAR(255) NULL,
  type                   INT          NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
