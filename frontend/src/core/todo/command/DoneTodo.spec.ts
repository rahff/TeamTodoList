import { Result } from "src/core/shared/dto/Result";
import { InMemoryTodoCommandHandler } from "../in-memory/InMemoryTodoCommandHandler";
import { DoneTodo } from "./DoneTodo";

describe("DoneTodo", () => {
    let doneTodo: DoneTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        doneTodo = new DoneTodo(dataSource);
    })

    it(" A teammate done a todo", () => {
        const doneTodoId = "todoId";
        doneTodo.execute(doneTodoId).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("doneTodo")).toEqual(doneTodoId);
        })
    })

    it(" A teammate done a todo but it fails", () => {
        const doneTodoId = "failure";
        doneTodo.execute(doneTodoId).subscribe((result: Result) => {
            expect(result.isError()).toEqual("bad request");
        })
    })
})