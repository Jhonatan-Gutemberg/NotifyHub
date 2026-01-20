# Notification Hub - Documentação de Padrões de Projeto

## 📋 Descrição do Projeto

O **Notification Hub** é uma aplicação Java que demonstra a implementação de diversos padrões de projeto (Design Patterns) em um sistema de gerenciamento e envio de notificações. O projeto utiliza **Java 21 LTS** e **Maven** como ferramenta de build.

## 🏗️ Arquitetura

A arquitetura do projeto é dividida em três camadas principais:

### 1. **Domain Layer** (Camada de Domínio)
Contém as entidades de negócio e regras de domínio:
- `Notification` - Entidade principal de notificação
- `Recipient` - Destinatário da notificação
- `NotificationMessage` - Conteúdo da mensagem
- `NotificationType` - Tipo de notificação
- `Priority` - Prioridade da notificação

### 2. **Application Layer** (Camada de Aplicação)
Implementa os padrões de projeto e casos de uso:
- Builder, Decorator, Factory, Strategy, Template Method, Observer

### 3. **Infrastructure Layer** (Camada de Infraestrutura)
Implementações concretas de persistência e envio:
- Banco de dados H2
- Estratégia de envio por Email
- Repositório de logs

---

## 🧭 Architectural Style (Clean Architecture)

This project follows principles inspired by **Clean Architecture**, ensuring a clear separation of concerns and high maintainability.

Key principles applied:

- **Domain layer is framework-agnostic**
- **Application layer orchestrates use cases and business flows**
- **Infrastructure layer depends on abstractions (ports)**
- **Dependency Inversion Principle (DIP)** is enforced through interfaces

This approach allows the system to evolve with minimal impact when introducing new notification channels or infrastructure changes.


## 🎯 Padrões de Projeto Implementados

### 1️⃣ **Builder Pattern**

Utilizado para construir objetos complexos `Notification` de forma fluente e segura.

```mermaid
classDiagram
    class NotificationBuilder {
        -Recipient recipient
        -NotificationMessage message
        -NotificationType type
        -Priority priority
        +setRecipient(Recipient) NotificationBuilder
        +setMessage(NotificationMessage) NotificationBuilder
        +setType(NotificationType) NotificationBuilder
        +setPriority(Priority) NotificationBuilder
        +build() Notification
    }
    
    class Notification {
        -NotificationId id
        -Recipient recipient
        -NotificationMessage message
        -NotificationType type
        -Priority priority
        -Instant createdAt
    }
    
    NotificationBuilder --> Notification : cria
```

---

### 2️⃣ **Strategy Pattern**

Define diferentes estratégias de envio de notificações de forma intercambiável.

```mermaid
classDiagram
    class INotificationStrategy {
        <<interface>>
        +send(Notification) void
    }
    
    class EmailNotificationStrategy {
        -JavaMailSender mailSender
        +send(Notification) void
    }
    
    class NotificationUseCase {
        -INotificationStrategy strategy
        -List~INotificationObserver~ observers
        +sendNotification(Notification) void
        -notifyObservers(Notification) void
    }
    
    INotificationStrategy <|.. EmailNotificationStrategy
    NotificationUseCase --> INotificationStrategy : usa
```

---

### 3️⃣ **Decorator Pattern**

Adiciona comportamentos extras às estratégias de notificação sem modificar seu código.

```mermaid
classDiagram
    class INotificationStrategy {
        <<interface>>
        +send(Notification) void
    }
    
    class NotificationDecorator {
        <<abstract>>
        #wrapped: INotificationStrategy
        +send(Notification) void
    }
    
    class LoggingDecorator {
        +send(Notification) void
    }
    
    class RetryDecorator {
        -maxRetries: int
        -retryDelay: long
        +send(Notification) void
    }
    
    class EmailNotificationStrategy {
        +send(Notification) void
    }
    
    INotificationStrategy <|.. NotificationDecorator
    NotificationDecorator <|-- LoggingDecorator
    NotificationDecorator <|-- RetryDecorator
    INotificationStrategy <|.. EmailNotificationStrategy
    NotificationDecorator --> INotificationStrategy : decora
```

---

### 4️⃣ **Factory Pattern**

Cria instâncias de notificações e estratégias de forma centralizada.

```mermaid
classDiagram
    class NotificationFactory {
        -INotificationStrategyFactory strategyFactory
        +create(Recipient, NotificationMessage, NotificationType, Priority) Notification
    }
    
    class INotificationStrategyFactory {
        <<interface>>
        +create(String) INotificationStrategy
    }
    
    class NotificationIdFactory {
        +generate() NotificationId
    }
    
    class Notification
    class INotificationStrategy {
        <<interface>>
    }
    
    NotificationFactory --> NotificationIdFactory
    NotificationFactory --> INotificationStrategyFactory : usa
    NotificationFactory --> Notification : cria
    INotificationStrategyFactory --> INotificationStrategy : cria
```

---

### 5️⃣ **Observer Pattern**

Notifica múltiplos observadores quando uma notificação é enviada.

```mermaid
classDiagram
    class INotificationObserver {
        <<interface>>
        +update(Notification) void
    }
    
    class DatabaseLogObserver {
        -INotificationLogRepository repository
        +update(Notification) void
    }
    
    class NotificationUseCase {
        -List~INotificationObserver~ observers
        +registerObserver(INotificationObserver) void
        +removeObserver(INotificationObserver) void
        -notifyObservers(Notification) void
    }
    
    class INotificationLogRepository {
        <<interface>>
        +save(NotificationLog) void
    }
    
    INotificationObserver <|.. DatabaseLogObserver
    NotificationUseCase --> INotificationObserver : notifica
    DatabaseLogObserver --> INotificationLogRepository : usa
```

---

### 6️⃣ **Template Method Pattern**

Define a estrutura do algoritmo em uma classe base e deixa as subclasses implementarem partes específicas.

```mermaid
classDiagram
    class HtmlEmailTemplate {
        <<abstract>>
        #buildSubject() String
        #buildBody() String
        #buildFooter() String
        +generate() String
    }
    
    class DesignPatternsEmailTemplate {
        #buildSubject() String
        #buildBody() String
        #buildFooter() String
    }
    
    HtmlEmailTemplate <|-- DesignPatternsEmailTemplate
```

---

## 📦 Dependências Principais

```xml
<!-- Database -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.2.224</version>
</dependency>

<!-- Email Support -->
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>2.0.1</version>
</dependency>

<!-- Environment Variables -->
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>

<!-- Logging -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.12</version>
</dependency>

<!-- SMS Support -->
<dependency>
    <groupId>com.twilio.sdk</groupId>
    <artifactId>twilio</artifactId>
    <version>9.0.0</version>
</dependency>
```

---

## 🗂️ Estrutura de Diretórios

```
notifications/hub/
├── src/main/java/com/notifyhub/
│   ├── application/          # Camada de Aplicação
│   │   ├── builder/          # Builder Pattern
│   │   ├── config/           # Configurações
│   │   ├── decorator/        # Decorator Pattern
│   │   ├── exception/        # Exceções personalizadas
│   │   ├── factory/           # Factory Pattern
|   |   ├── observer/         # Observer Pattern
│   │   ├── port/             # Interfaces (Strategy, Observer, Repository)
│   │   ├── templates/        # Template Method Pattern
│   │   └── usecase/          # Casos de uso
│   ├── domain/               # Camada de Domínio
│   │   ├── Notification.java
│   │   ├── NotificationId.java
│   │   ├── NotificationMessage.java
│   │   ├── NotificationType.java
│   │   ├── Priority.java
│   │   └── Recipient.java
│   ├── infra/                # Camada de Infraestrutura
│   │   ├── db/               # Conexão com banco de dados
│   │   ├── persistence/      # Modelos de persistência
│   │   ├── repository/       # Implementações de repositório
│   │   └── EmailNotificationStrategy.java
│   └── Main.java             # Ponto de entrada
└── pom.xml                   # Configuração Maven
```

---

## 🔧 Casos de Uso

### Enviar Notificação com Logs e Retry

```java
// Criar notificação
Notification notification = new NotificationBuilder()
    .setRecipient(new Recipient("user@example.com"))
    .setMessage(new NotificationMessage("Olá", "Bem-vindo!"))
    .setType(NotificationType.EMAIL)
    .setPriority(Priority.HIGH)
    .build();

// Aplicar decoradores (Logging + Retry)
INotificationStrategy strategy = new EmailNotificationStrategy();
strategy = new LoggingDecorator(strategy);
strategy = new RetryDecorator(strategy, 3, 1000);

// Executar caso de uso
NotificationUseCase useCase = new NotificationUseCase(strategy);
useCase.registerObserver(new DatabaseLogObserver(repository));
useCase.sendNotification(notification);
```

---

## 🚀 Como Compilar e Executar

### Pré-requisitos
- Java 21 LTS
- Maven 3.9.12+

### Compilar
```bash
cd notifications/hub
mvn clean compile
```

### Executar Testes
```bash
mvn test
```

### Build Completo
```bash
mvn clean package
```

### Executar Aplicação
```bash
mvn exec:java -Dexec.mainClass="com.notifyhub.Main"
```

---

## 📊 Diagrama Geral de Classes

```mermaid
classDiagram
    direction TB
    
    class Notification {
        -NotificationId id
        -Recipient recipient
        -NotificationMessage message
        -NotificationType type
        -Priority priority
        -Instant createdAt
    }
    
    class NotificationBuilder {
        +setRecipient(Recipient)
        +setMessage(NotificationMessage)
        +setType(NotificationType)
        +setPriority(Priority)
        +build() Notification
    }
    
    class INotificationStrategy {
        <<interface>>
        +send(Notification)
    }
    
    class EmailNotificationStrategy {
        +send(Notification)
    }
    
    class NotificationDecorator {
        <<abstract>>
        #wrapped: INotificationStrategy
        +send(Notification)
    }
    
    class LoggingDecorator {
        +send(Notification)
    }
    
    class RetryDecorator {
        +send(Notification)
    }
    
    class NotificationUseCase {
        -INotificationStrategy strategy
        -List~INotificationObserver~ observers
        +sendNotification(Notification)
    }
    
    class INotificationObserver {
        <<interface>>
        +update(Notification)
    }
    
    class DatabaseLogObserver {
        +update(Notification)
    }
    
    NotificationBuilder --> Notification
    INotificationStrategy <|.. EmailNotificationStrategy
    INotificationStrategy <|.. NotificationDecorator
    NotificationDecorator <|-- LoggingDecorator
    NotificationDecorator <|-- RetryDecorator
    NotificationUseCase --> INotificationStrategy
    NotificationUseCase --> INotificationObserver
    INotificationObserver <|.. DatabaseLogObserver
```

---

## 📝 Recursos Educacionais

Este projeto é um excelente exemplo de implementação dos seguintes padrões GOF (Gang of Four):

- ✅ **Creational**: Builder, Factory
- ✅ **Structural**: Decorator
- ✅ **Behavioral**: Strategy, Observer, Template Method

---

## 👨‍💻 Versão do Java

**Java 21 LTS** - Long Term Support

Benefícios da upgrade para Java 21:
- ✨ Record classes (atualizado)
- 🔒 Sealed classes melhorado
- ⚡ Pattern matching avançado
- 🧵 Virtual Threads (Project Loom)
- 🎯 Melhor performance e segurança

---

## 📄 Licença

Este projeto é fornecido como material educacional.

---

**Última atualização**: Janeiro 2026
**Versão Java**: 21 LTS
**Versão Maven**: 3.9.12+
