INSERT INTO city (id, name, state, population) VALUES
  (1,'St. John''s','NL',110000),
  (2,'Halifax','NS',430000)
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO airport (id, name, code, city_id) VALUES
  (1,'St. John''s Intl','YYT',1),
  (2,'Halifax Stanfield','YHZ',2)
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO aircraft (id, type, airline_name, number_of_passengers) VALUES
  (1,'B737-800','Air Canada',160),
  (2,'A320','WestJet',150)
ON DUPLICATE KEY UPDATE type=VALUES(type);

INSERT INTO passenger (id, first_name, last_name, phone_number, city_id) VALUES
  (1,'Ava','Taylor','555-1111',1),
  (2,'Liam','Lee','555-2222',2)
ON DUPLICATE KEY UPDATE first_name=VALUES(first_name);

INSERT INTO flight (id, aircraft_id, origin_airport_id, destination_airport_id, departure_time) VALUES
  (1,1,1,2,'2025-11-01 08:15:00'),
  (2,2,2,1,'2025-11-02 17:45:00')
ON DUPLICATE KEY UPDATE departure_time=VALUES(departure_time);

INSERT INTO passenger_flight (passenger_id, flight_id) VALUES
  (1,1),(1,2),(2,2)
ON DUPLICATE KEY UPDATE passenger_id=passenger_id;
