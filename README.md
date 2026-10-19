# Employee Leave Management System

A Java-based Employee Leave Management System developed as part of the ShadowFox Intermediate Project. The application enables employees to submit leave requests and track their status, while administrators can review and approve or reject pending requests.

## Features

- **Apply for Leave:** Submit leave requests with employee ID, leave type, dates, and reason.
- **Leave Types:** Supports Casual, Sick, Earned, and Other leave categories.
- **Leave History:** View leave requests and their current status.
- **Admin Panel:** Load pending leave requests and approve or reject them.
- **Status Tracking:** Track approved and rejected requests.
- **Database Integration:** Store employee and leave request information in MySQL.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Maven
- HTML, CSS, and JavaScript
- Git and GitHub

## Prerequisites

Install the following before running the application:

- Java 17 or a compatible version supported by the project
- MySQL 8.x
- Git

## Configuration

1. Create a MySQL database named `employee_leave_db`.
2. Configure the database connection using environment variables.

Example configuration:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/employee_leave_db}
spring.datasource.username=${DB_USERNAME:leaveuser}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
```

Set `DB_PASSWORD` to your own MySQL password before running the application. Do not commit real passwords or other credentials to GitHub.

## Run the Application

Clone the repository:

```bash
git clone https://github.com/Safiyah-Shaik/ShadowFox_EmployeeLeaveManagementSystem.git
cd ShadowFox_EmployeeLeaveManagementSystem
```

Set the database password in your terminal, then run:

```bash
./mvnw spring-boot:run
```

Open the application at:

`http://localhost:8080`

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Run Tests

```bash
./mvnw clean package
```

## Project Structure

```text
src/
 ├── main/
 │   ├── java/com/shadowfox/employeeleavemanagement/
 │   │   ├── config/
 │   │   ├── controller/
 │   │   ├── entity/
 │   │   ├── repository/
 │   │   └── service/
 │   └── resources/
 │       ├── application.properties
 │       └── static/index.html
 └── test/
```

## Future Enhancements

- Employee authentication and role-based authorization
- Email notifications for leave status updates
- Leave balance management
- Improved form validation and reporting

## Author

**Safiyah Shaik**

GitHub: [Safiyah-Shaik](https://github.com/Safiyah-Shaik)

## Acknowledgment

Developed as part of the ShadowFox Intermediate Project.

