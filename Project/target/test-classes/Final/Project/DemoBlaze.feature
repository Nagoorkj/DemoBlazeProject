Feature: To verify Demo_blaze Ecommerce Website on Google browser and Do the basic operations sign up and sign in.

  @tc1
  Scenario: To Launch the DemoBlaze URL On Chrome Browser
    Given launching the homepage

  @tc2
  Scenario: To Launch the DemoBlaze/ Product Store Site and Validate the Homepage.
    Given launching the homepage
    And Verify the site is working properly

  @tc3
  Scenario Outline: : To Verify the DemoBlaze Website and Validate the Sign up funtionality.
    Given launching the homepage
    And User Clicks  on the SignUp Button
    When I pass the "<username>" and "<password>"
    And User should be create the account successfully.
    And User should be landed to the HomePage

    Examples: 
      | username | password |
      | nagoor   | test1234 |

  #now reading from excel sheet based on header value
  @tc4
  Scenario: To Verify the DemoBlaze Website with Login funtionality and Validate the User  Greeting message.
    Given launching the homepage
    And User Clicks  on the Login Button
    When I enter "username" and "password"
    And User should be logged in and greeting message should be displayed
