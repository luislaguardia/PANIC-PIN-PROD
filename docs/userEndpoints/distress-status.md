# NOTICE
Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.

# Distress Status

## Get Distress Status

- Request Type: POST 
- Description: Get status of distress signal (pending, deployed, resolved, cancelled)
- endpoint: /distress/status

### Request Body:
``` javascript
{
    "apikey": "string"
    "distressID" : "string",
    "userID" : "stirng"
}

```

### Response Body:
``` javascript
{
    "message": "string",
    "status": "string",
}
```


## Get Distress Signal Final Report

- Request Type: POST
- Description: After a distress signal is marked as resolved the dashboard that accepted the distress signal is expected to provide a comprehensive report of what happened and what they did.
- endpoint: /distress/finalreport

### Request Body
``` javascript
{
    "apikey": "string",
    "distressID": "string"
}
```

### Response Body:
``` javascript
{
    "message": "string",
    "report": "string"
}
```
