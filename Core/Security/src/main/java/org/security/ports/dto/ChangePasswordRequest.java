package org.security.ports.dto;

public record ChangePasswordRequest(String email, String newPassword) {
}
