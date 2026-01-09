# PayRollManagement Deployment Guide

This project has been configured for deployment on cloud platforms like Render (application hosting) and Aiven (database hosting).

## Prerequisites

- A [Render](https://render.com/) account.
- An [Aiven](https://aiven.io/) account (or any MySQL provider).
- GitHub/GitLab repository with this code pushed.

## 1. Database Setup (Aiven)

1.  Create a new **MySQL** service on Aiven.
2.  Once running, note down the **Service URI** or the individual connection details:
    - Host
    - Port
    - Username (usually `avnadmin` or similar)
    - Password
    - Database Name (usually `defaultdb`, you can create a new one named `PayRollManagement` or just use the default).

## 2. Application Setup (Render)

1.  Create a new **Web Service** on Render.
2.  Connect your repository.
3.  Select **Docker** as the Runtime.
4.  **Environment Variables**: You MUST set these for the application to connect to your database.
    - `DB_URL`: The JDBC URL.
      - Format: `jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>?sslMode=REQUIRED`
      - Example: `jdbc:mysql://mysql-service-do-user-123.aivencloud.com:23528/defaultdb?sslMode=REQUIRED`
    - `DB_USER`: Your database username.
    - `DB_PASSWORD`: Your database password.

    *Note: If your database requires SSL (common for cloud providers), ensure `?sslMode=REQUIRED` (or `VERIFY_CA` etc.) is in the connection string.*

## 3. Deployment

- Click **Create Web Service**.
- Render will build the Docker image (using Maven) and deploy it to Tomcat.
- **Auto-Initialization**: When the app starts, it will automatically:
    - Check/Create the `employees` table.
    - Create a default admin user if one doesn't exist.

## 4. Default Login

- **Username**: `admin`
- **Password**: `admin123`

## Local Development

The application falls back to `localhost:3306`, user `root`, password `1111` if environment variables are not set. You can change these defaults in `src/main/java/config/DBConnection.java` or simply set environment variables on your local machine.
