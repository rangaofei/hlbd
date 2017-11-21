DROP TABLE IF EXISTS `team`;
CREATE TABLE team
(
  id                INT AUTO_INCREMENT
    PRIMARY KEY,
  created_time      DATETIME     NULL,
  teacher_id        VARCHAR(255) NULL,
  team_id           VARCHAR(255) NOT NULL,
  team_introduction VARCHAR(255) NOT NULL,
  team_name         VARCHAR(255) NOT NULL,
  team_volume       INT          NOT NULL,
  UNIQUE (team_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;