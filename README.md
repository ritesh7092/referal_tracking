# Referral Tracking System

## Overview
A robust Spring Boot-based Referral Tracking System that enables user signup with comprehensive referral code tracking and management.

## Technology Stack
- Spring Boot 3.x
- MySQL Database
- Spring Security
- JPA/Hibernate
- Swagger/OpenAPI Documentation

## Features
- User Signup with Optional Referral Code
- Unique Referral Code Generation
- Profile Completion Tracking
- Referral Status Management
- CSV Referral Report Generation

## API Documentation

### 1. User Signup API
- **Endpoint:** `POST /api/signup`
- **Description:** Register a new user with optional referral code

#### Request Body
```json
{
    "email": "user@example.com",
    "password": "StrongPassword123!",
    "referralCode": "OPTIONAL_REFERRAL_CODE"
}
```

#### Response
- **Success:** 200 OK with User Details
- **Error:** 400 Bad Request (Validation Errors)

#### Validation Rules
- Email must be unique
- Password minimum 8 characters
- Valid email format

### 2. Profile Completion API
- **Endpoint:** `PUT /api/profile/{userId}`
- **Description:** Complete user profile after signup

#### Request Body
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "+1234567890"
}
```

#### Response
- **Success:** 200 OK with Updated User Profile
- **Error:** 404 Not Found (Invalid User ID)

#### Validation Rules
- First Name required
- Last Name required
- Valid Phone Number format

### 3. Get User Referrals API
- **Endpoint:** `GET /api/referrals/{userId}`
- **Description:** Retrieve all referrals for a specific user

#### Response
- **Success:** 200 OK with Referral List
- Includes referral status, dates, referred users

### 4. Referral Report API
- **Endpoint:** `GET /api/referrals/report`
- **Description:** Generate comprehensive CSV referral report

#### Response
- **Success:** CSV file with referral statistics
- Columns: User ID, Name, Referral Code, Total Referrals

## Referral Workflow
1. User A signs up (optional referral code)
2. User completes profile
3. If referred, referral status updated
4. Referrer gets credit for successful referral

## Setup Instructions

### Prerequisites
- Java 17+
- Maven
- MySQL Database

### Configuration
1. Database Configuration in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/referral_system
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Running the Application
```bash
# Build the application
mvn clean package

# Run the application
java -jar target/referral-tracking-1.0.0.jar
```

## Postman Testing

### Signup Workflow
1. Signup User B (Referrer)
2. Use Referrer's Code for User A Signup
3. Complete Profile for User A
4. Check Referral Status

## Error Handling
- Duplicate Email: 400 Bad Request
- Invalid Referral Code: 400 Bad Request
- Profile Completion Errors: Detailed Validation Messages

## Security
- Password Encrypted Storage
- Referral Code Validation
- Unique Referral Code Generation

## Monitoring & Logging
- Comprehensive Logging
- Referral Status Tracking
- Performance Metrics

## Scalability Considerations
- Efficient Referral Code Generation
- Optimized Database Queries
- Stateless Authentication
