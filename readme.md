# Getting Started
https://www.crazycall.com/blog/client-vs-customer

To run the project:
````
./gradlew assemble
docker-compose up
````

## Users

Users: Taavi, Juku, Elon

pwd: kukejaan

## Stack
1) Java 11
2) Spring boot
3) Persistence: Mybatis, postgres, liquibase
4) Thymeleaf
5) Docker

## Acceptance criteria
1. Correct all of the deficiencies in index.html

2. "Sectors" selectbox:
* Add all the entries from the "Sectors" selectbox to database
* Compose the "Sectors" selectbox using data from database

3. Perform the following activities after the "Save" button has been pressed:

* Validate all input data (all fields are mandatory)
* Store all input data to database (Name, Sectors, Agree to terms)
* Refill the form using stored data
* Allow the user to edit his/her own data during the session

4. Allow the user to edit his/her own data during the session
* 4.1 Ability to log in with users
   
### Acceptance criteria remarks:
Acceptance criteria #1 should be clarified.

Acceptance criteria #4 has scope creep

## Architecture ideas

---
### User properties (checkboxes, sectors etc..)
***In the current project I went with mixture of 1. and 3.***

_Possible ways how to store user properties  data in db:_
1. Create new schema for individual properties

Downside is that whenever we want more properties to be added, new services/entities/schemas have to be created.

Upside is that using polymorphism we can design a pattern that has moving parts (properties for customer in this case) so adding new ones over time is quite easy. 

We will have the benefit of db schemas.

Can scale well with heavy property specific logic.

Easy to debug/troubleshoot.

2. Use Entity Attribute Value model - When business requirements are unknown while we want to have the most flexibility

Downside:
Doesn't scale very well. Mostly considered as an anti-pattern. 

We will miss out on the schema properties as mostly entity values are added as VARCHAR - unless we create for each datatype separate EAV table (which will add to complexity and slow down adding new properties). 

Upside is that adding new properties is fast. 

When the EAV is only about single object and tightly guarded what gets added as an entity. Then it is easy and fast way to deliver software.

3. Expand user_profile entity horizontally

Downside is that without a proper documentation (this is mostly always the case) the table will become too complex and nobody really understands it. 

Anti-pattern if adding multiple values into single column per row. 

Tends to go into conflict with single responsibility principle.

Upside is that when the project isn't big it is very easy to maintain.

### Inserting sectors per user
In current solution we are just looping over the list of sectors we want to add for the user. This is fine for smaller scale applications. When there are thousands of users per minute updating their profiles then we could consider switching to batch insertion with mybatis. 

Also triggers on the table can cause performance overhead. I did indeed add a trigger for created_dtime. Often than not this small performance cost over weighs the business need to know when the customer updated something.
