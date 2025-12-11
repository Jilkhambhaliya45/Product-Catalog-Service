Overview

PhaseZero Catalog Service is a Spring Boot–based RESTful microservice designed to manage a product catalog.
It provides APIs to add, list, search, filter, sort products, and calculate total inventory value.

This project demonstrates:
1. Clean REST API design
2. Proper layered architecture
3. Business rule enforcement
4. Structured and consistent API responses

Tech Stack
1. Java 17
2. Spring Boot 3
3. Spring MVC
4. Spring Data JPA
5. H2 / In-memory storage
6.Maven

How to Build and Run the Application
Prerequisites
Java 
Maven
IDE (Eclipse / IntelliJ / VS Code)

 Features
- Add new product
- List all products
- Search products by name (case-insensitive)
- Filter products by category
- Sort products by price (ascending)
- Calculate total inventory value
- Consistent validation and error handling


###  Application Design (Layered Architecture) ###

The project follows a clean layered architecture with proper separation of concerns:

1. Entity Layer
Represents the product data model used in your application.

-Responsibilities
Defines structure of Product (partNumber, partName, category, price, stock).
Maps to database or in-memory data structure.

-Example Class
Product

2. Repository Layer
Handles communication with the database or in-memory storage.

-Responsibilities
Save and retrieve Product data.
Perform search and filter queries.
Ensure unique partNumber.

-Example Class
ProductRepository

3. Data Access Object (DAO) Layer
Acts as an intermediate abstraction between Repository and Service.

-Responsibilities
Wraps repository methods.
Custom search, filtering, and fetching logic.
Provides a clean API for service layer.

-Example Class
ProductDao

4. Service Layer
Contains your entire business logic.

-Responsibilities
Validate product details.
Prevent duplicate partNumber.
Convert partName to lowercase before saving.
Handle sorting, filtering, and inventory calculation.
Communicate with DAO for data operations.

-Example Class
ProductService

5. Controller Layer
Exposes REST APIs to the client.

-Responsibilities
Handle HTTP requests (POST, GET, PUT, DELETE).
Pass incoming requests to Service Layer.
Return structured API responses.

-Example Class
ProductController

6. Data Transfer Object (DTO) Layer
Provides a standard response format for all API calls.

-Responsibilities
Wrap API responses with status code, message, and data.
Prevent exposing internal entity structures directly.

-Example Class
ResponseStructure<T>

7. Exception Layer
Handles all application exceptions in a clean and uniform way.

-Responsibilities
Throw custom exceptions (e.g., ProductNotFound, DuplicatePartNumber).
Provide user-friendly error messages.
Use Global Exception Handler for consistent error responses.

-Example Classes
GlobalExceptionHandler
Custom exception classes (e.g., ProductNotFoundException)

Product Data Model
| Field Name | Type   | Description                        |
| ---------- | ------ | ---------------------------------- |
| partNumber | String | Unique business identifier         |
| partName   | String | Product name (stored in lowercase) |
| category   | String | Product category                   |
| price      | double | Unit price                         |
| stock      | int    | Available quantity                 |



## Business Rules ##
partName is stored in lowercase
partNumber must be unique
price and stock cannot be negative



| HTTP Method | Endpoint                      | Description                     |
| ----------- | ----------------------------- | ------------------------------- |
| POST        | `/product`                    | Add a new product               |
| GET         | `/product`                    | List all products               |
| GET         | `/product/name/{partName}`    | Search by product name          |
| GET         | `/product/category/{category}`| Filter by category              |
| GET         | `/product/sort/{field}`       | Sort products by price          |
| GET         | `/product/inventory/value`    | Calculate total inventory value |

Example API Requests & Responses
POST /product
json
{
  "partNumber": "PN-1001",
  "partName": "Hydraulic Pump",
  "category": "Mechanical",
  "price": 4999.50,
  "stock": 120
}

response
{
    "data":{
  "partNumber": "PN-1001",
  "partName": "Hydraulic Pump",
  "category": "Mechanical",
  "price": 4999.50,
  "stock": 120
   },
    "message": "Product Details Saved Successfully",
    "statusCode": 201
}


Inventory Value
GET /product/inventory/value

{
  "statusCode": 200,
  "message": "Total Inventory Is Calculated Successfully",
  "data": 599940.0
}
