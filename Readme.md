![image](https://github.com/user-attachments/assets/91df032e-044e-410d-bd4f-fd3772efc6d3)


# Mid-Term Project

This project follows the tutorial from the video: [YouTube Video](https://www.youtube.com/watch?v=WyOeWlnNj8Y).

## Project Structure

The project has the following directory and file structure:

```
src/
 └── main/
     ├── java/
     │   └── vn/
     │       └── kaiz/
     │           └── midterm/
     │               ├── controllers/
     │               │   ├── ProductController.java
     │               │   └── ...
     │               ├── models/
     │               │   ├── Product.java
     │               │   └── ProductDto.java
     │               ├── repositories/
     │               │   └── ProductRepository.java
     │               ├── services/
     │               │   ├── ProductService.java
     │               │   └── ...
     │               ├── MidTermApplication.java
     │               └── ...
     ├── resources/
     │   ├── application.properties
     │   ├── static/
     │   │   └── images/
     │   └── templates/
     │       ├── products/
     │       │   ├── productList.html
     │       │   ├── createProduct.html
     │       │   └── viewProduct.html
     │       └── ...
     └── ...
```

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/tentoilatai/JVSP_Mid-Term.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd JVSP_Mid-Term
   ```

3. **Install Dependencies**

   This project uses Maven, so install the dependencies with:

   ```bash
   mvn install
   ```

4. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

## Additional Information

- **Controllers:** Contains the application controller classes, such as `ProductController.java`.
- **Models:** Contains the data model classes, such as `Product.java` and `ProductDto.java`.
- **Repositories:** Contains the data access classes, such as `ProductRepository.java`.
- **Services:** Contains the business logic service classes, such as `ProductService.java`.
- **Templates:** Contains HTML template files for the user interface.

## Contact

If you have any questions, please contact via email or create an issue on this repository.
