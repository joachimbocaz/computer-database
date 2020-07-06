  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admincdb-test'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db-test`.* TO 'admincdb-test'@'localhost' WITH GRANT OPTION;


  FLUSH PRIVILEGES;
