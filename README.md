# Notification Project

This project is an implementation of a notification system using design patterns. The goal is to provide a flexible and extensible structure for sending notifications through different channels.

## Project Structure

The project is organized to facilitate maintenance and extension.

## Dependencies

The project uses Maven as a dependency manager. Dependencies are listed in the `pom.xml` file.

## How to Run the Project

To run the project, follow the steps below:

1. Navigate to the project directory:
   ```bash
   cd <project_directory>
   ```
2. Compile the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the main class:
   ```bash
   java -cp target/classes com.notifyhub.Main
   ```

## Design Patterns Used

- **Builder**: To build complex objects in a controlled way.
- **Decorator**: To add functionality to objects dynamically.
- **Factory**: To create objects without specifying the exact class of the object to be created.

## Contributions

Contributions are welcome! Feel free to open a pull request or report issues.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
