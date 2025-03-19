### Build The Project Locally

```./gradlew build```

### Build the Local Docker image for your application

```docker build -t shopping-cart .```
#### . specifies the location of you docker file

### Run the Docker image on local
```docker run -p 8080:8080 shopping-cart```
