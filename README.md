# Weather Demo Server (RESTful API)

Weather Demo Server is a RESTful API (Java- Spring Boot) that exposed endpoints to serve

the FrontEnd App in repo [Weather Demo Client](https://github.com/DraQlaBlood/weather_demo_client).

Instructions how to run this API on localhost to serve a FrontEnd App
  * Running Weather Demo Server
  * Endpoints exposed by this API
  * Database SetUp

### Running Weather Demo server

1. Clone this repository anywhere on your computer

```
git clone https://github.com/DraQlaBlood/weather_demo_server.git
```

2. Run the application

The API can be tested using PostMan at

```
http://localhost:4040
```

### Endpoints exposed by this API

Once the API is running you can access the documentation here

```
http://localhost:4040/swagger-ui.html#/weather-controller
```

The endpoint provides the following information on classes

```java
//Agnecies

Agency{
  country	string
  id	string
  name	string
  type	string
  url	string
}

//Metrics

Metric{
  enabled	boolean
  id	string
  name	string
  unit	string
}

//Regions

Region{
  agency	string
  id	string
  name	string
  type	string
}

//Stations

Station{
  agency	string
  dataset	string
  elevation	number($double)
  iata	string
  id	string
  latitude	number($double)
  longitude	number($double)
  msc_ID	number($double)
  name	string
  provider	string
  region	string
  type	string
  wmo	string
}
```

### Database SetUp

Install MongoDB on your computer. The database name will be **weather_db** at port **27017**

This information can be modified in the **application.properties** file in the project

```java
  spring.data.mongodb.database = weather_db
  spring.data.mongodb.port=27017
```
