--This query is used in the search function of the app. It searches the movie table for a movie with a title matching the user provided string
--Page: localhost:8081/search?movieName=
SELECT m.title,m.year,m.movieId,m.posterLink FROM movie m WHERE m.title LIKE ?;

--This query calculates the average review score for a given movie
--Page: localhost:8081/search?movieName=  and  and localhost:8081/
SELECT r.movieId,avg(r.score) as average FROM review r WHERE r.movieId=? GROUP BY r.movieId;

--This query gets a specific review and the user who created it
--Page: localhost:8081/review/{reviewId}
SELECT u.firstName,u.lastName,u.userId,r.movieId,r.reviewDate,r.reviewText,r.score FROM review r, user u WHERE r.userId=u.userId AND r.reviewId=?;

--This query retrieves all comments on a specific review as well as the user's who wrote the comments
--Page: localhost:8081/review/{reviewId}
SELECT u.userId,u.firstName,u.lastName,c.commentDate,c.commentText,c.commentId FROM user u, comment c WHERE c.userId=u.userId AND c.reviewId = ?;

--This query retrieves all movies from the database
--Page: localhost:8081/
SELECT movieId, posterLink, title, year FROM Movie ORDER BY title;

--This query retrieves a specific user and compares it to a given username and password to authenticate and maintain a logged in user state
--Page: localhost:8081/login
SELECT userId,username,password,firstName,lastName FROM User WHERE username = ?;

--This query is used to register a new user with the given username, password, fname, and lname
--Page: localhost:8081/register
INSERT INTO User (username, password, firstName, lastName) values (?, ?, ?, ?);