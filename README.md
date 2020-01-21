# Reviews API

Supports operations for writing reviews and listing reviews for a product with no sorting or filtering <br/>
Built Using polyglot persistence layer(MYSQL and MongoDB) for a REST API.

# Getting Started

- In the project root folder run:<br/>
`mvn clean package`
- After the project is successfully build, run"<br/>
`java -jar target/reviews-api-0.0.1-SNAPSHOT.jar`

# APIs
When the Reviews project is running, the application is exposing multiple endpoints. Below, you can read what they do with some examples. For more information, run the app and visit: http://localhost:8080/swagger-ui.html

# Product

- POST request on /products/ will create a product.<br/>
```
{
	"name": "Redmi",
	"description": "Redmi Note 7 Plus",
	"companyName": "Redmi"
}
```

- GET request on /products/:id will retrieve a product.
- GET request on /products/ will retrieve all products.

# Review

- POST request on /reviews/products/:productId will create a review for a product with the given product id.<br/>
```
{
	"productId": 1,
	"authorName": "Jhon",
	"title": "This is Redmi review",
	"review": "This Redmi broke on me the other morning... :(",
	"score": 5
}
```

- GET request on /reviews/products/:id will retrieve a review including the comments of the review.

# Comment

- POST request on /comments/reviews/:reviewId will create a commend for a review with the given review id and return all reviews for the product.<br/>
```
{
	"authorName": "smith",
	"comment": "This is totally not true. My Redmi is working perfectly fine!"
}
```
- GET request on /comments/reviews/:reviewId will return a list of comments for a review with the given review id.
