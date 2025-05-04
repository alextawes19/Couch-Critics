--This query is used in the search function of the app. It searches the movie table for a movie with a title matching the user provided string
--Page: localhost:8081/search?movieName=
SELECT m.title,m.year,m.movieId,m.posterLink FROM movie m WHERE m.title LIKE ?;

--This query calculates the average review score for a given movie
--Page: localhost:8081/search?movieName=
SELECT r.movieId,avg(r.score) as average FROM review r WHERE r.movieId=? GROUP BY r.movieId;

--This query gets a specific review and the user who created it
--Page: localhost:8081/review/{reviewId}
SELECT u.firstName,u.lastName,u.userId,r.movieId,r.reviewDate,r.reviewText,r.score FROM review r, user u WHERE r.userId=u.userId AND r.reviewId=?;

--This query retrieves all comments on a specific review as well as the user's who wrote the comments
--Page: localhost:8081/review/{reviewId}
SELECT u.userId,u.firstName,u.lastName,c.commentDate,c.commentText,c.commentId FROM user u, comment c WHERE c.userId=u.userId AND c.reviewId = ?;