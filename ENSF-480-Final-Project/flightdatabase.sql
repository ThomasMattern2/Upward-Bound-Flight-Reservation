/* ENSF 480 F23 Project Database
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS SKYWARD_BOUND;
CREATE DATABASE SKYWARD_BOUND; 
USE SKYWARD_BOUND;


DROP TABLE IF EXISTS FLIGHTS;
CREATE TABLE FLIGHTS (
    FlightID			int not null AUTO_INCREMENT,
	FlightNum		varchar(6),
    FlightDate      varchar(25),    
	Origin			varchar(3),
	Destination		varchar(3),
	Aircraft	    varchar(25),
	DepartureTime	varchar(25),
	ArrivalTime		varchar(25),
	FlightTime		varchar(25),
	primary key (FlightID)
);

INSERT INTO FLIGHTS (FlightNum, FlightDate, Origin, Destination, Aircraft, DepartureTime, ArrivalTime, FlightTime)
VALUES
('SB-617', '2023-12-01','YYC', 'YYZ', 'Boeing 747', '8:32PM', '12:22AM', '3h 50m'),
('SB-233', '2023-12-02','LAX', 'JFK', 'Airbus A380', '6:09AM', '2:35PM', '5h 26m'),
('SB-019', '2023-12-03','YVR', 'LAS', 'Airbus A320', '9:05AM', '11:53AM', '2h 48m'),
('SB-793', '2023-12-04','NYC', 'YYZ', 'Boeing 737', '8:30AM', '10:14AM', '1h 44m'),
('SB-549', '2023-12-05','LAS', 'YYC', 'Airbus A380', '6:45PM', '10:42PM', '2h 57m'),
('SB-352', '2023-12-06','LHR', 'CDG', 'Boeing 737', '8:00PM', '10:20PM', '1h 20m'),
('SB-092', '2023-12-07','AMS', 'FRA', 'Airbus A320', '7:25AM', '8:30AM', '1h 05m'),
('SB-923', '2023-12-08','SEA', 'YYC', 'Boeing 747', '1:50PM', '04:42PM', '1h 52m'),
('SB-122', '2023-12-09','LAS', 'PMI', 'Boeing 737', '10:49AM', '12:50PM', '21h 50m'),
('SB-732', '2023-12-10','ORD', 'YVR', 'Airbus A320', '8:50AM', '11:35AM', '4h 45m'),
('SB-863', '2023-12-11','YYZ', 'CDG', 'Airbus A380', '6:55PM', '8:10AM', '7h 15m'),
('SB-052', '2023-12-12','LAX', 'PMI', 'Boeing 737', '6:25PM', '5:25PM', '14h 08m'),
('SB-581', '2023-12-13','LHR', 'SEA', 'Airbus A320', '12:25PM', '2:20PM', '9h 55m'),
('SB-189', '2023-12-14','FRA', 'JFK', 'Boeing 737', '11:15AM', '2:05PM', '8h 50m'),
('SB-787', '2023-12-15','AMS', 'CDG', 'Boeing 747', '6:50AM', '8:10AM', '1h 20m'),
('SB-634', '2023-12-16','CDG', 'YYC', 'Airbus A320', '2:25PM', '3:49PM', '9h 24m'),
('SB-064', '2023-12-17','ORD', 'LHR', 'Boeing 737', '9:05AM', '10:40PM', '7h 35m'),
('SB-216', '2023-12-18','YYZ', 'YYC', 'Airbus A380', '7:00AM', '9:30PM', '4h 30m');



DROP TABLE IF EXISTS AIRCRAFT;
CREATE TABLE AIRCRAFT(
	AircraftID			int,
	Name			varchar(50),
	Capacity	    int,
	primary key (AircraftID)
);

INSERT INTO AIRCRAFT (AircraftID, Name, Capacity)
VALUES
(1, 'Airbus A380', 100),
(2, 'Airbus A320', 80),
(3, 'Boeing 737', 90),
(4, 'Boeing 747', 120);


DROP TABLE IF EXISTS LOCATIONS;
CREATE TABLE LOCATIONS(
    LocationID			int not null AUTO_INCREMENT,
    AirportName         varchar(3),
    City                varchar(25),
    Country             varchar(50),
    primary key(LocationID) 
);

INSERT INTO LOCATIONS(AirportName, City, Country)
VALUES
('YYC', 'Calgary', 'Canada'),
('YYZ', 'Toronto', 'Canada'),
('YVR', 'Vancouver', 'Canada'),
('LAX', 'Los Angeles', 'USA'),
('LAS', 'Las Vegas', 'USA'),
('JFK', 'New York City', 'USA'),
('LHR', 'London', 'England'),
('CDG', 'Paris', 'France'),
('AMS', 'Amsterdam', 'Netherlands'),
('FRA', 'Frankfurt', 'Germany'),
('PMI', 'Mallorca', 'Spain'),
('SEA', 'Seattle', 'USA'),
('ORD', 'Chicago', 'USA');




DROP TABLE IF EXISTS REGISTEREDUSERS;
CREATE TABLE REGISTEREDUSERS(
    UserID			int not null AUTO_INCREMENT,
    FName           varchar(25),
    LName           varchar(25),
    Email           varchar(50),
    Password        varchar(25), 
    HouseNum        int,
    Street          varchar(25),
    City            varchar(25),
    Country         varchar(50),
    PostalCode      varchar(7),
    CreditCardNumber    varchar(50),
    CVV                 int,
    primary key(UserID) 
);
/*
could have email as pk
*/

