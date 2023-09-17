package org.example.controllers.api.httpRequestPayloads;

public record SignupUserRequestBody(String email, String name, String password, String id) {
}
