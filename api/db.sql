-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-08 16:37:42.986

-- tables
-- Table: Car
CREATE TABLE Car (
    id int NOT NULL AUTO_INCREMENT,
    model varchar(45) NOT NULL,
    rfid varchar(45) NOT NULL,
    image varchar(300) NOT NULL,
    no_plate varchar(45) NOT NULL,
    CarType_id int NOT NULL,
    CONSTRAINT Car_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- Table: CarType
CREATE TABLE CarType (
    id int NOT NULL AUTO_INCREMENT,
    type varchar(100) NULL,
    CONSTRAINT CarType_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- Table: Driver
CREATE TABLE Driver (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    license_no varchar(45) NOT NULL,
    address varchar(400) NOT NULL,
    balance double NULL,
    User_id int NOT NULL,
    CONSTRAINT Driver_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- Table: DriverCar
CREATE TABLE DriverCar (
    Car_id int NOT NULL,
    Driver_id int NOT NULL,
    CONSTRAINT DriverCar_pk PRIMARY KEY (Car_id,Driver_id)
) ENGINE InnoDB;

-- Table: Toll
CREATE TABLE Toll (
    id int NOT NULL AUTO_INCREMENT,
    amount double NOT NULL,
    CarType_id int NOT NULL,
    CONSTRAINT Toll_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- Table: TollLog
CREATE TABLE TollLog (
    id int NOT NULL AUTO_INCREMENT,
    Driver_id int NOT NULL,
    Car_id int NOT NULL,
    cost double NOT NULL,
    location varchar(100) NOT NULL,
    tolled_at timestamp NOT NULL DEFAULT current_timestamp,
    CONSTRAINT TollLog_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- Table: User
CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(100) NULL,
    password varchar(100) NULL,
    user_type varchar(45) NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
) ENGINE InnoDB;

-- foreign keys
-- Reference: Car_CarType (table: Car)
ALTER TABLE Car ADD CONSTRAINT Car_CarType FOREIGN KEY Car_CarType (CarType_id)
    REFERENCES CarType (id);

-- Reference: DriverCar_Car (table: DriverCar)
ALTER TABLE DriverCar ADD CONSTRAINT DriverCar_Car FOREIGN KEY DriverCar_Car (Car_id)
    REFERENCES Car (id);

-- Reference: DriverCar_Driver (table: DriverCar)
ALTER TABLE DriverCar ADD CONSTRAINT DriverCar_Driver FOREIGN KEY DriverCar_Driver (Driver_id)
    REFERENCES Driver (id);

-- Reference: Driver_User (table: Driver)
ALTER TABLE Driver ADD CONSTRAINT Driver_User FOREIGN KEY Driver_User (User_id)
    REFERENCES User (id);

-- Reference: TollLog_Car (table: TollLog)
ALTER TABLE TollLog ADD CONSTRAINT TollLog_Car FOREIGN KEY TollLog_Car (Car_id)
    REFERENCES Car (id);

-- Reference: TollLog_Driver (table: TollLog)
ALTER TABLE TollLog ADD CONSTRAINT TollLog_Driver FOREIGN KEY TollLog_Driver (Driver_id)
    REFERENCES Driver (id);

-- Reference: Toll_CarType (table: Toll)
ALTER TABLE Toll ADD CONSTRAINT Toll_CarType FOREIGN KEY Toll_CarType (CarType_id)
    REFERENCES CarType (id);

-- End of file.

