 create table Passenger(
 	name varchar(50) not null,
 	contact number not null,
 	email varchar(50) not null,
 	password varchar(50) not null,
 	age number not null,
 	gender char not null,
 	constraint pk_1 primary key (contact)
 	);

 create table Driver(
 	name varchar(50) not null,
 	phone number not null,
 	password varchar(50) not null,
 	licenseno varchar(15) not null,
 	age number not null,
 	gender char not null,
 	cartype varchar(25) not null,
 	carnumber varchar(10) not null,
 	carname varchar(20) not null,
 	seatcap number not null,
 	nooftrips number,
 	email varchar(25) null,
 	constraint pk_2 primary key (phone)
 	);

 create table Trip_Details(
 	tripid number not null,
 	dcontact number not null,
 	pcontact number not null,
 	pickup varchar(50) not null,
 	destination varchar(50) not null,
 	Amount number not null,
 	driver_rating number,
 	passenger_rating number,
 	date_of_travel date not null,
 	constraint pk_3 primary key (tripid,dcontact,pcontact),
 	constraint pk_4 foreign key (dcontact) references Driver(phone),
 	constraint pk_5 foreign key (pcontact) references Passenger(contact)
 	);

 create table CabBooking(
 	pickup varchar(50),
 	destination varchar(50),
 	dcontact number,
 	pcontact number,
 	dlocation varchar(50),
 	);


Trigger:
 create or replace trigger counttrips
 	before insert 
 	on Trip_Details
 	for each row
 	begin 
 		update Driver set nooftrips=nooftrips+1 where :new.dcontact = phone;
 	end;
 	/

 	Queries :
 		1)	To display the names of drivers and the passengers and the total no of times that the passenger had a journey with the same driver:
 			select Driver.name as Driver_Name ,Passenger.name as Passenger_Name,count(Driver.name) as Total_Drives
 			from Driver,Passenger,Trip_Details 
 			where Driver.phone = Trip_Details.dcontact and Passenger.contact = Trip_Details.pcontact
 			group by Driver.Name,Passenger.name;

 Output: 
 					DRIVER_NAME                                        PASSENGER_NAME                     						TOTAL_DRIVES
	-------------------------------------------------- -------------------------------------------------- 						------------
					Ash                                                Prakash                                                       3
					Vicky                                              Ash                                                           2
					Rohith                                             Ash                                                           1
					Rakesh                                             Prakash                                                       1
					Rakesh                                             Ash                                                           1
					Rohith                                             Ruthsan                                                       1


		2) To find the hotspot and least used destination in the city:
 			create view Hotspot as select max(pickup) as Hotspot_in_the_City,count(pickup) as Total_Bookings
 			from Trip_Details
 			group by pickup ;

 Output: 
 		HOTSPOT_IN_THE_CITY                                TOTAL_BOOKINGS
-------------------------------------------------- 			--------------
		Ukkadam                                                         2
		Singanallur                                                     1
		Gandhipuram                                                     1
		Hopes College                                                   1
		CIT College                                                     1
		Periyanaiyekapalayam                                            1
		RS Puram                                                        1
		Cheran Ma Nagar                                                 1

		3)Details of the trip in a single day of a Driver:
 			select * from Trip_Details where date_of_travel='dd-mmm-yyyy';