DROP TABLE IF EXISTS `teacher_subject`;
CREATE TABLE teacher_subject
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  stage       VARCHAR(255) NOT NULL,
  stage_id    INT          NOT NULL,
  subject     VARCHAR(255) NOT NULL,
  subject_id  INT          NOT NULL,
  teacher_id  VARCHAR(255) NULL,
  textbook    VARCHAR(255) NOT NULL,
  textbook_id INT          NOT NULL,
  version     VARCHAR(255) NOT NULL,
  version_id  INT          NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;