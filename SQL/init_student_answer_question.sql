DROP TABLE IF EXISTS `student_answer_question`;
CREATE TABLE student_answer_question
(
  id                           INT AUTO_INCREMENT
    PRIMARY KEY,
  answer                       VARCHAR(255) NULL,
  answer_comment               VARCHAR(255) NULL,
  answer_id                    VARCHAR(255) NULL,
  answer_img                   LONGTEXT     NULL,
  booknode_id                  INT          NOT NULL,
  correct                      LONGTEXT     NULL,
  correct_comment              VARCHAR(255) NULL,
  question_id                  INT          NOT NULL,
  questiontype_id              INT          NOT NULL,
  score                        INT          NOT NULL,
  stage_id                     INT          NOT NULL,
  state                        VARCHAR(255) NULL,
  student_id                   VARCHAR(255) NULL,
  student_name                 VARCHAR(255) NULL,
  subject_id                   INT          NOT NULL,
  teacher_homework_question_id INT          NOT NULL,
  textbook_id                  INT          NOT NULL,
  version_id                   INT          NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;