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

-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);
COMMIT;
