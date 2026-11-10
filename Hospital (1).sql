--Michael Pack

Create Database Hospital

Use Hospital

Create Table Patient(
PatientID Integer not null,
Fname varchar(35) not null,
Lname varchar(35) not null,
DOB Date,
Age As DATEDIFF(year,DOB,GetDate()),
Email varchar(50) not null Unique,
Phone char(13) not null Unique,
Gender varchar(10),
Street varchar(50),
City varchar(40),
State char(2),
Zipcode char(10),
Primary Key(PatientID)
)

Create Table Bed(
BedID Integer not null,
PatientID Integer not null,
Primary Key (BedID),
Foreign Key(PatientID) References Patient (PatientID)
)

Create Table Personnel(
PersonnelID Integer not null,
Fname varchar(35) not null,
Lname varchar(35) not null,
Phone char(13) not null Unique,
Email varchar(50) not null Unique,
Position varchar(50) not null,
Specialization varchar(40),
HireDate Date,
Street varchar(50),
City varchar(40),
State char(2),
Zipcode char(10),
Primary Key (PersonnelID)
)

Create Table Department(
DepartmentCode Integer not null,
DepartmentName varchar(50) not null,
Primary Key (DepartmentCode)
)

Create Table Treatment(
TreatmentCode Integer not null,
TreatmentCost money,
TreatmentDuration time,
SideEffects varchar(50),
TreatmentDescription varchar(100),
Primary Key (TreatmentCode)
)

Create Table Test(
TestCode Integer not null,
TestCost money,
TestDate date,
TestResults varchar(50),
Primary Key (TestCode)
)

Create Table PatientTest(
PatientTestID Integer not null,
PatientID Integer not null,
TestCode Integer not null,
TestDate Date,
TestResults varchar(50),
Primary Key (PatientTestID),
Foreign Key(PatientID) References Patient (PatientID),
Foreign Key(TestCode) References Test (TestCode)
)

Create Table PatientTreatment(
PatientTreatmentID Integer not null,
TreatmentCode Integer not null,
PatientID Integer not null,
Date Date,
Notes varchar(50),
Primary Key (PatientTreatmentID),
Foreign Key(PatientID) References Patient (PatientID),
Foreign Key(TreatmentCode) References Treatment (TreatmentCode)
)

Create Table PatientPersonnel(
PatientPersonnelID Integer not null,
PatientID Integer not null,
PersonnelID Integer not null,
PatientPersonnelDescription varchar(50),
Primary Key (PatientPersonnelID),
Foreign Key(PatientID) References Patient (PatientID),
Foreign Key(PersonnelID) References Personnel (PersonnelID)
)

insert into Patient values(11002433,'John','Doe','07/27/2013','JohnD@gmail.com','4043792400','Male','756 Creek Hill','SnellVille','GA','30016')
insert into Patient values(11002670,'Sara','Jane','08/30/2003','SJ@gmail.com','7727655061','Female','87 Maria Lane','Grayson','GA','30017')
insert into Patient values(11002509,'Oscar','Gatson','05/07/2006','OGatson@gmail.com','4047897700','Male','450 Grey Mark','Marrietta','GA','30060')
Select * from Patient

insert into Bed values(20030,11002433)
insert into Bed values(20032,11002670)
insert into Bed values(20033,11002509)
Select * from Bed

insert into Personnel values(100456,'Holmes','Charleston','3401544500','HC@Yahoo.com','Nurse','General Health','11/21/2014','861 Water Creek','Hudson','GA','30014')
insert into Personnel values(100468,'Rina','Williamson','4037904000','RinaW@hotmail.com','Doctor','General Health','06/11/2010','772 Ride Road','Snellville','GA','30016')
insert into Personnel values(100579,'Walter','Martin','2403468790','WM@gmail.com','Doctor','Physician','10/01/2018','67 Nelson Drive','Grayson','GA','30017')

select * from Personnel

insert into Department values(007,'Medicine')
insert into Department values(004,'Mental Health')
insert into Department values(003,'Physical Health')
Select * from Department

insert into Treatment values(0709,7500.00,'00:20:00','Slight Headaches','Pills taken every 3 days') 
insert into Treatment values(0801,400.00,'00:05:00','None','Medication taken every morning') 
insert into Treatment values(0766,180.00,'01:00:00','Increased Exhuastion','Medication taken at night') 
Select * from Treatment

insert into Test values(505,800.00,'04/17/2023','No signs of Abnormalities')
insert into Test values(568,600.00,'07/20/2024','Slight signs of defects')
insert into Test values(599,700.00,'06/16/2022','Significant signs of Abnormalities')
Select * from Test

