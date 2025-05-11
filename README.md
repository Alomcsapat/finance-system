# finance-system

# API Documentation

## Authentication

### POST `/api/auth/register`

**Description:** Регистрация нового пользователя

**Request Body:** `RegisterRequest`

```json
{
  "username": "string",
  "email": "string",
  "password": "string"
}
```

**Responses:**

* `200 OK` – регистрация успешна
* `400 Bad Request` – пользователь уже существует или данные некорректны

---

### POST `/api/auth/login`

**Description:** Аутентификация пользователя

**Request Body:** `LoginRequest`

```json
{
  "username": "string",
  "password": "string"
}
```

**Responses:**

* `200 OK` – возвращает `JwtResponse`

```json
{
  "token": "string",
  "type": "Bearer",
  "id": 0,
  "username": "string",
  "email": "string"
}
```

* `401 Unauthorized` – неправильный логин или пароль

---

## Operations

Все эндпоинты требуют авторизации через JWT.

### GET `/api/operations`

**Description:** Получение всех операций текущего пользователя

**Headers:**

* `Authorization: Bearer <token>`

**Responses:**

* `200 OK` – список `OperationDto[]`

---

### POST `/api/operations`

**Description:** Создание новой операции

**Headers:**

* `Authorization: Bearer <token>`

**Request Body:** `OperationDto`

```json
{
  "name": "string",
  "amount": 0,
  "category": "string",
  "date": "YYYY-MM-DD"
}
```

**Responses:**

* `201 Created` – операция успешно создана
* `400 Bad Request` – ошибки валидации

---

### GET `/api/operations/{id}`

**Description:** Получение операции по ID

**Headers:**

* `Authorization: Bearer <token>`

**Path Parameters:**

* `id` – ID операции

**Responses:**

* `200 OK` – `OperationDto`
* `404 Not Found` – операция не найдена или не принадлежит пользователю

---

### PUT `/api/operations/{id}`

**Description:** Обновление операции по ID

**Headers:**

* `Authorization: Bearer <token>`

**Path Parameters:**

* `id` – ID операции

**Request Body:** `OperationDto`

**Responses:**

* `200 OK` – операция обновлена
* `404 Not Found` – операция не найдена или не принадлежит пользователю

---

### DELETE `/api/operations/{id}`

**Description:** Удаление операции по ID

**Headers:**

* `Authorization: Bearer <token>`

**Path Parameters:**

* `id` – ID операции

**Responses:**

* `204 No Content` – операция удалена
* `404 Not Found` – операция не найдена или не принадлежит пользователю
