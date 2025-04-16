# NOTICE
Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.

# Friends


## Add Friend
- Request Type: POST
- Description: Sends a friend request to another user.
- Endpoint: /friends/add

#### Request Body:
```javascript
{
  "apikey": "string",
  "userId": "string",
  "friendId": "string"
}
```

#### Response Body: 
```javascript
{
   "message": "string",			 // eg: Friend request sent
   "status": "string"			 // eg: pending
}
```

## Remove Friend
- Request Type: POST
- Description: Remove a friend
- Endpoint: /friends/remove

### Request Body:
```javascript
{
  "apikey": "string",
  "userID": "string",
  "friendID": "string",
}
```

### Response Body:
```javascript
{
  "message": "string"
}
```

## Get Friends
- Request Type: GET
- Description: Retrieves a list of the user’s friends.
- Endpoint: /friends

#### Query Parameters:
```
    - apiKey
    - userID
```

#### Response Body:
```javascript
{
    "friends": [
    {
      "friendId": "string",
      "name": "string",
      "status": "string"
    }
  ]
}
```