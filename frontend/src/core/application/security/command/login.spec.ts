import { InMemoryEmailPasswordAuthentication } from "src/app/authentication/services/in-memory-email-password-authentication.service";
import { Result } from "../../shared/dto/Result";
import { fakeAuthentication } from "../data/authentication.data";
import { Authentication, EmailPasswordCredentials, SignupUserRequest } from "../dto/Authentication";
import { LoginUser } from "./LoginUser";
import { FakeUserContextHolder } from "src/app/dashboard/services/inMemory/FakeUserContextHolder";
import { EmailPasswordGateway } from "../spi/AuthenticationGateway";



describe("LoginCommand", () => {
    let login: LoginUser;
    let authenticationGateway: EmailPasswordGateway;
    let userContextHolder: FakeUserContextHolder
    beforeEach(() => {
        userContextHolder = new FakeUserContextHolder
        authenticationGateway = new InMemoryEmailPasswordAuthentication()
        login = new LoginUser(authenticationGateway, userContextHolder);
    })

    it("User login with email and password succes", () => {
        login.execute({email: "validemail@gmail.com", password: "correctPassword"})
        .subscribe((result: Result<Authentication>) =>{
            expect(result.isOk()).toBeTrue();
            expect(result.getValue()).toEqual(fakeAuthentication);
            expect(userContextHolder.haveBeenSaved("authentication", fakeAuthentication)).toBeTrue();
        })
    });

    it("User login with email and password fails", () => {
        login.execute({email: "validemail@gmail.com", password: "IncorrectPassword"})
        .subscribe((result: Result<Authentication>) =>{
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("bad credentials");
            expect(userContextHolder.haveBeenSaved("authentication", fakeAuthentication)).toBeFalse();
        })
    })
})