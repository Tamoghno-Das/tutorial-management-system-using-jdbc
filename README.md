# Tutorial Management System (Using JDBC)

A simple **Java JDBC-based application** for managing tutorials with CRUD operations.  
This project demonstrates connecting to a relational database using **JDBC**, performing SQL operations, and organizing the application with **DAO, Service, and Entity layers**.

---

## 📑 Table of Contents

1. [Features](#features)  
2. [Tech Stack](#tech-stack)  
3. [Prerequisites](#prerequisites)  
4. [Setup & Installation](#setup--installation)  
5. [Usage](#usage)  
6. [Project Structure](#project-structure)  
7. [How It Works](#how-it-works)  
8. [Contributing](#contributing)  
9. [License](#license)  
10. [Contact](#contact)  

---

## 🚀 Features

- Create, Read, Update, Delete (CRUD) tutorials  
- JDBC-based database connectivity  
- Exception handling for database operations  
- Well-structured code with **DAO, Service, and Entity** layers  
- Easy to extend for new features  

---

## 🛠 Tech Stack

- **Language:** Java (JDK 8+)  
- **Database:** MySQL (or any JDBC-compatible database)  
- **Build Tool:** Maven  
- **Database Driver:** MySQL Connector/J  

---

## 📦 Prerequisites

Before running the project, ensure you have:

- Java JDK 8 or above installed  
- Apache Maven installed  
- A running relational database (e.g. MySQL)  
- Basic knowledge of SQL  

---

## ⚙️ Setup & Installation

Follow these steps to set up and run the project locally:

### 1. Clone the repository
<pre>
<code>
git clone https://github.com/Tamoghno-Das/tutorial-management-system-using-jdbc.git
cd tutorial-management-system-using-jdbc
</pre>
</code>

---


## 2. Configure your database

Create a database, e.g. tutorial_db

### Create the tutorials table:

```sql
CREATE TABLE tutorials (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  published BOOLEAN DEFAULT FALSE
);
```

---

### Insert some sample data (optional):

```sql
INSERT INTO tutorials (title, description, published) VALUES
('Intro to JDBC', 'Learn basics of JDBC in Java', true),
('Spring Boot Setup', 'Step-by-step guide for Spring Boot setup', false);
```

---

## 3. Update database configuration in code

#### In your database connection class, set:

##### IN my case : 
*URL :*  "jdbc:oracle:thin:@//localhost:1521/ORCL" <br>
*USER :* c##scott <br>
*PASSWORD :* tiger <br>

```java
String jdbcUrl = "your_database_url";
String username = "your SID/SERVICE";
String password = "your_password";
```

---


## 4. Build the project

```bash
mvn clean compile
```

---

## 5. Run the application

```bash
mvn exec:java -Dexec.mainClass="com.example.main.Main"
```

---

## ▶️ Usage

### Once the application is running, you can perform:

1. Add a Tutorial → Insert a new tutorial

2. View Tutorials → Display all tutorials

3. Search by ID → Fetch tutorial details

4. Update a Tutorial → Modify existing tutorial

5. Delete a Tutorial → Remove tutorial by ID

6. Filter Published/Unpublished tutorials

---

### Example Console Interaction:

```pgsql
===== Tutorial Management Menu =====
1. Add Tutorial
2. View All Tutorials
3. Search Tutorial by ID
4. Update Tutorial
5. Delete Tutorial
6. Exit
```

---

## 📂 Project Structure

```bash
tutorial-management-system-using-jdbc/
├── src/
│   └── main/java/com/example/
│       ├── entity/         # Tutorial entity class
│       ├── dao/            # DAO interfaces and implementations
│       ├── service/        # Service layer (business logic)
│       ├── exception/      # Custom exceptions
│       └── main/           # Main entry point
├── pom.xml
└── README.md
```

---


## 🔄 How It Works

1. *Main.java* → Application entry point, shows menu & takes user input

2. *Service Layer* → Handles business logic and calls DAO methods

```java
// Example: calling service from main
TutorialService service = new TutorialServiceImpl();
service.addTutorial(new Tutorial("Intro to JDBC", "Learn JDBC basics", true));
```

---

3. *DAO Layer* → Executes SQL queries using JDBC

```java
// Example DAO insert
String sql = "INSERT INTO tutorials (title, description, published) VALUES (?, ?, ?)";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, tutorial.getTitle());
ps.setString(2, tutorial.getDescription());
ps.setBoolean(3, tutorial.isPublished());
ps.executeUpdate();
```

---

4. *Entity (Tutorial)* → Represents tutorial data (id, title, description, published)

5. *Results* are shown in the console

---

## 🤝 Contributing

Contributions are always welcome!

### Steps:

1. [x] Fork the repo

2. [x] Create a branch **`(git checkout -b feature/YourFeature)`**

3. [x] Commit your changes **`(git commit -m "Add feature")`**

4. [x] Push to your branch **`(git push origin feature/YourFeature)`**

5. [x] Open a Pull Request

---

## 📜 License

This project is licensed under the MIT License. See the LICENSE
file for details.

---

### 📧 Contact
- Author: Tamoghno Das
- Email: (add your email here if you’d like)


