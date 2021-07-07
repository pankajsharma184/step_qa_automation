Feature: Product Attributes Integration Test





    @testOne
    Scenario: Verify the product name
        When User hits get endpoint "https://step-dev.johnsonoutdoors.com/restapi/products/565906_P"
        Then Product name in response should be "150' CABLE (SUB)"


    @testWithExcel
    Scenario: Verify the product name
        When excel reader completed the job
        Then User hits the get endpoint "https://step-dev.johnsonoutdoors.com/restapi/products/<skuId>" for all the value in excel and validate the results

    @testAll
    Scenario Outline: Verify the product name

        When User hits get endpoint "https://step-dev.johnsonoutdoors.com/restapi/products/<Product_id>"
        Then Product name in response should be "<Product_name>"


        Examples:
            |Product_id  |Product_name  |
            |565906_P  |150' CABLE (SUB)   |
            |565908_P  |400' CABLE    (SUB)   |
            |565924_P  |CANNON TRANSDUCER   |
            |1037383_P  |DOWNRIGGER COVER, STD, BLACK      |







