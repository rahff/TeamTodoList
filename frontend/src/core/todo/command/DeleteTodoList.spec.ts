import { Result } from "src/core/shared/dto/Result";
import { InMemoryTodoCommandHandler } from "../in-memory/InMemoryTodoCommandHandler";
import { DeleteTodoList } from "./DeleteTodoList"


describe("DeleteTodoList", () => {
    let deleteTodoList: DeleteTodoList;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        deleteTodoList = new DeleteTodoList(dataSource);
    })

    it("A manager delete a team todo list", () => {
        const deletedTodoListId = "todoListId";
        deleteTodoList.execute(deletedTodoListId).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("deleteTodoListTodo")).toEqual(deletedTodoListId)
        })
    });

    it("A manager delete a team todo list but it fails", () => {
        const deletedTodoListId = "failure";
        deleteTodoList.execute(deletedTodoListId).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request");
        })
    });
})