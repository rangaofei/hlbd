DROP TABLE IF EXISTS `teacher_homework_question`;
CREATE TABLE teacher_homework_question
(
  id                  INT AUTO_INCREMENT
    PRIMARY KEY,
  booknode_id         INT          NOT NULL,
  correct_rate        FLOAT        NOT NULL,
  question_id         INT          NOT NULL,
  questiontype_id     INT          NOT NULL,
  stage_id            INT          NOT NULL,
  state               VARCHAR(255) NULL,
  subject_id          INT          NOT NULL,
  teacher_homework_id VARCHAR(255) NULL,
  textbook_id         INT          NOT NULL,
  version_id          INT          NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;