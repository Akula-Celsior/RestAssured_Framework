
**REST Assured API automation framework** with a clean modular structure designed for testing RESTful web services.

---
### **Project Structure**

![image](https://github.com/user-attachments/assets/8dd5e4b5-2a01-43cc-9c35-7dd9558fc341)

### 🔧 **Development Phase Components**

1. **Endpoints**

   * **UserEndPoints, StoreEndPoints, PetEndPoints**
   * Contains the RESTful URLs for different modules like user, store, and pet.
   * Central place to manage endpoint paths.

2. **Test Cases**

   * Modules: `UserTests`, `StoreTests`, `PetTests`
   * Contains all test classes that validate the APIs.
   * These test cases call the corresponding endpoints and validate responses.

3. **Properties**

   * `routes.properties`
   * Used to manage dynamic configurations like base URLs and endpoint paths.
   * Keeps the codebase flexible and easy to maintain.

4. **Payloads (POJO - Plain Old Java Objects)**

   * Java classes (e.g., User, Store, Pet) that define request/response structures.
   * Used with `ObjectMapper` for serialization/deserialization between JSON and Java objects.

5. **Utilities**

   * Includes common utilities like:

     * **DataProviders**: For parameterized testing using TestNG.
     * **ExtentReport Utility**: For generating rich test reports.
     * **Excel Utility (XL Utility)**: To read/write Excel data used in tests.

6. **Test Data**

   * External data files:

     * `UserData.xlsx`, `StoreData.xlsx`, `PetData.xlsx`
   * Used for data-driven testing.

---

### ▶️ **Execution Phase Components**

1. **TestNG.xml**

   * Acts as the test suite file.
   * Specifies which tests to run, in what order, and with what configurations.
   * Also supports grouping, parallel execution, etc.

2. **pom.xml**

   * Maven configuration file.
   * Manages dependencies like:

     * REST Assured
     * TestNG
     * ExtentReports
     * JSON/XML parsers
   * Also includes build plugins for report generation and integration.

3. **Reports**

   * Generated after test execution:

     * **TestNG Reports**: Default reports provided by TestNG.
     * **Extent Reports**: Rich HTML reports with logs, screenshots, and statuses.

---

### 🔁 **CI/CD Integration**

1. **Git + GitHub**

   * Source control and version management of the framework.
   * GitHub used to collaborate and store code.

2. **Jenkins**

   * Continuous Integration server.
   * Automatically pulls code from GitHub and runs tests.
   * Can trigger test runs on code changes, nightly builds, etc.
   * Displays reports and test results post-execution.

---

### ✅ Summary

This **REST Assured framework** is well-structured and supports:

* Modular code organization
* Data-driven and parameterized testing
* Extensible reporting
* CI/CD integration with Jenkins
* Scalable and maintainable API automation practices
