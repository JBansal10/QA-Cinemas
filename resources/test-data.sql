/* drop tables (do higher order tables first)*/
DROP TABLE IF EXISTS `qacinemas`.`screentime`;
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
    `DESC` VARCHAR(1000) NULL,
    PRIMARY KEY (`MOVIE_ID`)
  );

CREATE TABLE `qacinemas`.`screentime` (
    `SCREENTIME_ID` INT NOT NULL AUTO_INCREMENT,
    `SCREENTIME_MOVIE` INT NOT NULL,
    `SCREENTIME_DAY` VARCHAR(9),
    `SCREENTIME_TIME` VARCHAR(5),
    `SCREEN_TYPE` VARCHAR(10),
    PRIMARY KEY (`SCREENTIME_ID`),
    FOREIGN KEY (`SCREENTIME_MOVIE`) REFERENCES `qacinemas`.`movie`(`MOVIE_ID`)
  );
  
/* insert data */
INSERT INTO `qacinemas`.`movie` (`MOVIE_ID`, `MOVIE_NAME`, `YEAR`, `GENRE`, `AGE_RATING`, `ACTORS`, `DIRECTOR`, `IMAGE_URL`, `DESC`)
VALUES 
	(1, "Titanic", 123, "Sad", 3, "Fish probably", "Jeff Bezos", "titanic.png", "Boat drowning"),
    (2, "Nemo", 432, "Scary", 5, "Fish", "Steve Erwin", "Nemo.png", "Fish looking for fish"),
    (3, "Toy Story", 2000, "Happy", 6, "Toys", "Guy Fieri", "ToyStory.png", "Toys running away from their owner and doing other stuff"),
    (4, "Transformers", 2002, "Epic", 7, "Robots", "Human", "Transformers.png", "Robots fighting other robots and humans are present");

INSERT INTO `qacinemas`.`screentime` (`SCREENTIME_ID`, `SCREENTIME_MOVIE`, `SCREENTIME_DAY`, `SCREENTIME_TIME`, `SCREEN_TYPE`)
VALUES
    (1, 1, "Monday", "19:30", "Standard"),
    (2, 1, "Monday", "21:00", "Standard"),
    (3, 1, "Tuesday", "20:15", "Deluxe"),
    (4, 1, "Wednesday", "18:00", "Standard"),
    (5, 1, "Wednesday", "20:20", "Deluxe"),
    (6, 1, "Thursday", "21:00", "Standard"),
    (7, 1, "Friday", "18:00", "Standard"),
    (8, 1, "Friday", "20:00", "Standard"),
    (9, 1, "Friday", "21:45", "Deluxe"),
    (10, 1, "Saturday", "19:30", "Deluxe"),
    (11, 1, "Sunday", "18:15", "Standard"),
    (12, 1, "Sunday", "20:30", "Standard");