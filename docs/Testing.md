# Testing Guide

## Overview

The Enterprise Procurement Platform was tested using SAP API Management, SAP Cloud Integration (CPI), and Postman to validate authentication, policy enforcement, routing logic, backend integration, and error handling.

Testing focused on both successful request processing and failure scenarios to ensure enterprise-grade API behavior.

---

# Testing Environment

| Component | Tool |
|------------|------|
| API Testing | Postman |
| API Gateway Debugging | SAP API Management Debug Trace |
| Integration Monitoring | SAP Cloud Integration Monitor |
| Backend | Mock CPI Integration Flows |

---

# Authentication Testing

## API Key Authentication

Verified that requests are accepted only when a valid API Key is provided.

### Successful Test

- Valid API Key
- HTTP 200 OK

### Failure Test

- Missing API Key
- Invalid API Key

Expected Result

```
HTTP 401 Unauthorized
```

---

## OAuth 2.0

OAuth Client Credentials flow was configured and validated.

Tested scenarios:

- Token generation
- Access token verification
- Invalid access token
- Missing Authorization header

---

# Functional API Testing

The following APIs were tested.

## Procurement API

### GET

- Retrieve Purchase Orders
- Retrieve Purchase Order by ID
- Retrieve Materials
- Retrieve Business Partners

### POST

- Create Purchase Order

---

## Supplier API

### GET

- Retrieve Suppliers
- Retrieve Supplier by ID
- Retrieve Contracts

### POST

- Create Supplier

---

## Employee API

### GET

- Retrieve Employees
- Retrieve Employee by ID
- Retrieve Departments

---

# Policy Validation

The following SAP API Management policies were validated.

| Policy | Test Scenario |
|---------|---------------|
| Verify API Key | Valid and Invalid API Keys |
| OAuth | Token Generation and Verification |
| Spike Arrest | High request rate |
| Quota | Request limit exceeded |
| Response Cache | Repeated GET requests |
| Regular Expression Protection | XSS, SQL Injection, Path Traversal |
| JSON Threat Protection | Malformed JSON payloads |
| Raise Fault | Invalid business identifiers |

---

# Business Validation

JavaScript validation was tested for business-specific identifiers.

Validated formats include:

| API | Validation |
|------|------------|
| Procurement | Purchase Order ID |
| Supplier | Supplier ID |
| Employee | Employee ID |

Invalid requests return standardized HTTP 400 responses using the Raise Fault policy.

---

# Response Cache Testing

Caching was validated using repeated GET requests.

The implementation was verified to:

- Cache successful GET responses
- Skip POST requests
- Use a stable cache key

---

# Error Handling

The following error scenarios were validated.

- Missing API Key
- Invalid OAuth Token
- Invalid Supplier ID
- Invalid Employee ID
- Invalid Purchase Order ID
- Threat Protection policy violations
- Unsupported API operations

---

# Test Artifacts

The repository includes:

- Postman Collection
- Postman Environment
- API Management Debug Trace screenshots
- CPI screenshots
- API Management screenshots

These artifacts can be used to reproduce all documented test scenarios.