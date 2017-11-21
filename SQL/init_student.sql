DROP TABLE IF EXISTS `student`;
CREATE TABLE student
(
  id           INT AUTO_INCREMENT
    PRIMARY KEY,
  created_time DATETIME     NULL,
  name         VARCHAR(255) NOT NULL,
  password     VARCHAR(255) NOT NULL,
  phone        VARCHAR(255) NOT NULL,
  role         VARCHAR(255) NOT NULL,
  student_id   VARCHAR(255) NOT NULL,
  CONSTRAINT UK_lh7am6sc9pv0nhyg7qkj7w5d3
  UNIQUE (student_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;