# Couch Critics

## Description
Couch Critics is a web app which allows users to search IMDb's top 1000 movies and leave reviews, as well as comment on individual reviews.

## Team Member Contribution
Alex Tawes:
     - ER Diagram, normalization, and db design
     - Saved Movies page
     - Profile page 
     - Some CSS design
Evan Gavino:
     - Front end page structure
     - Movie search page
     - Individual review page
Henry Grinberg:
     - ddl.sql
     - Single Movie page with reviews
     - Reviews on the Singular movie page
Austin Miller:
     - Authentication management
     - Login, Register pages, frontend and backend
     - Splash page with list of all Movies

## Technologies Used
* Spring Boot
* Maven
* Bcrypt
* docker
* MySql

## Database information
name: couch_critics
username: root
password: mysqlpass

## Test (username, password) Pairs
1. (atawes, 123)
2. (bob, 123)
3. (dwade, 123)

## Instructions to run
To load csv data into Movie table:
1.) docker cp "YOUR FILE PATH\Term Project\imdb_top_1000.csv" mysql-server-4370:/var/lib/mysql-files
2.) In docker instance run the following
     use couch_critics;
     load data infile '/var/lib/mysql-files/imdb_top_1000.csv' into table Movie fields terminated by ',' enclosed by '"' lines terminated by '\n' ignore 1 rows (posterLink, title, year, runtime, genre, overview, director);
