# 📚 Online Bookstore API

## 📝 Project Description
This is a RESTful API for an **online bookstore**. It allows users to:
- 📦 **Create orders** for books in stock.
- 📄 **Retrieve existing orders**.
- 📊 **Check stock availability** before processing an order.
- 🔄 **Update stock asynchronously** after a successful order.

This project is built using **Java 17** and **Spring Boot**, following clean architecture principles.

---

## 🚀 Features
- ✅ **Create an order** (Validates stock before processing).
- ✅ **Reject an order if any book is out of stock**.
- ✅ **Retrieve all orders**.
- ✅ **Stock updates asynchronously** (does not block API responses).
- ✅ **Database persistence using Spring Data JPA**.
- ✅ **Unit tests with JUnit**.

---

## 🏗️ Project Structure
The project follows a modular structure for better organization and maintainability.
```
📁 src/main/java/com/adobe/bookstore/ 

│── 📁 controller/ # REST API endpoints 
│── 📁 dto/ # Data Transfer Objects (DTOs) 
│── 📁 entity/ # JPA entities (database mapping) 
│── 📁 repository/ # Data access layer (Spring Data JPA) 
│── 📁 service/ # Business logic
│── 📄 BookstoreApplication.java # Main Spring Boot class
```

## 📡 API Endpoints

### 📌 **1. Check Available Books**
- **Endpoint:** `GET /books_stock/{bookid}`
- **Description:** Returns the currently stock of a book.
- **Response Example:**
```json
    {
  "id": "12494472-c905-4ac0-a133-5ebb3b4751e4",
  "name": "excepteur eiusmod cupidatat in amet",
  "quantity": 0
  }
```
### 📌 2. Create a New Order
**Endpoint:** `POST /orders`  
**Description:** Creates an order if all books have enough stock.

#### **Request Body Example**
```json
{
    "items": [
        { "bookId": "book1", "quantity": 2 },
        { "bookId": "book2", "quantity": 1 }
    ]
}
```

#### **Successful Response Example**
```json
{ "orderId": 1 }
```

#### **Error Response (Not enough stock)**
```json
{ "error": "Not enough stock for one or more books." }
```

---

### 📌 3. Retrieve Existing Orders
**Endpoint:** `GET /orders/`  
**Description:** Returns all orders that have been placed.

#### **Response Example**
```json
[
    {
        "id": 1,
        "items": [
            { "bookId": "book1", "quantity": 2 },
            { "bookId": "book2", "quantity": 1 }
        ],
        "status": "SUCCESS"
    }
]
```

---

## ⚙️ Setup and Running the Project

### **1️⃣ Clone the repository**
```sh
git clone https://github.com/jurad0/bookstore-api.git
cd bookstore-api
```

### **2️⃣ Build the project**
```sh
mvn clean install
```

### **3️⃣ Run the application**
```sh
mvn spring-boot:run
```
The API will be available at **http://localhost:8080**.

---

## 🧪 Running Tests
Execute unit tests using:
```sh
mvn test
```


