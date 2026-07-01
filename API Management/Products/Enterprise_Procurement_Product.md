# Enterprise Procurement APIs

## Overview

**Enterprise_Procurement_APIs** is the API Product configured in SAP API Management for the Enterprise Procurement Platform.

The product groups the enterprise API proxies into a single consumable product, allowing authorized client applications to subscribe and securely access the exposed APIs using API Keys and OAuth 2.0 Client Credentials.

---

## Product Name

`Enterprise_Procurement_APIs`

## Environment

SAP Integration Suite – Trial

---

## Included API Proxies

- Procurement API
- Supplier API
- Employee API

---

## Authentication

- API Key
- OAuth 2.0 (Client Credentials)

> **Note:** API Key authentication is used for the primary demonstrations in this project. OAuth 2.0 Client Credentials was implemented and validated within the SAP API Management trial environment.

---

## Consumer Access

Client applications must:

1. Subscribe to the **Enterprise_Procurement_APIs** product.
2. Obtain an API Key.
3. Generate an OAuth 2.0 access token (Client Credentials Grant).
4. Include the API Key and Bearer Token in each request.

---

## Related Artifacts

The API proxy configurations and policy implementations are available in:

```text
API_Management/
├── Proxies/
├── Policies/
└── OAuth/
```