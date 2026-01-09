# 💰 Payroll Management System

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Servlet](https://img.shields.io/badge/Servlet-API-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Live Demo](https://img.shields.io/badge/Live-Demo-brightgreen?style=for-the-badge&logo=render&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

> A robust and efficient web-based application designed to streamline employee payroll processing, user management, and automated payslip generation.

**🌐 Live Demo:** [https://payroll-h8vh.onrender.com/](https://payroll-h8vh.onrender.com/)

---

## 📖 About The Project

The **Payroll Management System** is a full-stack Java Servlet application that automates the administrative tasks of managing employee salaries. It features a secure role-based access control system (Admin, Manager, Employee), allowing administrators to manage user records while employees can view and download their monthly payslips.

Built with a focus on performance and simplicity, this project leverages core Java Enterprise technologies, making it an excellent reference for Servlet-based web architecture.

---

## ✨ Key Features

### 🛡️ Administrator Module
*   **User Management:** Add, update, and remove employee records.
*   **Dashboard:** Comprehensive view of all registered employees.
*   **Salary Configuration:** Define base salary, HRA, and DA components.

### 💼 Employee Module
*   **Secure Login:** Individual accounts for every employee.
*   **Payslip Viewing:** Real-time generation of monthly salary details.
*   **PDF Export:** Download official payslips in PDF format.
*   **Dashboard:** Personal overview of employment details.

### ⚙️ Technical Highlights
*   **Role-Based Access Control (RBAC):** Secure authorization flow.
*   **Database Integration:** Efficient MySQL connectivity using JDBC.
*   **Responsive UI:** visually appealing interface with modern CSS styling.
*   **Containerized:** Docker support for easy deployment.

---

## 🛠️ Tech Stack

| Category | Technologies |
| :--- | :--- |
| **Backend** | Java 17, Java Servlets, JDBC |
| **Frontend** | HTML5, CSS3, JavaScript, Gson |
| **Database** | MySQL 8.0 |
| **Build Tool** | Apache Maven |
| **Containerization** | Docker |
| **PDF Generation** | iTextPDF |

---

## 🚀 Getting Started

Follow these instructions to set up the project locally.

### Prerequisites
*   Java Development Kit (JDK) 17 or higher
*   Apache Maven
*   MySQL Server
*   Docker (Optional, for containerized run)

### 📥 Installation & Setup

1.  **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/PayRollManagement.git
    cd PayRollManagement
    ```

2.  **Configure Database**
    This application requires a MySQL database. You can use a local instance or a cloud provider (e.g., Aiven).
    
    *Create the database schema (if not auto-generated):*
    ```sql
    CREATE DATABASE defaultdb; -- Or PayRollManagement
    -- Ensure required tables (Employee, etc.) are created
    ```

3.  **Environment Variables**
    Configure the application by setting the following environment variables.

    | Variable | Description | Example |
    | :--- | :--- | :--- |
    | `DB_URL` | JDBC Connection String | `jdbc:mysql://localhost:3306/defaultdb` |
    | `DB_USER` | Database Username | `root` |
    | `DB_PASSWORD` | Database Password | `password` |

### 🏃‍♂️ Running the Application

#### Option A: Using Maven & Tomcat (Local)
1.  Build the project:
    ```bash
    mvn clean package
    ```
2.  Deploy the generated `target/PayRollManagement.war` to your local Tomcat server or run with a Maven plugin if configured.

#### Option B: Using Docker (Recommended)
1.  **Build the Docker image:**
    ```bash
    docker build -t payroll-app .
    ```

2.  **Run the container:**
    *(Replace values with your actual database credentials)*
    ```bash
    docker run -d -p 8080:8080 \
      -e DB_URL="jdbc:mysql://host.docker.internal:3306/defaultdb" \
      -e DB_USER="root" \
      -e DB_PASSWORD="password" \
      --name payroll-container payroll-app
    ```

3.  **Access the application:**
    Open your browser and navigate to `http://localhost:8080`

---

## 📂 Project Structure

```
PayRollManagement/
├── src/main/java/          # Java Source Code
│   ├── config/             # DB Connection & Listeners
│   ├── controller/         # Servlets (Controllers)
│   ├── dao/                # Data Access Objects
│   └── model/              # POJO Classes
├── src/main/webapp/        # Frontend (HTML, CSS, JSP)
├── Dockerfile              # Docker build configuration
├── pom.xml                 # Maven dependencies
└── README.md               # Project documentation
```

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

<p align="center">
  Made with ❤️ by the Development Team
</p>