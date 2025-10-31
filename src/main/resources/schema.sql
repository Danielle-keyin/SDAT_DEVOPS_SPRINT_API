CREATE TABLE IF NOT EXISTS city (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  state VARCHAR(120),
  population INT
);

CREATE TABLE IF NOT EXISTS airport (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(160) NOT NULL,
  code CHAR(3) NOT NULL UNIQUE,
  city_id BIGINT NOT NULL,
  CONSTRAINT fk_airport_city FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS passenger (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  phone_number VARCHAR(40),
  city_id BIGINT NOT NULL,
  CONSTRAINT fk_passenger_city FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS aircraft (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(100) NOT NULL,
  airline_name VARCHAR(120),
  number_of_passengers INT
);

CREATE TABLE IF NOT EXISTS flight (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  aircraft_id BIGINT NOT NULL,
  origin_airport_id BIGINT NOT NULL,
  destination_airport_id BIGINT NOT NULL,
  departure_time DATETIME NOT NULL,
  CONSTRAINT fk_flight_aircraft FOREIGN KEY (aircraft_id) REFERENCES aircraft(id),
  CONSTRAINT fk_flight_origin FOREIGN KEY (origin_airport_id) REFERENCES airport(id),
  CONSTRAINT fk_flight_destination FOREIGN KEY (destination_airport_id) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS passenger_flight (
  passenger_id BIGINT NOT NULL,
  flight_id BIGINT NOT NULL,
  PRIMARY KEY (passenger_id, flight_id),
  CONSTRAINT fk_pf_passenger FOREIGN KEY (passenger_id) REFERENCES passenger(id),
  CONSTRAINT fk_pf_flight FOREIGN KEY (flight_id) REFERENCES flight(id)
);
