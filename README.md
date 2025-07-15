# Digital Banking Backend

A comprehensive backend application for digital banking operations built with Spring Boot, providing secure customer management, account operations, and transaction processing with JWT-based authentication.

## Project Overview

This Digital Banking Backend system is designed to provide core banking functionalities through a RESTful API. The application supports customer management, multiple account types (Current and Savings), transaction operations (credit, debit, transfer), and secure authentication with role-based access control.

The system is built using modern Java technologies including Spring Boot 3.4.6, Spring Security with OAuth2, and JPA for data persistence, making it suitable for both development and production environments.

## Main Features

### 🏦 Core Banking Operations
- **Customer Management**: Complete CRUD operations for customer accounts
- **Account Management**: Support for Current and Savings bank accounts
- **Transaction Processing**: Credit, debit, and transfer operations
- **Account History**: Detailed transaction history with pagination support
- **Balance Management**: Real-time balance updates and validation

### 🔐 Security & Authentication
- **JWT Authentication**: Secure token-based authentication system
- **Role-Based Access Control**: ADMIN and USER role management
- **Password Security**: BCrypt encryption for password storage
- **OAuth2 Integration**: Modern authentication standards compliance
- **CORS Support**: Cross-origin resource sharing configuration

### 📊 Data Management
- **Database Flexibility**: Support for both MySQL and H2 databases
- **Data Validation**: Comprehensive input validation and error handling
- **Transaction Safety**: Database transaction management
- **Sample Data**: Automatic initialization with test data

## How the System Works

### Authentication Flow
1. **Initial Setup**: System creates default users (including admin) on startup
2. **Login Process**: Users authenticate via `/auth/login` endpoint with credentials
3. **Token Generation**: Server generates JWT token with user roles and permissions
4. **API Access**: Clients include JWT token in Authorization header for API requests
5. **Authorization**: Server validates token and checks user permissions for each request

### Main APIs Overview
- **Customer API** (`/customers/**`): Customer lifecycle management
- **Account API** (`/accounts/**`): Bank account operations and transactions
- **Authentication API** (`/auth/**`): Login and token management
- **Security API**: Protected endpoints with role-based access

### Data Initialization
The application automatically initializes with sample data including:
- 5 default customers (Hassan, Imane, Mohamed, Anouar, and admin)
- Current and Savings accounts for each customer
- 10 random credit/debit transactions per account
- Admin user with full system access

## Project Structure

```
src/main/java/ma/enset/digitalbankingbackend/
├── DigitalBankingBackEndApplication.java    # Main Spring Boot application class
├── dto/                                     # Data Transfer Objects
│   ├── CustomerDTO.java                     # Customer data transfer object
│   ├── BankAccountDTO.java                  # Base bank account DTO
│   ├── CurrentBankAccountDTO.java           # Current account specific DTO
│   ├── SavingBankAccountDTO.java            # Savings account specific DTO
│   ├── AccountOperationDTO.java             # Transaction operation DTO
│   ├── AccountHistoryDTO.java               # Paginated account history DTO
│   ├── CreditDTO.java                       # Credit operation DTO
│   ├── DebitDTO.java                        # Debit operation DTO
│   └── TransferRequestDTO.java              # Transfer operation DTO
├── entities/                                # JPA Entity classes
│   ├── Customer.java                        # Customer entity with roles
│   ├── BankAccount.java                     # Abstract bank account entity
│   ├── CurrentAccount.java                  # Current account implementation
│   ├── SavingAccount.java                   # Savings account implementation
│   └── AccountOperation.java                # Transaction operation entity
├── repositories/                            # Data Access Layer
│   ├── CustomerRepository.java              # Customer data repository
│   ├── BankAccountRepository.java           # Bank account data repository
│   └── AccountOperationRepository.java      # Transaction operations repository
├── services/                               # Business Logic Layer
│   ├── BankAccountService.java              # Service interface
│   └── BankAccountServiceImp.java           # Service implementation
├── web/                                    # REST Controllers
│   ├── CustomerRestController.java          # Customer management endpoints
│   ├── BankAccountAPI.java                  # Account operations endpoints
│   └── SecurityRestController.java          # Authentication endpoints
├── security/                               # Security Configuration
│   ├── SecurityConfig.java                  # Spring Security configuration
│   └── CustomerDetailsService.java          # User details service implementation
├── mappers/                                # Entity-DTO Mapping
│   └── BankAccountMapperImpl.java           # Account mapping implementation
├── exceptions/                             # Custom Exception Classes
│   ├── CustomerNotFoundException.java       # Customer not found exception
│   ├── BankAccountNotFoundException.java    # Account not found exception
│   └── BalanceNotSufficientException.java   # Insufficient balance exception
└── enums/                                  # Enumeration Types
    ├── AccountStatus.java                   # Account status enumeration
    └── OperationType.java                   # Transaction type enumeration
```

### Key Configuration Files
- `pom.xml`: Maven dependencies and build configuration
- `application.properties`: Database and application configuration
- `src/main/resources/`: Resource files and configuration

## Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+** for dependency management
- **MySQL 8.0+** (or use H2 for development)
- **Git** for version control

