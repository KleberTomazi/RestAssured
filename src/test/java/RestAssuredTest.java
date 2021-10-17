import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RestAssuredTest {

    @BeforeAll
    public static void setBaseURI()
    {
        RestAssured.baseURI = "https://616c320737f997001745d589.mockapi.io/";
    }

    @Test
    public void testGetNameSuccess()
    {
        get("/student/1").then().statusCode(200);
        String studentName = get("/student/1").then().assertThat().statusCode(200).extract().path("name");
        assertEquals("name 1", studentName);
    }

    @Test
    public void testFailure()
    {
        get("/student/-1").then().statusCode(404);
        // Failure test
    }

    @Test
    public void TestFailureBreaking()
    {
        get("/student/-1").then().statusCode(400);
        // This test is purposely breaking!!
        // Failure test failing
    }
}
