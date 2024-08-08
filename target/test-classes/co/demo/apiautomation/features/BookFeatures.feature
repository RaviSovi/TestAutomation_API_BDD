@rahulShettyBookAPIValidation
Feature: Book Featues validations - Add, View, Delete Book.

  @addBookUsingExcel
  Scenario Outline: Verify User is able to add new book successfully
    Given User have a valid endpoints "app1.addBook.endPoint"
    When  User add new book using test data row "<dataKey>" from Excel
    Then  User should get a response code as 200
    And   User validates the add book api json reponse properties from Excel "<dataKey>"
    And   User validates the response with Json Schema from Excel
    
    Examples: 
      | dataKey |
      | addBook |

  @getBookDetailsUsingExcel
  Scenario Outline: Verify User is able to view existing book details successfully
    Given User have a valid endpoints "app1.getBook.endPoint"
    When  User view existing book details using test data row "<dataKey>" from Excel
    Then  User should get a response code as 200
    #And   User validates the view book api json reponse properties from Excel "<dataKey>"
    And   User validates the response with Json Schema from Excel
    
    Examples: 
      | dataKey |
      | viewBookDetails |
      
  @addBookUsingJson
  Scenario Outline: Verify User is able to add new book successfully Using Json
    Given User have a valid endpoints "app1.addBook.endPoint"
    When  User add a book using request body data "addBook_TC01" from JSON file "requestBody.json"
    Then  User should get a response code as 200
    And   User validate "responseBody" data "addBook_TC01" from JSON file "expectedresponseBody.json"
    And   user validates the response with JSON schema "addBookSchema.json"
    
    Examples: 
      | dataKey |
      | addBook |
      
  @getBookDetailsUsingJson
  Scenario Outline: Verify User is able to view existing book successfully Using Json
    Given User have a valid endpoints "app1.getBook.endPoint"
    When  User add a book using request body data "getBook_TC01" from JSON file "requestBody.json"
    Then  User should get a response code as 200
    And   User validate "responseBody" data "getBook_TC01" from JSON file "expectedresponseBody.json"
    And   user validates the response with JSON schema "getBookSchema.json"
    
    Examples: 
      | dataKey |
      | addBook |
      
  @deleteBookUsingJson
  Scenario Outline: Verify User is able to delete existing book successfully Using Json
    Given User have a valid endpoints "app1.deleteBook.endPoint"
    When  User add a book using request body data "deleteBook_TC01" from JSON file "requestBody.json"
    Then  User should get a response code as 404
    And   User validate "responseBody" data "deleteBook_TC01" from JSON file "expectedresponseBody.json"
    And   user validates the response with JSON schema "deleteBookSchema.json"
    
    Examples: 
      | dataKey |
      | addBook |
