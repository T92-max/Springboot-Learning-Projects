# Spring Boot Application - Getting Started

A simple Spring Boot application demonstrating the basics of Spring Boot auto-configuration, dependency injection, and component scanning.

## Overview

This project demonstrates:
- Spring Boot application setup and configuration
- Component scanning and auto-wiring
- Using ApplicationContext to retrieve Spring beans
- Spring Boot's simplified configuration vs traditional Spring

## Prerequisites

- Java Development Kit (JDK) 11 or higher (recommended: JDK 17 or 21)
- Maven 3.6+ or Gradle 7+
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

## Project Structure

```
springboot-first/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/teju/SpringbootFirst/
│       │       ├── SpringbootFirstApplication.java
│       │       ├── Simple.java
│       │       └── Computer.java (optional)
│       └── resources/
│           └── application.properties
├── pom.xml (or build.gradle)
└── README.md
```

## Dependencies

### Maven (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.teju</groupId>
    <artifactId>SpringbootFirst</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>SpringbootFirst</name>
    <description>Demo project for Spring Boot</description>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Gradle (build.gradle)

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.teju'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

## Required Files

### 1. Simple.java (Component Class)

Create the `Simple.java` class in the same package as your main application:

```java
package com.teju.SpringbootFirst;

import org.springframework.stereotype.Component;

@Component
public class Simple {
    
    public Simple() {
        System.out.println("Simple object created by Spring Boot");
    }
    
    public void code() {
        System.out.println("Simple is coding...");
        System.out.println("Writing awesome Spring Boot code!");
    }
}
```

### 2. With Dependency Injection (Optional)

**Computer.java:**
```java
package com.teju.SpringbootFirst;

import org.springframework.stereotype.Component;

@Component
public class Computer {
    
    public Computer() {
        System.out.println("Computer object created");
    }
    
    public void compile() {
        System.out.println("Computer is compiling code...");
    }
}
```

**Simple.java (with dependency):**
```java
package com.teju.SpringbootFirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Simple {
    
    @Autowired
    private Computer computer;
    
    public Simple() {
        System.out.println("Simple object created by Spring Boot");
    }
    
    public void code() {
        System.out.println("Simple is coding...");
        if (computer != null) {
            computer.compile();
        }
    }
}
```

### 3. application.properties (Optional Configuration)

Create in `src/main/resources/`:

```properties
# Application name
spring.application.name=SpringbootFirst

# Server port (if adding web support)
server.port=8080

# Logging configuration
logging.level.root=INFO
logging.level.com.teju.SpringbootFirst=DEBUG

# Custom properties (optional)
app.message=Hello from Spring Boot!
```

## How It Works

### 1. @SpringBootApplication Annotation

```java
@SpringBootApplication
public class SpringbootFirstApplication {
    // ...
}
```

This single annotation is equivalent to:
- `@Configuration`: Marks the class as a configuration class
- `@EnableAutoConfiguration`: Enables Spring Boot's auto-configuration
- `@ComponentScan`: Scans for components in the current package and sub-packages

### 2. Application Startup

```java
ApplicationContext context = SpringApplication.run(SpringbootFirstApplication.class, args);
```

This line:
- Starts the Spring Boot application
- Creates the Spring ApplicationContext
- Auto-configures beans based on classpath dependencies
- Scans for `@Component`, `@Service`, `@Repository`, `@Controller` annotations

### 3. Bean Retrieval

```java
Simple sm = context.getBean(Simple.class);
sm.code();
```

Spring Boot automatically:
- Finds the `Simple` class marked with `@Component`
- Creates an instance
- Manages its lifecycle
- Injects any dependencies

## Running the Application

### Using Maven:

```bash
# Clean and compile
mvn clean install

# Run the application
mvn spring-boot:run
```

### Using Gradle:

```bash
# Build the project
gradle clean build

# Run the application
gradle bootRun
```

### Using IDE:

1. Import the project as a Maven/Gradle project
2. Wait for dependencies to download
3. Right-click `SpringbootFirstApplication.java`
4. Select "Run As" → "Java Application"

### Using JAR:

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/SpringbootFirst-0.0.1-SNAPSHOT.jar
```

## Expected Output

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

Simple object created by Spring Boot
Simple is coding...
Writing awesome Spring Boot code!
```

## Key Concepts

### Spring Boot vs Traditional Spring

| Aspect | Traditional Spring | Spring Boot |
|--------|-------------------|-------------|
| Configuration | XML or Java Config | Auto-configuration |
| Dependencies | Manual setup | Starter dependencies |
| Deployment | Complex WAR deployment | Embedded server, JAR |
| Setup time | Hours | Minutes |

### Component Scanning

Spring Boot automatically scans for components in:
- The package of the main application class
- All sub-packages

