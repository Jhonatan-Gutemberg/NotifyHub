# Notification Hub

Module responsible for the project's notification system. Implements a flexible and extensible architecture for sending notifications through different channels and strategies.

## Overview

Notification Hub provides a robust solution for managing notifications throughout the application, using established design patterns to ensure scalability, maintainability, and ease of extension.

## Features

- **Multiple Strategies**: Support for different notification channels (email, logs, etc.)
- **Decorators**: Dynamic addition of features like logging and retry
- **Factory Pattern**: Flexible creation of notification objects
- **Builder Pattern**: Simplified construction of complex messages
- **Persistence**: Notification logging in database

## Module Structure

```
hub/
├── pom.xml
└── src/main/java/com/notifyhub/
    ├── Main.java
    ├── application/         # Application layer
    ├── domain/             # Domain models
    └── infra/              # Infrastructure layer
```

### Layers

**application/**: Contains application logic
- `builder/`: Builders for notification construction
- `config/`: Application configuration
- `decorator/`: Decorators to add features
- `exception/`: Custom exceptions
- `factory/`: Factories for object creation
- `templates/`: Notification templates
- `usecase/`: Use cases

**domain/**: Application domain models
- `Notification`: Main entity
- `NotificationType`: Notification types
- `Priority`: Priority levels
- `Recipient`: Notification recipient

**infra/**: Technical implementations
- `db/`: H2 database configuration
- `persistence/`: Persistence models
- `repository/`: Repository implementations

## Design Patterns Used

- **Builder**: Simplified construction of complex notifications
- **Decorator**: Dynamic addition of features (logging, retry)
- **Factory**: Creation of notification strategies
- **Observer**: Observers for notification events
- **Strategy**: Different sending strategies

## Dependencies

See the `pom.xml` file for the complete list of module dependencies.

## Build

```bash
mvn clean compile
```

## Run

```bash
mvn exec:java
```

## Test

```bash
mvn test
```

## Database

The project uses H2 as an in-memory database for development and testing. Tables are initialized automatically when the application starts.

## Custom Exceptions

- `ConfigurationException`: Configuration errors
- `NotificationSendException`: Errors when sending notifications
- `NotificationValidationException`: Validation errors
- `StrategyNotFoundException`: Strategy not found

## Contributions

Contributions are welcome! Please follow the patterns already established in the project.
