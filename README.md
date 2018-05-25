# PHPMyAdmin User Info
Login : root<br>
Password : IMyl8Pt1KwexPp7d<br>
Host : %<br>
<br>
Database: `dem-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `num_adress` int(11) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `cplt_adress` varchar(100) NOT NULL,
  `zip_code` int(5) NOT NULL,
  `city` varchar(50) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `birth` date NOT NULL,
  `sex` set('Homme','Femme') NOT NULL,
  `profil` set('0','1') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login`, `password`, `num_adress`, `adress`, `cplt_adress`, `zip_code`, `city`, `lastname`, `firstname`, `email`, `birth`, `sex`, `profil`) VALUES
('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 0, '', '', 0, '', '', '', '', '0000-00-00', '', ''),
('Morphaal', 'b1b3773a05c0ed0176787a4f1574ff0075f7521e', 6, 'avenue du Chancelier Adenauer', 'Residence America 1', 59370, 'Mons en Baroeul', 'Noyen', 'Sebastien', 'sebastien.noyen@gmail.com', '1985-11-01', 'Homme', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);
COMMIT;
