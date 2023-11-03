import { CreateTodoList } from "./CreateTodoList"
import { CreateTodoListFormData } from "../dto/CreateTodoListFormData";
import { TestIDProvider } from "../in-memory/testIdProvider";
import { TestDateProvider } from "../in-memory/TestDateProvider";

import { TodoList } from "../../../model/todo/TodoList";
import { DateProvider } from "../../shared/interfaces/DateProvider";
import { IDProvider } from "../../shared/interfaces/IDProvider";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";
import { Result } from "../../shared/dto/Result";



describe("CreateTodoList", () => {
    let createTodoList: CreateTodoList;
    let todoDataSource: InMemoryTodoCommandHandler;
    let idProvider: IDProvider;
    let dateProvider: DateProvider;
    beforeEach(() => {
        idProvider = new TestIDProvider();
        dateProvider = new TestDateProvider();
        todoDataSource = new InMemoryTodoCommandHandler()
        createTodoList = new CreateTodoList(todoDataSource, idProvider, dateProvider);
    })

 
    it("A manager create a personal todoList", async () => {
        const formData: CreateTodoListFormData = {name: "My todo list", ref: "ownerRef"}
        createTodoList.execute(formData).subscribe((result: Result<TodoList>)=> {
            expect(result.isOk()).toBeTrue();
            expect(todoDataSource.hasBeenCalled("createTodo")).toEqual({name: "My todo list", id: "generatedId", createdAt: new Date(2022, 10, 24), ref: "ownerRef"})
        });
    });

    it("A manager create a personal todoList but it fail", async () => {
        const formData: CreateTodoListFormData = {name: "will fail", ref: "ownerRef"}
        createTodoList.execute(formData).subscribe((result: Result<TodoList>) => {
            expect(result.getError().message).toBe("bad request");
        });
    })

    it("A manager create a team todoList", async () => {
        const formData: CreateTodoListFormData = {name: "todo list for team", ref: "teamRef"}
        createTodoList.execute(formData).subscribe((result: Result<TodoList>)=> {
            expect(result.isOk()).toBeTrue();
            expect(todoDataSource.hasBeenCalled("createTodo")).toEqual({name: "todo list for team", id: "generatedId", createdAt: new Date(2022, 10, 24), ref: "teamRef"})
        });
    });
 
   
})