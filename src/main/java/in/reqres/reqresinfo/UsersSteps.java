package in.reqres.reqresinfo;

import gherkin.lexer.En;
import in.reqres.constants.EndPoints;
import in.reqres.model.ReqresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import javax.xml.stream.events.DTD;
import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.then;

public class UsersSteps {

@Step("Creating users by name: {0}, job: {1}")
public ValidatableResponse createAllUsers(String name, String job){
    ReqresPojo reqresPojo = new ReqresPojo();
    reqresPojo.setFirstName(name);
    reqresPojo.setJob(job);

    return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .body(reqresPojo)
            .when()
            .post(EndPoints.CREATE_ALL_USERS)
            .then();
}
@Step("Getting page-2 users information")
    public ValidatableResponse creatingAllPageTwoUsers() {
    return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .queryParam("page", 2)
            .when()
            .get(EndPoints.CREATE_ALL_USERS)
            .then();
}

@Step("Getting users information by Id: {0}")
public HashMap<String, ?> getProductInfoByName(String usersId) {
    HashMap<String, ?> userMap = SerenityRest.given().log().all()
            .when()
            .pathParam("usersId", usersId)
            .get(EndPoints.GET_USERS_ID)
            .then()
            .statusCode(200)
            .extract().path("");

    return userMap;
}

@Step("Login users with email: {0}, password{1}")
    public HashMap<String, ?> loginUser(String email, String password) {

    ReqresPojo reqresPojo = new ReqresPojo();
    reqresPojo.setEmail(email);
    reqresPojo.setPassword(password);
    return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .body(reqresPojo)
            .when()
            .post(EndPoints.USERS_LOGIN)
            .then()
            .statusCode(200)
            .extract()
            .path("");
}

@Step("Update users with name: {0}, job{1}")
    public ValidatableResponse usersUpdateByPatch(String  usersId, String name, String job){
    ReqresPojo reqresPojo = new ReqresPojo();
    reqresPojo.setFirstName(name);
    reqresPojo.setJob(job);

    return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .pathParam("usersId", usersId)
            .body(reqresPojo)
            .when()
            .patch(EndPoints.UPDATE_USERS_BY_ID)
            .then();
}
@Step("Update users with name: {0}, job{1}")
    public ValidatableResponse usersUpdateByPut(String  usersId, String name, String job){
        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setFirstName(name);
        reqresPojo.setJob(job);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("usersId", usersId)
                .body(reqresPojo)
                .when()
                .put(EndPoints.UPDATE_USERS_BY_ID)
                .then();
}
@Step("Deleting users with id: {0}")
    public ValidatableResponse deleteUsersId(String usersId){
    return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .pathParam("usersId",usersId)
            .when()
            .delete(EndPoints.DELETE_BY_ID)
            .then();
}

}
