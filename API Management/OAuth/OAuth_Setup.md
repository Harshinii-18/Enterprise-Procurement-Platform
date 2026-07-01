# OAuth 2.0 Setup

## Overview

The Enterprise Procurement Integration Platform is configured to support **OAuth 2.0 Client Credentials** authentication using SAP API Management.

OAuth is implemented to demonstrate enterprise-grade API security, where client applications obtain an access token before invoking protected APIs.

---

## Grant Type

**Client Credentials**

This grant type is intended for machine-to-machine communication where no end-user authentication is required.

---

## OAuth Policies

The following SAP API Management policies are configured:

| Policy | Purpose |
|---------|---------|
| GenerateAccessToken | Generates an OAuth 2.0 access token for authenticated client applications. |
| VerifyAccessToken | Validates incoming Bearer tokens before allowing access to protected APIs. |

---

## Token Endpoint

```
POST /oauth/token
```

---

## Token Request

### Authentication

**Basic Authentication**

| Parameter | Value |
|-----------|-------|
| Username | Client ID |
| Password | Client Secret |

### Request Body

Content-Type

```
application/x-www-form-urlencoded
```

Body

```
grant_type=client_credentials
```

---

## Sample Response

```json
{
  "access_token": "<generated-access-token>",
  "token_type": "Bearer",
  "expires_in": 1800
}
```

---

## Protected API Request

Example

```
GET /employees
```

Headers

```
Authorization: Bearer <access_token>
apiKey: <api_key>
```

---

## Authentication Flow

```
Client Application
        │
        │
        ▼
POST /oauth/token
        │
        ▼
GenerateAccessToken Policy
        │
        ▼
Access Token
        │
        ▼
Protected API Request
        │
        ▼
VerifyAccessToken Policy
        │
        ▼
API Proxy
        │
        ▼
SAP Cloud Integration
```

---

## Security Notes

- Client ID and Client Secret are generated during application registration.
- API Keys and OAuth credentials are **not included** in this repository.
- Access tokens are generated dynamically and have a limited lifetime.
- Secrets and tokens should never be committed to source control.

---

## Trial Environment Notes

The OAuth 2.0 Client Credentials flow was implemented and validated within the SAP BTP Trial environment.

During development, intermittent trial-environment issues affecting OAuth token verification and API Product matching were encountered. Consequently, **API Key authentication is used for the primary functional demonstrations**, while the OAuth configuration is retained to demonstrate enterprise security implementation.

---

## Related Artifacts

- API Product: `Enterprise_Procurement_APIs`
- Client Application: `Procurement_Client`
- Policies:
  - `OAuth_GenerateToken.xml`
  - `OAuth_VerifyToken.xml`