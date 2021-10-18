
-- USE `reservations`;

USE `heroku_ece0f54a09b5458`;

-- -------------------------------------------------------

INSERT INTO `portal` (version, code, name) 
VALUES 
('0', UNHEX(replace(UUID(), '-', '')), "TicketDirecto"),
('0', UNHEX(replace(UUID(), '-', '')), "TicketsYa"),
('0', UNHEX(replace(UUID(), '-', '')), "AbcTickets");

-- -------------------------------------------------------