INSERT INTO REGISTEREDUSERS(FName, LName, Email, Password, HouseNum, Street, City, Country, PostalCode, CreditCardNumber, CVV)
VALUES
('Roy', 'Kent','roykent@gmail.com','soccer123', 819, 'Paved Court', 'London', 'England', 'KLM-352','1098765432123132', 201),
('Ted', 'Lasso','tedl12@gmail.com','football', 434, 'Wembley Road', 'Kansas', 'USA', 'IHV-164', '0246810121434658', 176),
('John', 'Smith', 'johnsmith@gmail.com', 'pass', 1, 'Maple Street', 'Calgary', 'Canada', 'TMS-257', '1234567891027493', 123),
('Sam', 'Obisanya', 'samo1234@gmail.com', 'nigeria', 32, 'Adams Road', 'Wolverhampton', 'England', 'TGC-347', '1029384756102938', 134),
('Rebecca', 'Welton', 'rwelts@hotmail.com', 'cookies', 562, 'Tottenham Way', 'York', 'England', 'LFY-224', '1452849274920164', 853),
('Jamie', 'Tartt', 'jamiet123@hotmail.com', 'keeley', 52, 'Hunter Avenue', 'Edmonton', 'Canada', 'PDA-293', '8375937840293918', 273),
('Coach', 'Beard', 'coachbeard1@hotmail.com', 'beard', 223, 'Garisson Avenue', 'New York City', 'USA', 'KDA-677', '7539274537194726', 829);


DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS(
    TicketID			int not null AUTO_INCREMENT,
    seatNum                   int,
    price                     int,
    FlightNumber         varchar(6),
    insurance                 boolean,
    Email				varchar(25),
    DepartureTime          varchar(25),
    classSeat           varchar(25),
    primary key(TicketID) 
);


DROP TABLE IF EXISTS CREWMEMBER;
CREATE TABLE CREWMEMBER(
    CrewID			int not null AUTO_INCREMENT,
    FName           varchar(25),
    LName           varchar(25),
    Email           varchar(25),
    Password        varchar(25), 
    Job             varchar(25),
    HouseNum        int,
    Street          varchar(25),
    City            varchar(25),
    Country         varchar(50),
    PostalCode      varchar(7),
    primary key(CrewID) 
);


