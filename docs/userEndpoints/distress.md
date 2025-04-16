# NOTICE
Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.

# Distress

## Send Distress Signal
- Request Type: POST
- Description: Sends a distress signal to alert emergency contacts.
- Endpoint: /distress/send

#### Request Body:
```javascript
{
  "apikey": "string",
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
  "message": "string",
  "dashboardID": "string", 
  "distressReportID": "string"
}
```

## Get Distress ID 

- Request Type: GET
- Description: Get distress ID used for getting status and final report of distress signal
- Endpoint: /distress/getid

### Request Body:
```javascript
{
  "apikey": "string",
  "userID": "string"
}
```

### Response Body:
```javascript
{
  "message": "string",
  "distressID": "string"
}
```