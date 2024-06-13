package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.ContactPojo;
import utilities.ObjectMapperUtils;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ContactStepDefinitions {
    Response response;
    static ContactPojo expectedData;// Farkli feature dosyalarindan kullnilmasi gereken variable lar static belirtilir.
    static String contactId;// static variable ler tüm proje icin ortak olduklari icin tüm proje de kullanilir

    @Given("set the url for add user")
    public void setTheUrlForAddUser() {
        //"https://thinking-tester-contact-list.herokuapp.com/"
        spec.pathParams("first", "contacts");
    }

    @And("set the expected data for add user")
    public void setTheExpectedDataForAddUser() {
        String json = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "New York",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = ObjectMapperUtils.jsonToJava(json, ContactPojo.class);
        expectedData.setFirstName(Faker.instance().name().firstName());
        expectedData.setLastName(Faker.instance().name().lastName());
        expectedData.setEmail(Faker.instance().internet().emailAddress());

        System.out.println("expectedData = " + expectedData);
    }

    @When("send the post request and get the response")
    public void sendThePostRequestAndGetTheResponse() {
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
    }

    @Then("do assertion for add user")
    public void doAssertionForAddUser() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getCountry(), actualData.getCountry());
        assertEquals(expectedData.getBirthdate(), actualData.getBirthdate());
        assertEquals(expectedData.getPhone(), actualData.getPhone());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getPostalCode(), actualData.getPostalCode());
        assertEquals(expectedData.getStateProvince(), actualData.getStateProvince());
        assertEquals(expectedData.getStreet1(), actualData.getStreet1());
        assertEquals(expectedData.getStreet2(), actualData.getStreet2());
        assertEquals(expectedData.getEmail(), actualData.getEmail());

        contactId = response.jsonPath().getString("_id");

    }

    @Given("set the url for get contact")
    public void setTheUrlForGetContact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts/contactId
        spec.pathParams("first", "contacts", "second", contactId);

    }

    @And("set the expected data for get contact")
    public void setTheExpectedDataForGetContact() {
        System.out.println("expectedData = " + expectedData);
    }

    @When("send the get request and get the response for get contact")
    public void sendTheGetRequestAndGetTheResponseForGetContact() {
        response = given(spec)
                .get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for get contact")
    public void doAssertionForGetContact() {
    }
}