INSERT INTO CREWMEMBER(FName, LName, Email,Password, Job, HouseNum, Street, City, Country, PostalCode)
VALUES
('Chandler', 'Bing','cbing@gmail.com','house', 'pilot', 22, 'Jump Street', 'New York City', 'USA', 'VPB-176'),
('Rachel', 'Green', 'rachelgreen12@gmail.com','newpass','crew', 45, 'Madison Avenue', 'Pittsburgh', 'USA', 'WXS-192'),
('Monica', 'Geller', 'mgeller@hotmail.com','password','crew', 92, 'Circle Drive', 'Saskatoon', 'Canada', 'SSK-189'),
('Ross', 'Geller', 'rossg@yahoo.ca','wewereonbreak','pilot', 823, 'Orange Place', 'London', 'England', 'PLD-262'),
('Phoebe', 'Buffay', 'phoebe1234@yahoo.ca','123','manager', 111, '32nd Avenue', 'Montreal', 'Canada', 'SPM-232'),
('Joey', 'Tribbiani', 'joeytribbs@yahoo.ca','apple','crew', 220, 'Woodcrest Circle', 'Vancouver', 'Canada', 'VPB-023'),
('Janice', 'Hosenstein', 'janiceh22@gmail.com','orange','pilot', 278, 'Raddison Place', 'Nashville', 'USA', 'NPT-091'),
('Richard', 'Burke', 'oldrichard@yahoo.com','ball','pilot', 81, 'Jamieson Place', 'Paris', 'France', 'KDA-812'),
('Gunther', 'Perk', 'creepygunther@hotmail.com','dog','crew', 1217, '2nd Street', 'Rotterdam', 'Netherlands', 'PLY-273');


DROP TABLE IF EXISTS CREWASSIGNMENT;
CREATE TABLE CREWASSIGNMENT(
    EntryID			int not null AUTO_INCREMENT,
    Email				varchar(25),
    FlightNum 			varchar(6),
    primary key(EntryID) 
);


INSERT INTO CREWASSIGNMENT(Email, FlightNum) 
VALUES
('cbing@gmail.com', 'SB-617'), 
('rachelgreen12@gmail.com', 'SB-617'),
('phoebe1234@yahoo.ca','SB-233'),
('creepygunther@hotmail.com', 'SB-233'),
('phoebe1234@yahoo.ca','SB-019'),
('cbing@gmail.com','SB-019'),
('oldrichard@yahoo.com', 'SB-793'), 
('joeytribbs@yahoo.ca', 'SB-793'),
('janiceh22@gmail.com', 'SB-549'), 
('mgeller@hotmail.com', 'SB-549'), 
('rachelgreen12@gmail.com','SB-352'),
('creepygunther@hotmail.com','SB-352'),
('oldrichard@yahoo.com','SB-092'),
('cbing@gmail.com','SB-092'),
('rossg@yahoo.ca','SB-923'), 
('joeytribbs@yahoo.ca','SB-923'),
('oldrichard@yahoo.com','SB-122'),
('mgeller@hotmail.com','SB-122'), 
('janiceh22@gmail.com','SB-732'),
('phoebe1234@yahoo.ca','SB-732'),
('rossg@yahoo.ca','SB-863'),
('rachelgreen12@gmail.com','SB-863'),
('oldrichard@yahoo.com','SB-052'),
('joeytribbs@yahoo.ca','SB-052'), 
('mgeller@hotmail.com','SB-581'),
('phoebe1234@yahoo.ca','SB-581'),
('cbing@gmail.com','SB-189'),
('joeytribbs@yahoo.ca','SB-189'),
('oldrichard@yahoo.com','SB-787'),
('janiceh22@gmail.com','SB-787'),
('rossg@yahoo.ca','SB-634'),
('phoebe1234@yahoo.ca','SB-634'),
('mgeller@hotmail.com','SB-064'),
('rachelgreen12@gmail.com','SB-064'),
('cbing@gmail.com','SB-216'),
('rossg@yahoo.ca','SB-216');




-- DROP TABLE IF EXISTS PAYMENT;
-- CREATE TABLE PAYMENT(
--     PaymentID			int not null AUTO_INCREMENT,
--     CreditCardNumber    varchar(50),
--     CVV                 int,
--     Email               varchar(25),
--     primary key(PaymentID) 
-- ); 

-- INSERT INTO PAYMENT(CreditCardNumber,CVV, Email)
-- VALUES
-- ('10987654321', 201,'roykent@gmail.com'),
-- ('02468101214', 176,'tedl12@gmail.com'),
-- ('12345678910', 123,'johnsmith@gmail.com');


/*

*/
