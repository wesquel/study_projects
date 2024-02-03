
# Simple Authentication

Simple authentication service in Java with **Spring Security** from Spring Framework.

## Documentation API

#### Authentication

```http
  POST /api/auth/login
```

| Parameter   | Type       | Desc                           |
| :---------- | :--------- | :---------------------------------- |
| `usernameOrEmail` | `string` | **Required**. User username or email. |
| `password` | `string` | **Required**. User password. |

#### Return accessToken and tokenType.

```http
  GET /api/admin
```

| Auth   | Desc                           |
| :---------- | :---------------------------------- |
| `BearToken` | **Required**. Token generated with admin login. |

#### Return "Hello Admin".

```http
  GET /api/user
```

| Auth   | Desc                           |
| :---------- | :---------------------------------- |
| `BearToken` | **Required**. Token generated with user login. |

#### Return "Hello User".