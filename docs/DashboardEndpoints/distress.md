# NOTICE
Our API key system would be overhauled using OAuth specification. All API keys as fields is not implemented but should be still passed.

We will use a technique called polling for realtime updates for distress signals. 

# What is polling

Polling is a technique where we call something(in this case an api endpoint) periodically, inspecting the output and then acting based on it. For our purposes this would be always polling data from the server checking if an distress signal is assigned to it. if not wait for some time and poll data again.

# Poll Distress Signal
- Request Type: POST
- Description: checks if a distress signal is assigned to this dashboard. (use polling)
- enpoint /dashboard/distress/poll

### Request Body
```javascript
{
    "apikey": "string",
    "dashboardID": "string"
}
```

### Response Body
```javascript
{
    "message": "string",
    "distressId": "string",
}
```
