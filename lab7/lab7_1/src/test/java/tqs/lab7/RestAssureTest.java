package tqs.lab7;

import org.junit.Test;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class RestAssureTest {

    @Test
    public void getAllTodos() {
        when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200);
    }

    @Test
    public void getTodo4() {
        when().get("https://jsonplaceholder.typicode.com/todos/4")
                .then().body("id", is(4))
                .and().body("title", is("et porro tempora"));
    }

    @Test
    public void whenAllTodos_198And199ShouldBeOnIt() {
        when().get("https://jsonplaceholder.typicode.com/todos")
                .then().body("id", hasItems(198, 199));
    }
}
