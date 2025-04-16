# NOTICE

Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.

## Send Area Review

- Request Type: POST
- Description: Send area review
- Endpoint: /area/review

### NOTE:

as of now the review system is not very defined this might be subject to change

### Request Body:

```javascript
{
    "apikey": "string",
    "location": {
        "longitude": "float"
        "latitude": "float"
    }
    "review": "string"
}
```

### Response Body:

```javascript
{
    "message": "string"
}
```

## Get Reviews from are radius

- Request Type: POST
- Description: get reviews from radius from location origin
- Endpoint: /area/get

### Request Body:

```javascript
{
    "apikey": "string",
    "location": {
        "longitude": "float",
        "latitude" : "float"
    }
    "radius": "float"

}
```

### Response Body:

```javascript
{
    "message": "string",
    "reviews": [{
        "location": {
            "longitude": "float",
            "latitude" : "float"
        }
        "review" : "string"
    }]  // note:  this is a list of reviews

}
```
