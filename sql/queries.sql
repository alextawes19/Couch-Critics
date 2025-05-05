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

--This query is used to obtain user information such as their firstName, lastName, username, number of reviewed and saved movies
--Page: http://localhost:8081/profile
SELECT u.username, u.firstName, u.lastName, COUNT(DISTINCT r.reviewId) AS rev_count, COUNT(DISTINCT s.movieId) AS save_count FROM User u LEFT JOIN Review r ON u.userId = r.userId LEFT JOIN Save s ON u.userId = s.userId WHERE u.userId = ? GROUP BY u.username, u.firstName, u.lastName;

--This query gets the movie information for the movies the user reviewed
--Page: http://localhost:8081/profile
SELECT m.movieId, posterLink, title, year, r.score FROM Movie m JOIN Review r ON r.movieId = m.movieId AND r.userId = ?;

--This query gets the movie information for the movies the user saved
--Page: http://localhost:8081/saved
SELECT m.movieId, posterLink, title, year FROM Movie m JOIN Save s ON s.movieId = m.movieId AND s.userId = ?

--This insert statement inserts the movie the user chose to save
--Page: http://localhost:8081/movie/{movieId}
INSERT INTO save VALUES (" + userId + "," + movieId + ");

--This delete statement deletes the movie the user chooses to unsave
--Page: http://localhost:8081/movie/{movieId}
DELETE FROM save WHERE movieId = " + movieId + " AND userId = " + userId + ";

-- This query fetches details about a singular movie.
-- joins the movie table with the review table with a left join. 
SELECT movie.movieId, movie.title, movie.overview, movie.posterLink, movie.runtime, movie.genre, movie.director, movie.year, review.score
FROM movie LEFT JOIN review ON review.movieId = movie.movieId
WHERE movie.movieId = {movieId};

-- Check if the current user has saved the movie. 
-- If count is > 0 then movie is saved otherwise it is not.
SELECT COUNT(*) AS isSaved
FROM Save
WHERE movieId = {movieId} AND userId = {userId};

-- adds a new review into the review table. 
INSERT INTO review (userId, movieId, reviewDate, reviewText, score) 
VALUES (?, ?, NOW(), ?, ?);

-- retrieves all reviews for a given movieId as well as first name and last name from the user table.
-- checks if the review has any comments by using count() 
-- number of comments. 
SELECT r.reviewId, r.movieId, r.userId, u.firstName, u.lastName, r.reviewDate, r.reviewText, r.score,
IF(COUNT(c.commentId) = 0, FALSE, TRUE) AS hasComments,
COUNT(c.commentId) AS commentCount
FROM review r
JOIN user u ON r.userId = u.userId
LEFT JOIN comment c ON r.reviewId = c.reviewId
WHERE r.movieId = ?
GROUP BY r.reviewId, r.movieId, r.userId, u.firstName, u.lastName, r.reviewDate, r.reviewText, r.score;

-- get details about a specific review 
SELECT u.firstName, u.lastName, u.userId, r.movieId, r.reviewDate, r.reviewText, r.score
FROM review r, user u
WHERE r.userId = u.userId AND r.reviewId = ?;

-- gets all comments from a specific review. 
SELECT u.userId, u.firstName, u.lastName, c.commentDate, c.commentText, c.commentId
FROM user u, comment c
WHERE c.userId = u.userId AND c.reviewId = ?;

-- insert a new comment
INSERT INTO comment (userId, reviewId, commentDate, commentText)
VALUES (?, ?, NOW(), ?);

