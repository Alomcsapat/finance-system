# finance-system

# API Documentation

## Authentication

### POST `/api/auth/register`

**Description:** Register of new user

**Request Body:** `RegisterRequest`

```json
{
  "name": "string",
  "inn": "string",
  "email": "string",
  "phone": "string",
  "login": "string",
  "password": "string"
}
```

**Responses:**

* `200 OK` – registration is OK
* `400 Bad Request` – the user is not found or incorrect data is found

---

### POST `/api/auth/login`

**Description:** Authentication

**Request Body:** `LoginRequest`

```json
{
  "email": "string",
  "password": "string"
}
```

**Responses:**

* `200 OK` – returns `JwtResponse`

```json
{
    "token": "string",
    "accountId": number,
    "userId": number
}
```

* `401 Unauthorized` – wrong login or password

---

## Operations

All endpoints claims athorization through JWT

### POST `/api/operations/new`

**Description:** Creating of new operation

**Headers:**

* `Authorization: Bearer <token>`

**Request Body:** `OperationDto`

```json
{
    "accountId": number,
    "type": "string",
    "status": "string",
    "amount": number,
    "categoryTitle": "string",
    "contactId": number,
    "description": "string"
}
```

**Responses:**

* `201 Created` – operation is successfully created
* `400 Bad Request` – validation error

---

### GET `/api/operations/?<FILTERING_PARAMETER>`

**Description:** Filtering operations
**Headers:**

* `Authorization: Bearer <token>`

**Request Body:** `Empty`


**Responses:**

* `400 Bad Request` – validation error

List of operations
---


