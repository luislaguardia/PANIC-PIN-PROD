# NOTICE
The authentication API would be overhauled to use OAuth specifications

# Auth 

Notes:
- Use hash(sha256) for the password when making an api request for security

## Sign in
- Request Type: POST
- Description: Creates a new user account. API key in reponse
- Endpoint: /user/auth/signin

#### Request Body:
```javascript
{
    "username": "string",
    "password": "string",
}
```


#### Response Body:
```javascript
{
  "message": "string",
  "userId": "string",
  "apikey": "string"
}

```

## Sign up

- Request Type: POST
- Description: Creates a new user account.
- Endpoint: /user/auth/signup

#### Request Body:
```javascript
{
    "username": "string",
    "password": "string",
    "email": "string"
    "fullname": "string",
    "phoneno": "string",
    "gender": "string",
    "birthday": "string"
}

```

#### Response Body:
```javascript
{
    "message": "string",
}
```

