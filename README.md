# STC Demo - Spring Boot + OracleXE 

This project is a **Spring Boot REST API** integrated with **OracleXE**, fully dockerized for easy setup and deployment.

---

## Features

- Spring Boot REST API (Java 17)
- OracleXE database (via Docker Compose)
- Docker-ready setup for local or cloud deployment
- Easy build and run commands
- Ready for hosting on Render, Railway, or any Docker-compatible platform

---

## Project Structure

```
stc-demo/
│
├── src/                      # Source code (Java/Spring Boot)
├── target/                   # Compiled JARs (after build)
├── Dockerfile                # Docker image build definition
├── docker-compose.yml        # Compose setup for app + DB
├── pom.xml                   # Maven build file
└── README.md                 # This file
```

---

## ⚙️ Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- (Optional) [Maven](https://maven.apache.org/) if building manually

---

## Setup Instructions

### Build the JAR
```bash
mvn clean package -DskipTests
```

### Build Docker Image
```bash
docker build -t stc-demo .
```

### Run with Docker Compose
```bash
docker-compose up
```

This will spin up:
- `stc-demo` (Spring Boot App) at [http://localhost:8080](http://localhost:8080)
- `oracle-xe` (Database) at port `1521`

---

## API Documentation

All requests and responses are in JSON.  
Temporarily hosted here -  base URL: `https://varsity-springboot.onrender.com/api/`

---

### Student APIs

#### **Register Student**
`POST /students/register`
```json
{
  "nationalId": "1234567890",
  "registrationDate": "2025-10-20",
  "firstName": "Ali",
  "lastName": "Khan",
  "birthDate": "2000-05-10",
  "address": "Riyadh, KSA",
  "mobileNumber": "0598765432"
}
```

#### **Get Student Profile by University ID**
`GET /students/profile?universityId=1234567890`

#### **Get Students by Registration Date Range**
`GET /students/profile/date-range?from=2025-01-01&to=2025-12-31`

#### **Register Student Courses**
`POST /students/{universityId}/courses`
```json
["IS230", "CS101", "MA105"]
```

#### **Get Student Course Details**
`GET /students/{universityId}/courses`

---

### Professor APIs

#### **Register Professor**
`POST /professors/register`
```json
{
  "nationalId": "9876543210",
  "firstName": "Ahmed",
  "lastName": "Al-Saud",
  "title": "Associate Professor",
  "birthDate": "1980-03-15",
  "address": "Riyadh, KSA",
  "mobileNumber": "0501234567"
}
```

#### **Get Professor Profile**
`GET /professors/profile?nationalId=9876543210`

---

### Course APIs

#### **Register Course**
`POST /courses/register`
```json
{
  "courseCode": "IS230",
  "courseName": "Information Systems"
}
```

#### **Get All Courses**
`GET /courses`


---

## License

This project is licensed under the **MIT License**.

---

## Author

**Javed Ali**

