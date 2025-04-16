
# Distress Status

## Send dispatch status of distress signal
- Request Type: POST
- Description: send dispatch status (pending, deployed, resolved, cancelled)
- endpoint: /dashboard/distress/status

### Request Body:
```javascript
{
    "apikey": "string",
    "distressID": "string",
    "status": "string"
}
```

### Response Body:
```javascript
{
    "message": "string"
}
```


## Send Final Report
- Request Type: POST
- Description: After setting the status of distress signal as resolved dashboard is expected to fill up a final report
- Endpoint: /dashboard/distress/report


### Request Body:
```javascript
{
    "apikey": "stirng",
    "distressID": "string",
    "report": "string",
}
```

### Response Body:
```javascript
{
    "message": "string"
}
```