**Example:**
```
com.teju.SpringbootFirst        ← Main class here
├── SpringbootFirstApplication  ← Scanned
├── Simple                      ← Scanned
├── Computer                    ← Scanned
└── service/
    └── MyService               ← Scanned (sub-package)
```

### Dependency Injection Methods

#### 1. Field Injection (Not Recommended)
```java
@Component
public class Simple {
    @Autowired
    private Computer computer;
}
```

#### 2. Constructor Injection (Recommended)
```java
@Component
public class Simple {
    private final Computer computer;
    
    @Autowired
    public Simple(Computer computer) {
        this.computer = computer;
    }
}
```

#### 3. Setter Injection
```java
@Component
public class Simple {
    private Computer computer;
    
    @Autowired
    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
```

## Common Annotations

### Component Stereotypes

```java
@Component      // Generic component
@Service        // Business logic layer
@Repository     // Data access layer
@Controller     // Web layer (requires spring-boot-starter-web)
@RestController // REST API endpoints
```

### Configuration Annotations

```java
@Configuration  // Configuration class
@Bean           // Define a bean manually
@Value          // Inject property values
@ConfigurationProperties // Bind properties to POJO
```

### Example with @Service and @Repository

```java
@Repository
public class SimpleRepository {
    public String getData() {
        return "Data from repository";
    }
}

@Service
public class SimpleService {
    @Autowired
    private SimpleRepository repository;
    
    public void processData() {
        String data = repository.getData();
        System.out.println("Processing: " + data);
    }
}

@Component
public class Simple {
    @Autowired
    private SimpleService service;
    
    public void code() {
        service.processData();
    }
}
```

## Adding Web Support

To create a web application, add the web starter:

**Maven:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**Create a REST Controller:**
```java
package com.teju.SpringbootFirst;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    
    @Autowired
    private Simple simple;
    
    @GetMapping("/code")
    public String code() {
        simple.code();
        return "Check console for output!";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
```

Access at: `http://localhost:8080/hello`

## Project Enhancements

### 1. Add Logging

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Simple {
    private static final Logger logger = LoggerFactory.getLogger(Simple.class);
    
    public void code() {
        logger.info("Simple is coding...");
        logger.debug("This is a debug message");
    }
}
```

### 2. Use Property Values

```java
@Component
public class Simple {
    @Value("${app.message:Default Message}")
    private String message;
    
    public void code() {
        System.out.println(message);
    }
}
```

### 3. Add Profiles

**application-dev.properties:**
```properties
app.message=Development Environment
```

**application-prod.properties:**
```properties
app.message=Production Environment
```

**Run with profile:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Troubleshooting

### NoSuchBeanDefinitionException

**Problem**: Spring cannot find the `Simple` bean.

**Solutions**:
1. Ensure `Simple.java` is in the same package or sub-package as `SpringbootFirstApplication`
2. Add `@Component` annotation to `Simple` class
3. Check for typos in class name

### Port Already in Use (if using web)

**Solution**:
```properties
# In application.properties
server.port=8081
```

### Dependencies Not Downloading

**Maven Solution**:
```bash
mvn clean install -U
```

**Gradle Solution**:
```bash
gradle clean build --refresh-dependencies
```

## Best Practices

1. **Use Constructor Injection**: More testable and immutable
2. **Follow Package Structure**: 
   ```
   com.teju.SpringbootFirst/
   ├── SpringbootFirstApplication.java
   ├── controller/
   ├── service/
   ├── repository/
   └── model/
   ```
3. **Externalize Configuration**: Use `application.properties`
4. **Use Profiles**: Separate configs for dev, test, prod
5. **Enable DevTools**: For automatic restart during development
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-devtools</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```
6. **Write Tests**: Use `@SpringBootTest` for integration tests

## Testing

**SimpleTest.java:**
```java
package com.teju.SpringbootFirst;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SimpleTest {
    
    @Autowired
    private Simple simple;
    
    @Test
    void testSimpleBean() {
        assertNotNull(simple);
        simple.code(); // Should not throw exception
    }
}
```

**Run tests:**
```bash
mvn test
# or
gradle test
```

## Next Steps

1. **Add Database Support**: Include `spring-boot-starter-data-jpa` and a database driver
2. **Build REST APIs**: Add `spring-boot-starter-web` and create controllers
3. **Add Security**: Include `spring-boot-starter-security`
4. **Implement Validation**: Use `spring-boot-starter-validation`
5. **Add Actuator**: Monitor your app with `spring-boot-starter-actuator`

## Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Boot Guides](https://spring.io/guides)
- [Spring Initializr](https://start.spring.io/) - Bootstrap new projects
- [Baeldung Spring Boot Tutorials](https://www.baeldung.com/spring-boot)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)

## License

This is an educational project demonstrating Spring Boot fundamentals.
