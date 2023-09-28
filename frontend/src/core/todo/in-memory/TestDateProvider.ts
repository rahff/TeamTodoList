import { DateProvider } from "src/core/shared/interfaces/DateProvider";


export class TestDateProvider implements DateProvider {
    now(): Date {
       return new Date(2022, 10, 24);
    }

}