import { InMemoryEmailPasswordAuthentication } from "src/app/authentication/services/in-memory-email-password-authentication.service";
import { AuthenticationGateway } from "../spi/AuthenticationGateway";
import { AuthenticationByToken } from "./AuthenticationByToken"
import { UserContextHolder } from "../../shared/interfaces/UserContextHolder";

import { Result } from "../../shared/dto/Result";
import { Authentication } from "../dto/Authentication";
import { fakeAuthentication } from "../data/authentication.data";

describe("AuthenticationByToken", () => {
    let authentication: AuthenticationByToken;
    let authenticationGateway: AuthenticationGateway;
    let userContextHolder: jasmine.SpyObj<UserContextHolder>

    beforeEach(() => {
        userContextHolder = jasmine.createSpyObj("UserContextHolder", ["getToken"]);
        authenticationGateway = new InMemoryEmailPasswordAuthentication();
        authentication = new AuthenticationByToken(authenticationGateway, userContextHolder);
    })

    it("try to authenticate when there no token at local storage", () => {
        userContextHolder.getToken.and.returnValue(null);
        authentication.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("no token")
        });
    });

    it("try to authenticate with a invalid token", () => {
        userContextHolder.getToken.and.returnValue({accessToken: "invalid token", refreshToken: "refreshToken"});
        authentication.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("invalid token")
        })
    })

    it("try to authenticate with a expires token", () => {
        userContextHolder.getToken.and.returnValue({accessToken: "expires token", refreshToken: "refreshToken"});
        authentication.authenticate().subscribe((result: Result<Authentication, Error>) => {
            expect(result.isOk()).toBeTrue();
            expect(result.getValue()).toEqual(fakeAuthentication);
        })
    })
})