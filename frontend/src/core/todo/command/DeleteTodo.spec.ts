import { Result } from "src/core/shared/dto/Result";
import { InMemoryTodoCommandHandler } from "../in-memory/InMemoryTodoCommandHandler";
import { DeleteTodo } from "./DeleteTodo";


describe("DeleteTodo", () => {
    let deleteTodo: DeleteTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        deleteTodo = new DeleteTodo(dataSource);
    })

    it("A manager delete a todo", () => {
        const deletedTodoId = "todoId";
        deleteTodo.execute(deletedTodoId).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("deleteTodo")).toEqual(deletedTodoId)
        })
    });

    it("A manager delete a todo but it fails", () => {
        const deletedTodoId = "failure";
        deleteTodo.execute(deletedTodoId).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request")
        })
    })
})