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

> Initialize the database and the service
>1. `./gradlew bootRun`
>
> In order to close the database
>
>1. ``./gradlew composeDown``

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

> Memory tests
> 1. `./gradlew testMemory`
>
> H2 tests
> 1. `./gradlew testH2`
>
> Database tests
> 1. `./gradlew testDB`

### Compile without tests the App

> 1. `./gradlew build -x test`

### TO DO:

- [X]  Inject from parameters the desired dependencies (for testing with memory or database).
- [X]  Implement Migrations.
- [X]  Validation Error.
- [X]  `TimeStamp` in `DomainError` must be a unix.
- [X]  `User.equals` is lazy.
- [X]  See a way of not having to duplicate fixtures between tests.
- [X]  Prepare the app for being able to be deployed.
- [X]  Search for a linter (checkStyle)
- [X]  Liquibase generates a databaseChangeLog sentence in the sql EVERYTIME (only one is needed)
- [ ]  CI/CD

### Known Issues

- [ ]  Should the application layer receive the DTO?
- [ ]  Teardown of tests not always works as expected
- [ ]  Memory implementation injects h2 (even though is not used)

### Questions and roadmap

- [ ]  Currently, we must use Spring-boot 2.7.3 to be able to be compatible with liquibase.

