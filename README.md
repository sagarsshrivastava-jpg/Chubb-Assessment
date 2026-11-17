# Chubb APAC Claims Platform

## Overview

Chubb APAC processes Motor and Property insurance claims across multiple APAC markets.

This platform digitizes the claims process by enabling:

- Claim submission
- Claim tracking
- Claims workflow management
- Audit trail tracking
- Claims approval and rejection
- Manager dashboard reporting

The objective is to replace phone calls, emails, spreadsheets, and shared inboxes with a centralized claims management platform.

---

# Architecture

## Architecture Style

Modular Monolith

```text
┌─────────────────────────┐
│      REST APIs          │
└──────────┬──────────────┘
           │
┌─────────────────────────┐
│ Spring Boot Application │
├─────────────────────────┤
│ Claim Module            │
│ Workflow Module         │
│ Dashboard Module        │
│ Audit Module            │
└─────────────────────────┘
           │
           ▼
     PostgreSQL
```

---

# Technology Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Lombok
- Validation API
- Swagger / OpenAPI

---

# Database Setup

Create Database:

```sql
CREATE DATABASE chubb_claims;
```

Update:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/chubb_claims
    username: postgres
    password: postgres
```

---

# Running Application

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run
```

Application URL:

```text
http://localhost:8080
```

Swagger URL:

```text
http://localhost:8080/swagger-ui.html
```

---

# Claim Lifecycle

```text
SUBMITTED
    |
    v
ASSIGNED
    |
    v
AWAITING_INFO
    |
    +---------------------+
    |                     |
    v                     |
APPROVED             REJECTED
```

---

# API Endpoints

Base URL

```text
http://localhost:8080
```

---

## 1. Create Claim

### Endpoint

```http
POST /api/claims
```

### Postman URL

```text
http://localhost:8080/api/claims
```

### Request

```json
{
  "policyNumber": "POL123456",
  "claimType": "MOTOR",
  "incidentDate": "2026-01-10",
  "estimatedAmount": 5000,
  "market": "Singapore",
  "claimantName": "John Doe",
  "email": "john@test.com",
  "phone": "9999999999"
}
```

### Success Response

```json
{
  "claimId": 1,
  "claimNumber": "CHB-81F46AC2",
  "status": "SUBMITTED"
}
```

---

## 2. Track Claim

### Endpoint

```http
GET /api/claims/{claimNumber}
```

### Postman URL

```text
http://localhost:8080/api/claims/CHB-81F46AC2
```

### Response

```json
{
  "claimId": 1,
  "claimNumber": "CHB-81F46AC2",
  "status": "SUBMITTED"
}
```

---

## 3. Assign Claim

### Endpoint

```http
POST /api/workflow/{claimId}/assign
```

### Postman URL

```text
http://localhost:8080/api/workflow/1/assign
```

### Request

```json
{
  "userId": 101,
  "userName": "Alice Tan"
}
```

### Response

```json
"Claim assigned successfully"
```

---

## 4. Request Additional Information

### Endpoint

```http
POST /api/workflow/{claimId}/request-info
```

### Postman URL

```text
http://localhost:8080/api/workflow/1/request-info
```

### Request

```json
{
  "remarks": "Please upload police report"
}
```

### Response

```json
"Additional information requested"
```

---

## 5. Approve Claim

### Endpoint

```http
POST /api/workflow/{claimId}/approve
```

### Postman URL

```text
http://localhost:8080/api/workflow/1/approve
```

### Request

```json
{
  "remarks": "Assessment completed",
  "approvedAmount": 5000
}
```

### Response

```json
"Claim approved"
```

---

## 6. Reject Claim

### Endpoint

```http
POST /api/workflow/{claimId}/reject
```

### Postman URL

```text
http://localhost:8080/api/workflow/1/reject
```

### Request

```json
{
  "remarks": "Policy expired before incident date"
}
```

### Response

```json
"Claim rejected"
```

---

## 7. Dashboard Metrics

### Endpoint

```http
GET /api/dashboard
```

### Postman URL

```text
http://localhost:8080/api/dashboard
```

### Response

```json
{
  "totalClaims": 100,
  "submittedClaims": 40,
  "approvedClaims": 35,
  "rejectedClaims": 15,
  "awaitingInfoClaims": 10
}
```

---

# Database Tables

## claims

```text
id
claim_number
policy_number
claim_type
status
incident_date
estimated_amount
market
created_at
updated_at
```

## claim_history

```text
id
claim_id
old_status
new_status
remarks
changed_at
```

## tasks

```text
id
claim_id
assigned_user_id
assigned_user_name
completed
assigned_at
```

---

# Error Response Example

### Claim Not Found

```json
{
  "message": "Claim not found : INVALID",
  "status": 404,
  "timestamp": "2026-11-17T11:00:00"
}
```

---

# Future Enhancements

- JWT Authentication
- Role Based Access Control
- Kafka Event Publishing
- Email Notifications
- Document Upload Support
- SLA Tracking
- Team Workload Dashboard
- Azure AD Integration

---

# Assessment Notes

This solution intentionally uses a Modular Monolith architecture instead of microservices to:

- Reduce deployment complexity
- Simplify development and testing
- Demonstrate clean separation of concerns
- Allow future migration to microservices if required

The architecture can be extended with Kafka and Notification services without major changes to the domain model.