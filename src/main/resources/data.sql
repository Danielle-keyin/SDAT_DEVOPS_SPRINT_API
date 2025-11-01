SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE passenger_flight;
TRUNCATE TABLE flight;
TRUNCATE TABLE passenger;
TRUNCATE TABLE airport;
TRUNCATE TABLE city;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO city (id, name, state, population) VALUES
(1, 'St. John''s', 'NL', 110000),
(2, 'Halifax', 'NS', 440000),
(3, 'Toronto', 'ON', 2930000),
(4, 'Montreal', 'QC', 1760000),
(5, 'Vancouver', 'BC', 675000),
(6, 'Calgary', 'AB', 1300000),
(7, 'Edmonton', 'AB', 981000),
(8, 'Winnipeg', 'MB', 749000),
(9, 'Ottawa', 'ON', 1010000),
(10, 'Quebec City', 'QC', 550000);


INSERT INTO airport (id, name, code, city_id) VALUES
(1, 'St. John''s Intl', 'YYT', 1),
(2, 'Halifax Stanfield Intl', 'YHZ', 2),
(3, 'Toronto Pearson Intl', 'YYZ', 3),
(4, 'Montréal-Trudeau Intl', 'YUL', 4),
(5, 'Vancouver Intl', 'YVR', 5),
(6, 'Calgary Intl', 'YYC', 6),
(7, 'Edmonton Intl', 'YEG', 7),
(8, 'Winnipeg James Armstrong Richardson Intl', 'YWG', 8),
(9, 'Ottawa Macdonald–Cartier Intl', 'YOW', 9),
(10, 'Quebec City Jean Lesage Intl', 'YQB', 10);


INSERT INTO aircraft (id, type, airline_name, number_of_passengers) VALUES
  (1, 'B737-800', 'Air Canada', 160),
  (2, 'A320', 'WestJet', 150),
  (3, 'A220-300', 'Air Canada Rouge', 137),
  (4, 'B787-9', 'Air Canada', 298),
  (5, 'Q400', 'Porter Airlines', 78),
  (6, 'A321neo', 'WestJet', 180),
  (7, 'E175', 'Jazz Aviation', 76),
  (8, 'B737 MAX 8', 'Flair Airlines', 189),
  (9, 'CRJ900', 'Air Canada Express', 90),
  (10, 'B757-200', 'Charter Air', 220)
ON DUPLICATE KEY UPDATE type = VALUES(type);


INSERT INTO passenger (id, first_name, last_name, phone_number, city_id) VALUES
  (1, 'Ava', 'Taylor', '555-1111', 1),
  (2, 'Liam', 'Lee', '555-2222', 2),
  (3, 'Noah', 'Martin', '555-3333', 3),
  (4, 'Emma', 'Wilson', '555-4444', 4),
  (5, 'Olivia', 'Brown', '555-5555', 5),
  (6, 'Ethan', 'Clark', '555-6666', 6),
  (7, 'Sophia', 'White', '555-7777', 7),
  (8, 'Mason', 'Hall', '555-8888', 8),
  (9, 'Isabella', 'King', '555-9999', 9),
  (10, 'Lucas', 'Scott', '555-1010', 10)
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name);


INSERT INTO flight (id, aircraft_id, origin_airport_id, destination_airport_id, departure_time) VALUES
  (1, 1, 1, 2, '2025-11-01 08:15:00'),
  (2, 2, 2, 3, '2025-11-01 12:30:00'),
  (3, 3, 3, 4, '2025-11-02 09:00:00'),
  (4, 4, 4, 5, '2025-11-02 15:20:00'),
  (5, 5, 5, 6, '2025-11-03 07:45:00'),
  (6, 6, 6, 7, '2025-11-03 18:10:00'),
  (7, 7, 7, 8, '2025-11-04 11:00:00'),
  (8, 8, 8, 9, '2025-11-04 19:30:00'),
  (9, 9, 9, 10, '2025-11-05 10:45:00'),
  (10, 10, 10, 1, '2025-11-06 13:15:00')
ON DUPLICATE KEY UPDATE departure_time = VALUES(departure_time);


INSERT INTO passenger_flight (passenger_id, flight_id) VALUES
  (1, 1), (1, 2),
  (2, 2), (2, 3),
  (3, 3), (3, 4),
  (4, 4), (4, 5),
  (5, 5), (5, 6),
  (6, 6), (6, 7),
  (7, 7), (7, 8),
  (8, 8), (8, 9),
  (9, 9), (9, 10),
  (10, 1), (10, 10)
ON DUPLICATE KEY UPDATE passenger_id = passenger_id;

