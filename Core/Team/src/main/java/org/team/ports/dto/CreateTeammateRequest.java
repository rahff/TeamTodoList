package org.team.ports.dto;

public record CreateTeammateRequest(String teammateId, String email, String name, String accountId) {
}
