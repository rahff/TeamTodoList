package org.security.ports.dto;

public record SignupUserRequest(String email, String name, String password, String id, String accountId) {
}