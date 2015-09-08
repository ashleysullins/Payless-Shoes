# Payless-Shoes

#### By Ashley Sullins

## Description

This is an application that can be used for a shoe franchise to display different brands of shoes they carry at different stores within the brand. A user can add stores and brands to the database, and can associate a brand with a store and vice versa.

This is an exercise completed at the end of the fourth week of Epicodus, a code school, to see if we have successfully learned how to gather information out of databases using a join table. In this particular example, a join table was created to get store location information for specific brands and brand information for specific stores. 

## Setup

In order to install Payless-Shoes, you'll need to have [Java] (https://www.learnhowtoprogram.com/lessons/java-setup), [Gradle] (https://www.learnhowtoprogram.com/lessons/setting-up-a-project-with-gradle) and [Postgres] (https://www.learnhowtoprogram.com/lessons/installing-postgres) installed on your computer.

Databases are stored in the ``shoes.sql`` and ``shoes_test.sql`` files. To use them locally, type in ``psql [database_name] < [sqlfile]`` into your command line. You'll need to create the databases shoes and shoes_test inside of psql prior to running the psql command. 

You can also create the database manually using the following commands:

```
CREATE DATABASE shoes;
\c shoes;
CREATE TABLE stores (id serial PRIMARY KEY, name varchar, address varchar, phone varchar);
CREATE TABLE brands (id serial PRIMARY KEY, name varchar);
CREATE TABLE stores_brands (id serial PRIMARY KEY, brand_id int, store_id int);
CREATE DATABASE shoes_test WITH TEMPLATE shoes;
```

In order to run the application, you'll need to type ``gradle run`` on your command line. All additional libraries will be installed after running gradle. The local version of this application can be found at ``localhost:4567``.
 
## Tests

There are both unit tests for each object and integration tests associated with this application. The test files can be found under /src/test/java in the ``AppIntegrationTest.java``, ``BrandTest.java``, ``StoreTest.java`` files. To run the test suite, you'll need to type ``gradle test`` in your command line.

## Technologies Used

Technologies used to create this application include:

* Gradle
* Spark 
* Fluentlenium for integration testing and jUnit for unit testing
* Java 
* HTML and CSS with Bootstrap styling

### Legal

Copyright (c) 2015 Ashley Sullins

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED.