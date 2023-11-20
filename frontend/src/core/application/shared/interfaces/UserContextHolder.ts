import { Authentication, TokenJwtPair } from "../../security/dto/Authentication"

export interface UserContextHolder {
    getAccountId(): string | null;
    getUserId(): string | null;
    saveAuthentication(authentication: Authentication): void;
    getToken(): TokenJwtPair | null
}