# API Management Policy Overview

## Overview

The Enterprise Procurement Platform uses SAP API Management policies to secure, protect, validate, and optimize API traffic before requests reach the SAP Cloud Integration (CPI) backend.

The implemented policies demonstrate enterprise API Gateway capabilities, including authentication, traffic management, threat protection, request validation, fault handling, and response optimization.

---

# Security Policies

## Verify API Key

**Purpose**

Validates the API Key supplied by the client application before allowing access to protected APIs.

**Applied To**

- Proxy PreFlow

**Result**

- Valid API Key → Request continues.
- Missing or invalid API Key → HTTP 401 Unauthorized.

---

## OAuth 2.0

**Policies**

- GenerateAccessToken
- VerifyAccessToken

**Grant Type**

- Client Credentials

**Purpose**

Provides token-based authentication for machine-to-machine communication.

> API Key authentication is used for the primary demonstrations due to SAP BTP Trial environment limitations affecting OAuth verification.

---

# Traffic Management

## Spike Arrest

**Purpose**

Protects backend systems against sudden traffic spikes by limiting the request rate.

**Applied To**

- Proxy PreFlow

**Configuration**

Example

```
30 requests per minute
```

**Benefit**

Prevents traffic bursts from overwhelming backend integrations.

---

## Quota

**Purpose**

Limits the number of API requests a client application can make during a defined time period.

**Applied To**

- Proxy PreFlow

**Configuration**

Example

```
100 requests per hour per application
```

**Benefit**

Ensures fair API consumption and protects backend resources.

---

# Threat Protection

## JSON Threat Protection

**Purpose**

Validates incoming JSON payloads to protect against excessively large or malicious request bodies.

**Applied To**

- POST operations

**Checks**

- Maximum object entries
- Maximum array elements
- Maximum nesting depth
- Maximum string length
- Maximum property name length

---

## Regular Expression Protection

**Purpose**

Blocks common attack patterns before requests reach the backend.

**Examples**

- Cross-Site Scripting (XSS)
- SQL Injection
- Path Traversal

**Applied To**

- Proxy PreFlow

---

# Business Validation

## JavaScript Validation

**Purpose**

Implements business-specific validation rules that cannot be handled using standard gateway policies.

**Current Validations**

Regex patterns are used to validate the PurchaseOrderId, SupplierId and EmployeeId in respective api proxies.

Example
```
EMP1001
```

Pattern

```
EMP + 4 digits
```


## Raise Fault

**Purpose**

Returns standardized error responses when business validation fails.

**Example Response**

```json
{
    "error": "Invalid Employee ID"
}
```

**HTTP Status**

```
400 Bad Request
```

---

# Performance Optimization

## Response Cache

**Purpose**

Caches successful GET responses to reduce repeated backend calls.

**Applied To**

- GET operations

**Benefits**

- Lower backend load
- Faster response times
- Reduced latency

---

# Enterprise Flow

```
Client Application
        │
        ▼
Verify API Key
        │
        ▼
OAuth Verification
        │
        ▼
Spike Arrest
        │
        ▼
Quota
        │
        ▼
Threat Protection
        │
        ▼
Business Validation
        │
        ▼
Response Cache
        │
        ▼
SAP Cloud Integration (CPI)
```