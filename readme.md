# ☕ JAVA BOILERPLATE

This is a Work in Progress Boilerplate for java.


## Prerequisites
- 🐳 Docker
- ☕️ Java 17
- 💡 IntelliJ Idea or another code editor


## Libraries and versions

- ☕ ️Java `17`
- 🌱 Springboot ``3.0.0``
- 🐘 Gradle `7.5.1`
- ⛵️ Jakarta 
- 🧪 Rest-assured `5.3.0`
 

## Run

### Running the App
>1. `docker-compose up`
>2. `./gradlew bootRun`

### Running tests (TODO)
> 1. ...
> 2. ``./gradlew test``

### Compile without tests the App
> 1. `./gradlew build -x test`


### TO DO:
- [ ]  Inject from parameters the desired dependencies (for testing with memory or database).
- [ ]  Implement Migrations.
- [ ]  Validation Error.
- [ ]  Timestamp in domainError must be an unix.
- [ ]  `User.equals` is lazy.
- [ ]  See a way of not having to duplicate fixtures between tests.
- [ ]  Prepare the app for being able to be deployed.