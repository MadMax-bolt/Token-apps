Feature: Testing My Spring Boot Application APIs

  Background:
    * url 'http://localhost:8181'

  Scenario: Get Public Key Endpoint
    Given path '/publicKey'
    And header Authorization = 'Basic MTIzOjEyMw=='
    When method get
    Then status 200


  Scenario: Sign Token Endpoint
    Given path '/token'
    And request { value: 'basant' }
    And header Authorization = 'Basic MTIzOjEyMw=='
    When method post
    Then status 200
