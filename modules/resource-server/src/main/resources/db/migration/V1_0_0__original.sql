CREATE TABLE zodiac
(
  id          BIGINT(20) PRIMARY KEY NOT NULL,
  end_day     TINYINT(4)             NOT NULL,
  end_month   VARCHAR(20)            NOT NULL,
  name        VARCHAR(20)            NOT NULL,
  start_day   TINYINT(4)             NOT NULL,
  start_month VARCHAR(20)            NOT NULL
) ENGINE=InnoDB;

CREATE TABLE country
(
  id   BIGINT(20) PRIMARY KEY NOT NULL,
  name VARCHAR(50)            NOT NULL
) ENGINE=InnoDB;

CREATE TABLE region
(
  id             BIGINT(20) PRIMARY KEY NOT NULL,
  name           VARCHAR(50)            NOT NULL,
  country_id     BIGINT(20)             NOT NULL,
  alternate_name VARCHAR(255),
  CONSTRAINT fk_region_country FOREIGN KEY (country_id) REFERENCES country (id)
) ENGINE=InnoDB;
CREATE INDEX region_country_idx ON region (country_id);

CREATE TABLE city
(
  id             BIGINT(20) PRIMARY KEY NOT NULL,
  name           VARCHAR(50)            NOT NULL,
  alternate_name VARCHAR(255),
  region_id      BIGINT(20)             NOT NULL,
  CONSTRAINT fk_city_region FOREIGN KEY (region_id) REFERENCES region (id)
) ENGINE=InnoDB;
CREATE INDEX city_region_idx ON city (region_id);

CREATE TABLE usr
(
  id          BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50)            NOT NULL,
  description VARCHAR(500)
) ENGINE=InnoDB;
CREATE UNIQUE INDEX uk_usr_name ON usr (name);

