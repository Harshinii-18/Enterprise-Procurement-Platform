# Security Architecture

## Overview

The Enterprise Procurement Platform secures all API traffic through SAP API Management before requests are forwarded to SAP Cloud Integration (CPI).

The security implementation follows a layered defense approach, combining authentication, authorization, traffic management, threat protection, request validation, and controlled error handling to protect backend services from unauthorized access and malicious requests.

---

# Security Architecture

```
Client Application
        │
        ▼
Verify API Key
        │
        ▼
OAuth 2.0 (Client Credentials)
        │
        ▼
Spike Arrest
        │
        ▼
Quota
        │
        ▼
Regular Expression Protection
        │
        ▼
JSON Threat Protection
        │
        ▼
Business Validation
(JavaScript)
        │
        ▼
Raise Fault
        │
        ▼
Response Cache
        │
        ▼
SAP Cloud Integration
```

---

# Authentication

## API Key Authentication

All API requests are validated using the **Verify API Key** policy.

The API Key identifies the consuming application and determines whether the application is subscribed to the configured API Product.

Unauthenticated requests are rejected with:

```
HTTP 401 Unauthorized
```

---

## OAuth 2.0

The project includes support for **OAuth 2.0 Client Credentials** authentication.

Configured OAuth policies:

- GenerateAccessToken
- VerifyAccessToken

The OAuth implementation demonstrates machine-to-machine authentication commonly used in enterprise integrations.

> **Note**
>
> Due to SAP BTP Trial environment limitations, API Key authentication is used for the primary demonstrations contained in this repository.

---

# Traffic Management

To protect backend integrations against excessive traffic, the following policies are implemented:

- Spike Arrest
- Quota

These policies ensure controlled API consumption while preventing abuse and accidental traffic spikes.

---

# Threat Protection

Incoming requests are inspected before reaching SAP Cloud Integration.

Implemented protections include:

- JSON Threat Protection
- Regular Expression Protection

The Regular Expression Protection policy blocks common attack patterns including:

- Cross-Site Scripting (XSS)
- SQL Injection
- Path Traversal
- HTML Injection

JSON Threat Protection validates request payload size and complexity by limiting:

- Object entry count
- Array size
- Container depth
- Property name length
- String length

---

# Business Validation

Business-specific validation rules are implemented using JavaScript policies.

Current validations include:

| API | Validation |
|------|------------|
| Employee API | Employee ID must follow `EMP####` |
| Supplier API | Supplier ID must follow `SUP####` |
| Procurement API | Purchase Order must follow mock SAP PO format |

Invalid requests return standardized error responses using the Raise Fault policy.

---

# Response Optimization

Successful GET requests are cached using the Response Cache policy.

Caching is enabled only for idempotent operations to improve performance while avoiding stale data for write operations.

---

# Security Principles

The implementation follows the following security principles:

- Authenticate every client.
- Protect backend services from excessive traffic.
- Validate every incoming request.
- Reject malicious payloads before backend processing.
- Return consistent error responses.
- Cache only safe, read-only operations.
- Never expose credentials in source control.

---

# Related Documentation

- Policy Overview: `API_Management/Policies/Policy_Overview.md`
- OAuth Configuration: `API_Management/OAuth/OAuth_Setup.md`
- Deployment Guide: `Deployment.md`