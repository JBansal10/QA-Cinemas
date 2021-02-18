/* drop tables (do higher order tables first)*/
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `qacinemas`.`payment`;
DROP TABLE IF EXISTS `qacinemas`.`booking`;
DROP TABLE IF EXISTS `qacinemas`.`discussionboard`;
DROP TABLE IF EXISTS `qacinemas`.`screentime`;
DROP TABLE IF EXISTS `qacinemas`.`movie`;
SET FOREIGN_KEY_CHECKS = 1;

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
    FOREIGN KEY (`SCREENTIME_MOVIE`) REFERENCES `qacinemas`.`movie`(`MOVIE_ID`) ON DELETE CASCADE
    );

CREATE TABLE `qacinemas`.`booking` (
   `FORM_ID`       int NOT NULL AUTO_INCREMENT,
   `CUSTOMER_NAME` varchar(100) DEFAULT NULL,
   `ADULTS`        int          DEFAULT NULL,
   `CHILDS`        int          DEFAULT NULL,
   `CONCESSION`    varchar(100) DEFAULT NULL,
   `SCREEN_DATE`   varchar(50)  DEFAULT NULL,
   `SCREEN_ID`     int          DEFAULT NULL,
   PRIMARY KEY (`FORM_ID`),
   FOREIGN KEY (`SCREEN_ID`) REFERENCES `qacinemas`.`screentime` (`SCREENTIME_ID`) ON DELETE CASCADE
);

CREATE TABLE `qacinemas`.`discussionboard`
(
    `POST_ID`       int NOT NULL AUTO_INCREMENT,
    `CONTENT`       varchar(1000) DEFAULT NULL,
    `POST_DATETIME` varchar(100)  DEFAULT NULL,
    `MOVIE_ID`      int           DEFAULT NULL,
    `MOVIE_RATING`  int           DEFAULT NULL,
    `POST_CHECKER`  tinyint(1) DEFAULT '0',
    PRIMARY KEY (`POST_ID`),
    FOREIGN KEY (`MOVIE_ID`) REFERENCES `qacinemas`.`movie`(`MOVIE_ID`) ON DELETE CASCADE
    );

CREATE TABLE `qacinemas`.`payment` (
    `PAYMENT_ID` INT NOT NULL AUTO_INCREMENT,
    `CARD_HOLDER_NAME` VARCHAR(255),
    `CARD_NO` INT NOT NULL,
    `EXPIRY_DATE` VARCHAR(255),
    `SECURITY_CODE` INT NOT NULL,
    `BOOKING_ID` INT NOT NULL,
    PRIMARY KEY (`PAYMENT_ID`),
    FOREIGN KEY (`BOOKING_ID`) REFERENCES `qacinemas`.`booking`(`FORM_ID`) ON DELETE CASCADE
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

INSERT INTO `qacinemas`.`booking` (`FORM_ID`, `CUSTOMER_NAME`, `ADULTS`, `CHILDS`, `CONCESSION`, `SCREEN_DATE`, `SCREEN_ID`)
VALUES
    (1, "Piers", 1, 1, "Popcorn, HotDog", "30/2/2021", 1),
    (2, "Ayub", 1, 1, "None", "30/2/2021", 1),
    (3, "Jake", 1, 1, "Large Coke, medium popcorn", "30/2/2021", 1),
    (4, "Jas", 1, 1, "Tango Ice Blast", "30/2/2021", 1),
    (5, "Simon", 1, 1, "M&Ms", "30/2/2021", 1),
    (6, "Iqra", 1, 1, "Minstrels", "30/2/2021", 1);

INSERT INTO `qacinemas`.`discussionboard` (`POST_ID`, `CONTENT`, `POST_DATETIME`, `MOVIE_ID`, `MOVIE_RATING`)
VALUES
(1, "Oh dear oh dear oh dear... Talks of Leonardo getting an oscar for this are poor.
Why don't we not just just leave this were we found it... At the bottom of the ocean!",
 "17/02/2021 12:34", 1, 9),
(2, "Welp, this was interesting, certainly expected us to feel for the toys but wow... they're awful!",
 "17/02/2021 12:48", 3, 9);