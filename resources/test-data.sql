/* drop tables */
DROP TABLE IF EXISTS `qacinemas`.`movie`;

/*create new tables*/
CREATE TABLE `qacinemas`.`movie` (
  `MOVIE_ID` INT NOT NULL AUTO_INCREMENT,
  `MOVIE_NAME` VARCHAR(255) NULL,
  `YEAR` INT NULL,
  `GENRE` VARCHAR(45) NULL,
  `AGE_RATING` VARCHAR(45) NULL,
  `ACTORS` VARCHAR(500) NULL,
  `DIRECTOR` VARCHAR(45) NULL,
  `IMAGE_URL` VARCHAR(255) NULL,
  `DESC` VARCHAR(500) NULL,
  PRIMARY KEY (`MOVIE_ID`)
  );
  
/* insert data */
INSERT INTO `qacinemas`.`movie` (`MOVIE_ID`, `MOVIE_NAME`, `YEAR`, `GENRE`, `AGE_RATING`, `ACTORS`, `DIRECTOR`, `IMAGE_URL`, `DESC`)
VALUES 
	(1, "Titanic", 123, "Sad", 3, "Fish probably", "Jeff Bezos", "titanic.png", "Boat drowning"),
    (2, "Nemo", 432, "Scary", 5, "Fish", "Steve Erwin", "Nemo.png", "Fish looking for fish"),
    (3, "Toy Story", 2000, "Happy", 6, "Toys", "Guy Fieri", "ToyStory.png", "Toys running away from their owner and doing other stuff"),
    (4, "Transformers", 2002, "Epic", 7, "Robots", "Human", "Transformers.png", "Robots fighting other robots and humans are present")
    