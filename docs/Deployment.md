# Deployment Guide

## Overview

This document describes the deployment steps required to configure the Enterprise Procurement Platform using SAP Integration Suite.

# Prerequisites

Before deployment, ensure the following services are available:

- SAP Integration Suite
- Cloud Integration
- API Management
- SAP BTP Trial or Enterprise Account
- Postman

---

# Deployment Order

The recommended deployment sequence is:

```
Cloud Integration

↓

API Management

↓

API Product

↓

Developer Application

↓

Postman Testing
```

---

# Step 1 — Deploy Cloud Integration Artifacts

Import and deploy the following integration flows.

| Integration Flow |
|------------------|
| Procurement_Interface |
| Supplier_Interface |
| Employee_Interface |

Verify that each HTTP endpoint is successfully deployed and reachable.

---

# Step 2 — Deploy API Proxies

Import the following API proxies into SAP API Management.

| API Proxy |
|-----------|
| Procurement_API |
| Supplier_API |
| Employee_API |

Verify that each proxy is deployed successfully.

---

# Step 3 — Configure Policies

Each API proxy includes the following policies.

- Verify API Key
- OAuth 2.0
- Spike Arrest
- Quota
- Response Cache
- Regular Expression Protection
- JSON Threat Protection
- Raise Fault

Policy execution can be verified using SAP API Management Debug Trace.

---

# Step 4 — Create API Product

Create the API Product:

```
Enterprise_Procurement_APIs
```

Include the following API proxies:

- Procurement API
- Supplier API
- Employee API

Publish the API Product.

---

# Step 5 — Create Developer Application

Create a Developer Application.

Example:

```
Procurement_client
```

Subscribe the application to:

```
Enterprise_Procurement_APIs
```

Generate:

- API Key
- OAuth Client Credentials (if applicable)

> **Important:** Do not commit generated credentials to source control.

---

# Step 6 — Import Postman Collection

Import:

- Enterprise_Procurement.postman_collection.json
- Enterprise_Procurement.postman_environment.json

Configure the following environment variables:

| Variable | Description |
|----------|-------------|
| baseUrl | API Gateway Base URL |
| apiKey | Generated API Key |
| accessToken | OAuth Access Token |

---

# Step 7 — Validate Deployment

Execute the following requests.

## Procurement

- GET Purchase Orders
- POST Purchase Order

## Supplier

- GET Suppliers
- POST Supplier

## Employee

- GET Employees

Verify:

- Successful responses
- Policy execution
- Debug Trace
- CPI Message Processing Logs

---

# Monitoring

Deployment validation can be performed using:

## SAP API Management

- API Proxies
- Debug Trace
- Analytics

## SAP Cloud Integration

- Monitor
- Message Processing Logs
- Integration Flow Status

---

# Repository Artifacts

The repository includes:

- Cloud Integration artifacts
- API Management configuration
- Policy definitions
- API Product documentation
- OAuth documentation
- Postman Collection
- Architecture diagrams
- Testing documentation

---

# Deployment Notes

This project currently uses mock backend integrations implemented in SAP Cloud Integration.

The architecture is designed so that mock services can be replaced with actual SAP enterprise systems, such as SAP S/4HANA, SAP Ariba, and SAP SuccessFactors, without requiring changes to the API Management layer.