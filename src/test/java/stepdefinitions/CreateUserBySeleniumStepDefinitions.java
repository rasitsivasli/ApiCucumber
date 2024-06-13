package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import pages.CLAddUserPage;
import pages.CLContactsPage;
import pages.CLHomePage;
import utilities.Driver;

public class CreateUserBySeleniumStepDefinitions {
    CLHomePage clHomePage = new CLHomePage();
    CLAddUserPage clAddUserPage = new CLAddUserPage();
    CLContactsPage clContactsPage = new CLContactsPage();
    public static String email;
    public static String password;

    @Given("user goes to {string}")
    public void user_goes_to(String url) {
        Driver.getDriver().get(url);
    }

    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {
        clHomePage.signup.click();

    }

    @When("user enters firstname")
    public void user_enters_firstname() {
        clAddUserPage.firstName.sendKeys("John");
    }

    @When("user enters lastname")
    public void user_enters_lastname() {
        clAddUserPage.lastName.sendKeys("Doe");
    }

    @When("user enters email")
    public void user_enters_email() {
        email = Faker.instance().internet().emailAddress();// Athentication icin kullanilacak email ataniyor
        clAddUserPage.email.sendKeys(email);
    }

    @When("user enters password")
    public void user_enters_password() {
        password = Faker.instance().internet().password();// Athentication icin kullanilacak password ataniyor
        clAddUserPage.password.sendKeys(password);
    }

    @When("user clicks on submit button")
    public void user_clicks_on_submit_button() {
        clAddUserPage.submit.click();
    }

    @Then("validate logout button")
    public void validate_logout_button() {
        assert clContactsPage.logout.isDisplayed();
    }

    @And("close browser")
    public void closeBrowser() {
        Driver.closeDriver();
    }
}
