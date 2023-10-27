import { InMemoryEmailPasswordAuthentication } from "src/app/authentication/services/in-memory-email-password-authentication.service";
import { Result } from "../../shared/dto/Result";
import { fakeAuthentication } from "../data/authentication.data";
import { Authentication } from "../dto/Authentication";
import { AuthenticationGateway } from "../spi/AuthenticationGateway";
import { SignupUser } from "./SignupUser";
import { FakeUserContextHolder } from "src/app/dashboard/services/inMemory/FakeUserContextHolder";

describe("LoginCommand", () => {
    let signup: SignupUser;
    let authenticationGateway: AuthenticationGateway;
    let userContextHolder: FakeUserContextHolder;
    beforeEach(() => {
        userContextHolder = new FakeUserContextHolder();
        authenticationGateway = new InMemoryEmailPasswordAuthentication()
        signup = new SignupUser(authenticationGateway, userContextHolder);
    })

    it("User signup with email and password succes", () => {
        signup.execute({name: "rahff", email: "validemail@gmail.com", password: "correctPassword"})
        .subscribe((result: Result<Authentication>) =>{
            expect(result.isOk()).toBeTrue();
            expect(result.getValue()).toEqual(fakeAuthentication);
            expect(userContextHolder.haveBeenSaved("authentication", fakeAuthentication)).toBeTrue();
        })
    });

    it("User signup with email and password fails", () => {
        signup.execute({name: "rahff", email: "alreadyexist@gmail.com", password: "correctPassword"})
        .subscribe((result: Result<Authentication>) =>{
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toEqual("email already exists");
            expect(userContextHolder.haveBeenSaved("authentication", fakeAuthentication)).toBeFalse();
        })
    })
})