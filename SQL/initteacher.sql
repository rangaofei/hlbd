DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  teacher_id    VARCHAR(255) NOT NULL,

  name          VARCHAR(255) NOT NULL,
  password      VARCHAR(255) NOT NULL,
  phone         VARCHAR(255) NOT NULL,
  role          VARCHAR(255) NOT NULL,
  crteated_time DATETIME     NULL

)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;