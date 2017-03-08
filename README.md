# transparency-detection

This service detects the transparency rate of an image from a given url of this image. 
The service exposes one HTTP endpoint that allows you to request via the url of an image. 
This HTTP endpoint returns transparency rate of this image. This service uses serverless.

The logic behind this service is providing the ratio of # of transparent pixels over # of all pixels as transparency rate.

__Remark__ This service is inspired by the following stackoverflow question: [Identify whether a Png image has 100 percent transparent Background](http://stackoverflow.com/questions/23734939/identify-whether-a-png-image-has-100-percent-transparent-background)

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
