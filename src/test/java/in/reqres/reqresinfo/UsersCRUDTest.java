package in.reqres.reqresinfo;

import in.reqres.model.ReqresPojo;
import in.reqres.testbase.TestBase;
import in.reqres.utilis.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCRUDTest extends TestBase {

    static String name = "Amy" + TestUtils.getRandomValue();
    static String job = "Tester" + TestUtils.getRandomValue();
    static String email = "amysmith@gmail.com";
    static String password = "smith123";

    static String usersId;

    @Steps
    UsersSteps usersSteps;

@Title("This will create users by name:{0}, job: {1}")
@Test
public void test001(){
    ValidatableResponse response = usersSteps.createAllUsers(name,job);
    response.log().all().statusCode(201);
    usersId = response.log().all().extract().path("id");
    System.out.println(usersId);
}
@Title("Verify new users information add")
 @Test
    public void test002() {
    HashMap<String, ?> userMap = usersSteps.getProductInfoByName(usersId);
    Assert.assertThat(userMap, hasValue(name));
    System.out.println(usersId);
}
@Title("This is for users to login ")
 @Test
 public void test003() {
    HashMap<String, ?> tokenMap = usersSteps.loginUser(email, password);
    Assert.assertThat(tokenMap, hasKey("token"));
    System.out.println(tokenMap);

}
 @Title("This will update a user by PUT")
 @Test
    public void test004() {
    name = name + "_updatedPut";
     ValidatableResponse response = usersSteps.usersUpdateByPut(usersId, name,job);
     response.log().all().statusCode(200);

 }
@Title("This will update a user by Patch")
 @Test
    public void test005() {
        name = name + "_updatedPatch";
        ValidatableResponse response = usersSteps.usersUpdateByPatch(usersId, name, job);
        response.log().all().statusCode(200);
    }
    @Title("This will delete a use")
    @Test
    public void test006() {

        ValidatableResponse response = usersSteps.deleteUsersId(usersId);
        response.log().all().statusCode(204);
    }
    @Title("1. This will verify if Page = 2")
    @Test
    public void test007() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        int page = response.log().all().extract().path("page");
        Assert.assertEquals(2, page);
    }

    @Title("2. This will if verify Per_page = 6")
    @Test
    public void test008() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        int per_page = response.log().all().extract().path("per_page");
        Assert.assertEquals(6, per_page);
    }

    @Title("3. This will if verify second data id = 8")
    @Test
    public void test009() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        int data = response.log().all().extract().path("data[1].id");
        Assert.assertEquals(8, data);
    }

    @Title("4. This will if verify forth data first_name = Byron")
    @Test
    public void test010() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        String firstname = response.log().all().extract().path("data[3].first_name");
        Assert.assertEquals("Byron", firstname);
    }

    @Title("5. This will if verify list of data = 6")
    @Test
    public void test011() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        List<?> listOfData= response.log().all().extract().path("data");
        Assert.assertEquals(6, listOfData.size());
    }

    @Title("6. This will if verify fifth data avatar=https://reqres.in/img/faces/12-image.jpg")
    @Test
    public void test012() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        String imageUrl = response.log().all().extract().path("data[5].avatar");
        Assert.assertEquals("https://reqres.in/img/faces/12-image.jpg", imageUrl);
    }

    @Title("7. This will if verify support.url =https://reqres.in/#support-heading")
    @Test
    public void test013() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        String supportHeading = response.log().all().extract().path("support.url");
        Assert.assertEquals("https://reqres.in/#support-heading", supportHeading);
    }

    @Title("8. This will if verify support.text = To keep ReqRes free, contributions towards server costs are appreciated!")
    @Test
    public void test014() {
        ValidatableResponse response = usersSteps.creatingAllPageTwoUsers();
        String supportText = response.log().all().extract().path("support.text");
        Assert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", supportText);
    }


}
