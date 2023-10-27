import { Authentication, TokenJwtPair } from "../../security/dto/Authentication"

export interface UserContextHolder {
    getAccountId(): string;
    getUserId(): string;
    save(authentication: Authentication): void;
    getToken(): TokenJwtPair | null
}