CREATE TABLE usr
(
    id       BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email    VARCHAR(50)            NOT NULL,
    role     VARCHAR(10)            NOT NULL,
    password VARCHAR(100)           NOT NULL
) ENGINE = InnoDB;
CREATE UNIQUE INDEX uk_usr_name ON usr (email);

ALTER TABLE usr
    AUTO_INCREMENT = 5;
