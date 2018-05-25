# PHPMyAdmin User Info
Login : root<br>
Password : IMyl8Pt1KwexPp7d<br>
Host : %<br>
<br>
<!--
- phpMyAdmin XML Dump
- version 4.7.0
- https://www.phpmyadmin.net
-
- Host: localhost:3306
- Generation Time: May 25, 2018 at 01:01 PM
- Server version: 5.6.34-log
- PHP Version: 7.1.7
--><pma_xml_export version="1.0"><!--
    - Structure schemas
    --><pma:structure_schemas><pma:database name="dem-db" collation="latin1_swedish_ci" charset="latin1"><pma:table name="users">
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
                  `profil` set('0','1') NOT NULL,
                  PRIMARY KEY (`login`)
                ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
            </pma:table></pma:database></pma:structure_schemas><!--
    - Database: 'dem-db'
    --><database name="dem-db"><!-- Table users --><table name="users"><column name="login">admin</column><column name="password">d033e22ae348aeb5660fc2140aec35850c4da997</column><column name="num_adress">0</column><column name="adress"/><column name="cplt_adress"/><column name="zip_code">0</column><column name="city"/><column name="lastname"/><column name="firstname"/><column name="email"/><column name="birth">0000-00-00</column><column name="sex"/><column name="profil"/></table><table name="users"><column name="login">Morphaal</column><column name="password">b1b3773a05c0ed0176787a4f1574ff0075f7521e</column><column name="num_adress">6</column><column name="adress">avenue du Chancelier Adenauer</column><column name="cplt_adress">Residence America 1</column><column name="zip_code">59370</column><column name="city">Mons en Baroeul</column><column name="lastname">Noyen</column><column name="firstname">Sebastien</column><column name="email">sebastien.noyen@gmail.com</column><column name="birth">1985-11-01</column><column name="sex">Homme</column><column name="profil">0</column></table></database></pma_xml_export>
