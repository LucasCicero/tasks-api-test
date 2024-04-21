package br.ce.wcaquino.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;



public class ApiTest {



    @Test
    public void deveRetornarTarefas(){
        RestAssured.given()
                    .log().all()
                .when()
                    .get("http://localhost:8001/tasks-backend/todo")
                .then()
                    .statusCode(200);
        ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        RestAssured.given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2025-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                    .post("http://localhost:8001/tasks-backend/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"))
        ;

    }

    @Test
    public void naoDeveAdicionarTarefaComSucesso(){
        RestAssured.given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:8001/tasks-backend/todo")
                .then()
                .statusCode(201);
        ;

    }
}
