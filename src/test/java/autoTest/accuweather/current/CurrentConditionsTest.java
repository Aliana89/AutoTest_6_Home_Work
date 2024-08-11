package autoTest.accuweather.current;

import autoTest.accuweather.AbstractAccuweatherTest;
import autoTest.accuweather.currentConditions.CurrentCondition;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CurrentConditionsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("CurrentConditionsTest")
    @Description("GET Current Conditions")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By ID 15")
    void getCurrentConditions() {

        List<CurrentCondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/current conditions/v1/15")
                .then()
                .statusCode(404)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", CurrentCondition.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("https://www.accuweather.com/en/jp/oga-shi/16/current-weather/16?lang=en-us",
                response.get(0).getMobileLink());
    }
}
