# NOTICE
Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.


# User Location

## Send Location
- Request Type: POST
- Description: Shares the user’s current location.
- Endpoint: /location/send

Note: This should be called for a set interval (Cotton Reccomends sending one once per 10 or 5 seconds);

#### Request Body:
```javascript
{
  'apikey", "string",
  "userId": "string",
  "location": {
    "latitude": "float",
    "longitude": "float"
  }
}
```

#### Response Body:
```javascript
{
  "message": "string"
}
```




