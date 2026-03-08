# Write your MySQL query statement below
SELECT id, movie, description, rating
FROM Cinema
WHERE id % 2 = 1
  AND LOWER(description) NOT LIKE '%boring'
  order by rating desc;
