
# Location Tracking

## Get location of user
- Request Type: POST
- Description: Get past location of user (Uses distressID so dashboard cant just monitor where our users are)
- Endpoint: /dashboard/location/get

### Request Body
```javascript
{
    "apikey": "string",
    "distressID": "string",
}
```

### Response Body
```javascript
{
    "message": "string",
    "locations": [
        {
            "longitude": "float",
            "latutude": "float"
        }
    ] // list
}
```
