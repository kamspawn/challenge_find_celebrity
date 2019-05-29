* To Find the celebrity you need create a spreadsheet with xlsx format with the following rules:
1. Add the data to load in the sheet 1
2. The first row contains the row names Column A - [Code Id] | Column B - [Name] | Column c [Team Know List]
2.1. Code Id : will be integer not duplicated to identify the people
2.2. Name : will be string value with the people name
2.3. Team Know List : is a string value with the code id that know and will be separated by pipe |
 
* You need deploy jar file with MVN
- Execute the following command : mvn clean package

* the application will deploy the executable to target/FindCelebrity-jar-with-dependencies.jar

* To test only need run the following command :
 - java -jar FindCelebrity-jar-with-dependencies.jar file/name/path
 
 - i.e:  java -jar FindCelebrity-jar-with-dependencies.jar test-classes/Success_Example.xlsx 

* The application will load the file and output
 - Process Complete: when all be done and can identify the celebrity
 - Process Fail: when something issue was occurs