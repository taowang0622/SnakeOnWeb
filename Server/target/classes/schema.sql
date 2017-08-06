DROP DATABASE snake;

CREATE DATABASE snake;

USE snake;

CREATE TABLE players (
  id          BIGINT       NOT NULL        AUTO_INCREMENT PRIMARY KEY,
  player_name VARCHAR(100) NOT NULL UNIQUE, # player_name must be unique!!!
  status      TINYINT(1)   NOT NULL        DEFAULT 0, # 0=>offline, 1=>playing, 2=>available
  host_name   VARCHAR(20)  NOT NULL        DEFAULT "0.0.0.0",
  best_score  BIGINT       NOT NULL        DEFAULT 0,
  create_time TIMESTAMP    NOT NULL        DEFAULT current_timestamp
)
  ENGINE = innodb
  #   id increments from 1000
  #   AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT = 'The table for players';

INSERT INTO players (player_name, status, best_score) VALUES (
  'taowang', 0, 0
)