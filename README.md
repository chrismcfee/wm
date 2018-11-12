# Back End Coding Challenge

This program reads a file of included addresses and finds the geolocation of each.

To run, call GetMapsData.main, passing in the absolute path to a file of addresses.

Example output
```
[{
  "address": "address",
  "status": "FOUND"
  "location": {
    "lat": 1234.56,
    "lng": 1234.56
  }
}]
```

For the file provided, the test GetMapsDataTest.addressesLargeTest will print the expected output 
for the provided addresses.

## Limitations

An optimal solution would have used a rate limiter rather than sleeping. By sleeping, this solution
ignores the amount of time a request takes to process. 

For some applications this might be okay, but for this one, it slows the process down by an order of 
magnitude. One implementation that would solve this is a Token Bucket algorithm.