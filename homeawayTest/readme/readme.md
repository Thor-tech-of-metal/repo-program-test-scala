
### Old swagger scala example. 

\m/

Build
======
sbt clean compile

Server
======

Start: sbt run

Debug:  sbt -jvm-debug 9999 run

Test
====
sbt test

Covered functionality
======================
1a) add valid
1b) add update --> validation invalid format

2a) get by id valid
2b) get by id --> validation not found
2c) get By id --> validation invalid id

3a) update valid
3b) update --> validation not found
3c) update --> validation invalid id
3d) update --> validation invalid format

4a) delete valid
4b) delete --> validation not found
4c) delete --> validation invalid id


Urls
====

All endpoints:
http://localhost:9000/listings/swagger.json

GET  :  http://localhost:9000/listings/5e22a83a-6f4f-11e6-8b77-86f30ca893d3

POST : http://localhost:9000/listings

{
  "contact" : {
    "phone" : "15126841100",
    "formattedPhone" : "+1 512-684-1100"
  },
  "address" : [ {
    "address" : "1011 york road",
    "postalCode" : "Sw11 4SW",
    "countryCode" : "UK",
    "city" : "Austin",
    "state" : "London",
    "country" : "UK"
  } ],
  "location" : {
    "lat" : "40.4255485534668",
    "lng" : "-3.7075681686401367"
  }
}

PUT http://localhost:9000/listings

{
  "id" : "f7f952bd-b954-4599-a9b2-ab0428405e76",
  "contact" : {
    "phone" : "15126841100",
    "formattedPhone" : "+1 512-684-1100"
  },
  "address" : [ {
    "address" : "1011 york road",
    "postalCode" : "Sw11 4SW",
    "countryCode" : "UK",
    "city" : "Austin",
    "state" : "London",
    "country" : "UK"
  } ],
  "location" : {
    "lat" : "40.4255485534668",
    "lng" : "-3.7075681686401367"
  }
}

DELETE : http://localhost:9000/listings/5e22a83a-6f4f-11e6-8b77-86f30ca893d3

Notes: improvements that could  be done:

1) test coverage for controller using injected mocks

2) using latest version of play and swagger.
Unfortunately it was not done due to the the test development was done in airplane not internet :(






