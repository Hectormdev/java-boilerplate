# ☕ JAVA BOILERPLATE

This is a Work in Progress Boilerplate for java.


## Prerequisites
- 🐳 Docker
- ☕️ Java 17
- 💡 IntelliJ Idea or another code editor


## Libraries and versions

- ☕ ️Java `17`
- 🌱 Spring-boot `2.7.6`
- 🐘 Gradle `7.5.1` 
- 🧪 Rest-assured `5.3.0`
 

## Run

### Running the App
>1. `./gradlew initialize`
>2. `./gradlew bootRun`

### Migration flow
> See the migration
>1. ``./gradlew liquibase diff `` 
>
> Generate the migration
>2. ``./gradlew liquibase diffChangelog``
>
> Execute migration
>3. ``./gradlew liquibase update``

### Running tests (TODO)
> 1. ...
> 2. ``./gradlew test``

### Compile without tests the App
> 1. `./gradlew build -x test`


### TO DO:
- [ ]  Inject from parameters the desired dependencies (for testing with memory or database).
- [X]  Implement Migrations.
- [ ]  Validation Error.
- [X]  `TimeStamp` in `DomainError` must be a unix.
- [X]  `User.equals` is lazy.
- [X]  See a way of not having to duplicate fixtures between tests.
- [ ]  Prepare the app for being able to be deployed.
- [ ]  Currently, we must use Spring-boot 2.7.3 to be able to be compatible with liquibase.
- [ ]  Search for a linter