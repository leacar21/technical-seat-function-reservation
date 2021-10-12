
USE `reservations`;

-- -------------------------------------------------------

INSERT INTO `portal` (version, code, name) 
VALUES 
('0', UNHEX(replace(UUID(), '-', '')), "TicketDirecto"),
('0', UNHEX(replace(UUID(), '-', '')), "TicketsYa"),
('0', UNHEX(replace(UUID(), '-', '')), "AbcTickets");

-- -------------------------------------------------------