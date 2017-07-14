CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `login` varchar(64) UNIQUE KEY NOT NULL,
  `password` varchar(64) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `users` (`id`, `login`, `password`, `balance`) VALUES
  (1, 'admin', 'nimda', 999.99),
  (2, 'manager', 'password', 555.55);

