import { Result } from "src/core/application/shared/dto/Result";
import { InMemoryTodoCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTodoCommandHandler";
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
        deleteTodoList.execute(deletedTodoListId).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("deleteTodoListTodo")).toEqual(deletedTodoListId)
        })
    });

    it("A manager delete a team todo list but it fails", () => {
        const deletedTodoListId = "failure";
        deleteTodoList.execute(deletedTodoListId).subscribe((result: Result<string>) => {
            expect(result.getError().message).toBe("bad request");
        })
    });
})