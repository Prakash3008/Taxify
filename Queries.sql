Table Contents :

SQL> desc Driver;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 NAME                                      NOT NULL VARCHAR2(50)
 PHONE                                     NOT NULL NUMBER
 PASSWORD                                  NOT NULL VARCHAR2(50)
 LICENSENO                                 NOT NULL VARCHAR2(15)
 AGE                                       NOT NULL NUMBER
 GENDER                                    NOT NULL CHAR(1)
 CARTYPE                                   NOT NULL VARCHAR2(25)
 CARNUMBER                                 NOT NULL VARCHAR2(10)
 CARNAME                                   NOT NULL VARCHAR2(20)
 SEATCAP                                   NOT NULL NUMBER
 NOOFTRIPS                                          NUMBER
 EMAIL                                              VARCHAR2(25)

 SQL> desc Passenger;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 NAME                                      NOT NULL VARCHAR2(50)
 CONTACT                                   NOT NULL NUMBER
 EMAIL                                     NOT NULL VARCHAR2(50)
 PASSWORD                                  NOT NULL VARCHAR2(50)
 AGE                                       NOT NULL NUMBER
 GENDER                                    NOT NULL CHAR(1)

 SQL> desc Trip_Details;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 TRIPID                                    NOT NULL NUMBER
 DCONTACT                                  NOT NULL NUMBER
 PCONTACT                                  NOT NULL NUMBER
 PICKUP                                    NOT NULL VARCHAR2(50)
 DESTINATION                               NOT NULL VARCHAR2(50)
 AMOUNT                                    NOT NULL NUMBER
 DRIVER_RATING                                      NUMBER
 PASSENGER_RATING                                   NUMBER
 DATE_OF_TRAVEL                            NOT NULL DATE


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