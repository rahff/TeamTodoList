import { InMemoryTokenAuthentication } from "src/app/authentication/services/in-memory-email-password-authentication.service";

import { AuthenticationByToken } from "./AuthenticationByToken"
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";

import { Result } from "../../shared/dto/Result";
import { Authentication } from "../dto/Authentication";
import { fakeAuthentication, fakeAuthenticationWithExpiredAccessToken, fakeAuthenticationWithInvalidToken } from "../data/authentication.data";
import { TokenGateway } from "../spi/AuthenticationGateway";



describe("AuthenticationByToken", () => {
    let authenticationByToken: AuthenticationByToken;
    let userContextHolder: jasmine.SpyObj<UserContextHolder>
    const setupSecurityContextHolder = (authentication:Authentication | null) => {
        const tokenAuthenticator = new InMemoryTokenAuthentication()
        tokenAuthenticator.setAuthentication(authentication)
        userContextHolder = jasmine.createSpyObj("UserContextHolder", ["saveAuthentication"]);
        authenticationByToken = new AuthenticationByToken(tokenAuthenticator, userContextHolder);
      }
    beforeEach(() => {
        userContextHolder = jasmine.createSpyObj("UserContextHolder", ["saveAuthentication"]);
    })

    it("try to authenticate when there no token at local storage", () => {
        setupSecurityContextHolder(null);
        authenticationByToken.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("no token")
        });
    });

    it("try to authenticate with a invalid token", () => {
        setupSecurityContextHolder(fakeAuthenticationWithInvalidToken);
        authenticationByToken.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("invalid token")
        })
    })

    it("try to authenticate with a expires token", () => {
        setupSecurityContextHolder(fakeAuthenticationWithExpiredAccessToken);
        authenticationByToken.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeTrue();
            expect(result.getValue()).toEqual(fakeAuthentication);
        })
    })
})