insert into PatientTest values(70067,11002433,505,'04/17/2023','No signs of Abnormalities')
insert into PatientTest values(70014,11002670,568,'07/20/2024','Slight signs of defects')
insert into PatientTest values(70070,11002509,599,'06/16/2022','Significant signs of Abnormalities')
Select * from PatientTest

insert into PatientTreatment values(4034,0709,11002670,'07/23/2024','Pills taken every 3 days')
insert into PatientTreatment values(4090,0801,11002509,'06/20/2022','Medication taken every morning')
insert into PatientTreatment values(4067,0766,11002433,'04/17/2023','Medication taken at night')
Select * From PatientTreatment

insert into PatientPersonnel values(2045,11002670,100456, 'Holmes Charleston is assigned to patient Sara Jane')
insert into PatientPersonnel values(2069,11002433,100468, 'Rina Williamson is assigned to patient John Doe')
insert into PatientPersonnel values(2070,11002509,100579, 'Walter Martin is assigned to patient Oscar Gatson')
Select * from PatientPersonnel

--Assingment 4
Create View [Patient's Bed] As
Select Patient.PatientID As [Patient ID], Bed.BedID As [Bed Number], Fname As [Patient First Name], Lname As [Patient Last Name]
From Patient join Bed on Patient.PatientID = Bed.PatientID

Select * from [Patient's Bed]

Create View [Patient's Treatmnet] As
Select Patient.PatientID As [Patient ID], Fname As [Patient First Name], Lname As [Patient Last Name], Treat.TreatmentCode As [Treatment Number], Treat.TreatmentDescription As [Treatment Description]
From Patient join PatientTreatment PTreat on Patient.PatientID = PTreat.PatientID join Treatment Treat on PTreat.TreatmentCode = Treat.TreatmentCode

Select * from [Patient's Treatmnet]

Create View [Patient's Test] As
Select Patient.PatientID As [Patient ID], Fname As [Patient First Name], Lname As [Patient Last Name], T.TestCode As [Test Number], T.TestDate As [Test Date], T.TestResults As [Test Results]
From Patient join PatientTest PT on Patient.PatientID = PT.PatientID join Test T on PT.TestCode = T.TestCode

Select * from [Patient's Test]

Create View [Patient's Personnel] As
Select Patient.PatientID As [Patient ID], Patient.Fname As [Patient First Name], Patient.Lname As [Patient Last Name], Personnel.PersonnelID As [Personnel ID], Personnel.Fname As [Personnel First Name], Personnel.Lname As [Personnel Last Name], PP.PatientPersonnelDescription As [Assingment]
From Patient join PatientPersonnel PP on Patient.PatientID = PP.PatientID join Personnel on PP.PersonnelID = Personnel.PersonnelID

Select * From [Patient's Personnel]

Select * from Patient order by Lname

insert into Patient values(11002444,'Rod','Copper','05/14/2015','RoDC@gmail.com','1343804400','Male','780 Stone Walk','Atlanta','GA','30026')
insert into Patient values(11002384,'Scott','Granger','05/14/2015','Scott@Hotmail.com','1093452400','Male','780 Stone Walk','Woodstock','GA','30005')
insert into Test values(510,300.00,'08/11/2024','Bone Fracture')
insert into PatientTest values(70038,11002444,510,'08/11/2024','Bone Fracture')
insert into Treatment values(0871,350.00,'00:10:00','None','Medication taken every 2 hours')
insert into PatientTreatment values(4096,0871,11002384,'02/22/2022','Medication taken every 2 hours')


Create View [Patient's Test In Atlanta] As
Select Patient.PatientID As [Patient ID], Fname As [Patient First Name], Lname As [Patient Last Name], City As [Test Location], T.TestCode As [Test Number], T.TestDate As [Test Date], T.TestResults As [Test Results]
From Patient join PatientTest PT on Patient.PatientID = PT.PatientID join Test T on PT.TestCode = T.TestCode
where City = 'Atlanta'

Select * from [Patient's Test In Atlanta]

Create View [Patient's Treatment in Woodstock/Roswell] As
Select Patient.PatientID As [Patient ID], Fname As [Patient First Name], Lname As [Patient Last Name], City As [Location],Treat.TreatmentCode As [Treatment Number], Treat.TreatmentDescription As [Treatment Description]
From Patient join PatientTreatment PTreat on Patient.PatientID = PTreat.PatientID join Treatment Treat on PTreat.TreatmentCode = Treat.TreatmentCode
where City in ('Woodstock','Roswell')

Select * From [Patient's Treatment in Woodstock/Roswell]