# Demo project (Blog)

1. You may use [Blog Demo.postman_collection.json](Blog%20Demo.postman_collection.json) to use app.
2. It is decided that tag should be created separately (post with unknown tag should not be created)

# Task definition
Spring Boot app that provides an RESTfull API for the blog:
- get collection of posts (paginated, has optional filter by collection of tags)
- create a new post
- update for only post's tags
- delete a post by id

Post has:
- title (required)
- content (required)
- tags (optional)

Use in-memory database. Integration with database have to be implemented with Spring Data JPA(hibernate)
Separate database tables for posts and tags
The code is available via GitHub/EPAMGit

Additional requirements:
1. Use streams where it possible.
2. Use native queries for custom methods in repositories 
