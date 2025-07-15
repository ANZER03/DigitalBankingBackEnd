# DigitalBankingBackEnd

## Overview

DigitalBankingBackEnd is a Java-based backend application designed to power digital banking solutions. Built with Spring Boot, it provides a comprehensive RESTful API for managing customers, bank accounts (both current and saving), performing transactions, and handling authentication and authorization securely using JWT tokens. The project aims to simulate core digital banking operations such as account creation, balance management, fund transfers, and user management.

## Features

- **Customer Management:** Create, retrieve, update, and manage banking customers.
- **Account Management:** Support for both current and saving bank accounts, including creation and retrieval.
- **Transactions:** Perform debit, credit, and transfer operations between accounts.
- **Authentication & Security:** JWT-based authentication and role-based access control using Spring Security.
- **Password Management:** Secure password handling, including change and validation.
- **RESTful API:** All banking operations are exposed as REST endpoints.

## How It Works

1. **Authentication:**  
   Users authenticate via the `/auth/login` endpoint, providing their username and password. Upon successful authentication, a JWT token is issued, which must be included in subsequent requests.

2. **Customer and Account Operations:**
    - Customers can be managed via `/customers` endpoints.
    - Bank accounts (current and saving) are created and managed via `/accounts` endpoints (e.g., `/accounts/saveSavingAccount`, `/accounts/saveCurrentAccount`).
    - Account operations such as credit, debit, and transfer are available via `/accounts/credit`, `/accounts/debit`, and `/accounts/transfer`.

3. **Transactions:**  
   Each bank account supports a history of operations, accessible via endpoints like `/accounts/{accountId}/operations`.

4. **Security:**  
   All sensitive operations are protected with JWT authentication. Passwords are encrypted using a `PasswordEncoder`.

5. **Data Initialization:**  
   On startup, the application seeds the database with sample customers and accounts using a `CommandLineRunner` bean.

## Project Structure

```
src/
└── main/
    └── java/
        └── ma/
            └── enset/
                └── digitalbankingbackend/
                    ├── dto/            # Data Transfer Objects (DTOs) for transferring data
                    ├── entities/       # JPA entities representing database models
                    ├── exceptions/     # Custom exception classes
                    ├── mappers/        # Mapper implementations for converting between entities and DTOs
                    ├── repositories/   # Spring Data JPA repositories
                    ├── security/       # Security configuration and controllers (JWT, Spring Security)
                    ├── services/       # Service layer implementing business logic
                    ├── web/            # REST controllers exposing endpoints
                    └── DigitalBankingBackEndApplication.java # Main Spring Boot application class
```

### Key Files

- **DigitalBankingBackEndApplication.java:** Main class; seeds initial data.
- **security/SecurityConfig.java:** Configures JWT-based security.
- **web/BankAccountAPI.java & CustomerRestController.java:** REST controllers for bank account and customer operations.
- **dto/**: Contains DTOs such as `TransferRequestDTO`, `DebitDTO`, etc.
- **entities/**: Entity classes like `Customer`, `CurrentAccount`, `SavingAccount`.

## Getting Started

1. **Clone the repository:**
   ```
   git clone https://github.com/ANZER03/DigitalBankingBackEnd.git
   ```

2. **Build the project:**
   ```
   ./mvnw clean install
   ```

3. **Run the application:**
   ```
   ./mvnw spring-boot:run
   ```

4. **Access the API:**  
   The REST API will be available at `http://localhost:8080/`.

## API Endpoints (Examples)

- `POST /auth/login` - Authenticate and get a JWT.
- `GET /customers` - List all customers.
- `POST /accounts/saveSavingAccount` - Create a saving account.
- `POST /accounts/transfer` - Transfer funds between accounts.
- `GET /accounts/{accountId}/operations` - Get account transaction history.