# transparency-detection

This service detects the transparency rate of an image from a given url of this image. 
The service exposes one HTTP endpoint that allows you to request via the url of an image. 
This HTTP endpoint returns transparency rate of this image.

### Usage
* `GET {API_GATEWAY_URL}?url={URL}`

### Setup & Deployment

1. `mvn install`
2. `mvn package`
3. `serverless deploy`

The expected result should be similar to:
```
Service Information
service: transparency-detection
stage: production
region: us-east-1
api keys:
  None
endpoints:
  GET - https://***.execute-api.us-east-1.amazonaws.com/production/detect
functions:
  transparency-detection-production-detect: arn:aws:lambda:us-east-1:030505702965:function:transparency-detection-production-detect
```


### Sample Output
```
{
    "transparency_rate": 0.25869140625
}
```

### Sample errors
* Unknown host error for incorrect urls:
```
{"message":"An error occured: java.net.UnknownHostException: ***"}
```
* No protocol for when no when `http://` is not entered:
```
{"message":"An error occured: java.net.MalformedURLException: no protocol: "}
```
* Null pointer exception for when the given url does not belong to an image:
```
{"message":"An error occured: java.lang.NullPointerException"}
```
* If url query parameter is blank:
{"message":"url query parameter cannot be blank!"}