# Claim Management System

This is a Claim Management System developed in Java. The system allows users to manage insurance claims, including creating, updating, deleting, and viewing all claims.

## Features

- Add a new claim
- Update an existing claim
- Delete a claim
- View all claims
- Handle insurance cards
- Handle customer and bank information

## Classes

- `Main`: The entry point of the application.
- `SystemView`: Handles the user interface and interactions.
- `ClaimController`: Handles the business logic for claims.
- `ClaimProcessManager`: Implements the `ClaimProcess` interface, manages the list of claims and customers.
- `FileHandler`: Handles reading from and writing to files.
- `Customer`, `Claim`, `Bank`, `InsuranceCard`: Represent the main entities in the system.

## How to Run

1. Compile all the java files in the project.
2. Run the `Main` class.

## Data

The system uses text files to store data:

- `src/customers.txt`: Stores customer data.
- `src/claims.txt`: Stores claim data.
- `src/bank.txt`: Stores bank data.
- `src/insuranceCard.txt`: Stores insurance card data.

## Author

Han Duc Khang - s3986602