### 1. Clone the Repository
```bash
git clone https://github.com/ANZER03/DigitalBankingBackEnd.git
cd DigitalBankingBackEnd
```

### 2. Database Configuration

#### Option A: MySQL Database (Recommended for Production)
1. Install and start MySQL server
2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/DigitalBanking?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### Option B: H2 Database (Development)
1. Comment out MySQL configuration in `application.properties`
2. Uncomment H2 configuration:
```properties
spring.datasource.url=jdbc:h2:mem:bank
spring.h2.console.enabled=true
```

### 3. Build the Application
```bash
# Clean and compile
mvn clean compile

# Run tests (optional)
mvn test

# Package the application
mvn clean package
```

### 4. Run the Application
```bash
# Using Maven
mvn spring-boot:run

# Or using the JAR file
java -jar target/DigitalBankingBackEnd-0.0.1-SNAPSHOT.jar
```

### 5. Access the Application
- **Base URL**: `http://localhost:8085`
- **H2 Console** (if using H2): `http://localhost:8085/h2-console`
- **API Documentation**: `http://localhost:8085/swagger-ui.html` (if SpringDoc is configured)

### 6. Default Login Credentials
The application creates default users on startup:
- **Admin User**: 
  - Username: `admin@gmail.com`
  - Password: `1234`
  - Roles: ADMIN, USER
- **Regular Users**: 
  - Usernames: `Hassan@gmail.com`, `Imane@gmail.com`, `Mohamed@gmail.com`, `Anouar@gmail.com`
  - Password: `1234`
  - Roles: USER

## Example API Endpoints

### Authentication
```http
# Login to get JWT token
POST /auth/login
Content-Type: application/json

{
  "username": "admin@gmail.com",
  "password": "1234"
}
```

### Customer Management
```http
# Get all customers
GET /customers
Authorization: Bearer <jwt_token>

# Get customer by ID
GET /customers/{id}
Authorization: Bearer <jwt_token>

# Create new customer
POST /customers
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "password123"
}

# Update customer
PUT /customers/{id}
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "John Smith",
  "email": "john.smith@example.com"
}

# Delete customer
DELETE /customers/{id}
Authorization: Bearer <jwt_token>

# Search customers
GET /customers/search?keyword=John
Authorization: Bearer <jwt_token>
```

### Bank Account Operations
```http
# Get all bank accounts
GET /accounts
Authorization: Bearer <jwt_token>

# Get account by ID
GET /accounts/{accountId}
Authorization: Bearer <jwt_token>

# Get customer's accounts
GET /customer/{customerId}/accounts
Authorization: Bearer <jwt_token>

# Get account operations history
GET /accounts/{accountId}/operations
Authorization: Bearer <jwt_token>

# Get paginated account history
GET /accounts/{accountId}/pageOperations?page=0&size=10
Authorization: Bearer <jwt_token>
```

### Transaction Operations
```http
# Credit account
POST /accounts/credit
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "accountId": "account-123",
  "amount": 1000.0,
  "description": "Salary deposit"
}

# Debit account
POST /accounts/debit
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "accountId": "account-123",
  "amount": 500.0,
  "description": "ATM withdrawal"
}

# Transfer between accounts
POST /accounts/transfer
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "accountSource": "account-123",
  "accountDestination": "account-456",
  "amount": 300.0
}
```

### Account Creation
```http
# Create Savings Account
POST /accounts/saveSavingAccount
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "initialBalance": 5000.0,
  "interestRate": 3.5,
  "customerId": 1
}

# Create Current Account
POST /accounts/saveCurrentAccount
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "initialBalance": 2000.0,
  "overDraft": 1000.0,
  "customerId": 1
}
```

## API Response Examples

### Successful Customer Response
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "roles": ["USER"]
}
```

### Account History Response
```json
{
  "accountOperationDTOS": [
    {
      "id": 1,
      "operationDate": "2024-01-15T10:30:00",
      "amount": 1000.0,
      "type": "CREDIT",
      "description": "Salary deposit"
    }
  ],
  "accountId": "account-123",
  "balance": 5000.0,
  "currentPage": 0,
  "totalPages": 3,
  "pageSize": 10
}
```

## Error Handling

The API returns appropriate HTTP status codes and error messages:

- `200 OK`: Successful operation
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid input data
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Insufficient permissions
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

Example error response:
```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Customer not found with ID: 999",
  "path": "/customers/999"
}
```

## Development Notes

### Database Schema
The application uses JPA with automatic schema generation. The database schema includes:
- `customer` table with role management
- `bank_account` table with inheritance for account types
- `account_operation` table for transaction history

### Security Configuration
- JWT tokens expire based on configuration
- CORS is configured to allow cross-origin requests
- Method-level security with `@PreAuthorize` annotations
- Password encoding with BCrypt

### Testing
- Unit tests available in `src/test/java`
- Integration testing with Spring Boot Test framework
- H2 database recommended for testing

## License

This project is open source and available under the [MIT License](LICENSE).

---

**Note**: This is a demo application for educational purposes. For production use, ensure proper security configurations, database optimizations, and comprehensive testing.