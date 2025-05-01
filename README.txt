To load csv data into Movie table:
1.) docker cp "YOUR FILE PATH\Term Project\imdb_top_1000.csv" mysql-server-4370:/var/lib/mysql-files
2.) In docker instance run the following
     use couch_critics;
     load data infile '/var/lib/mysql-files/imdb_top_1000.csv' into table Movie fields terminated by ',' enclosed by '"' lines terminated by '\n' ignore 1 rows (posterLink, title, year, runtime, genre, overview, director);