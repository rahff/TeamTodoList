
import { createStore } from "../Store";
import {  todoListsReceivedEvent, todoListAddedEvent, todoListDeletedEvent } from "./Events";
import { TodoListsStoreApi } from "./TodoListStoreApi";
import { TodoList } from "src/core/application/todo/dto/TodoList";
import { GLOBAL_FAKE_STATE, GLOBAL_INITIAL_STATE } from "../shared/inMemory.store";
import { newTodoList, todoListsFakeState, todoListsFakeStateAfterDeleteSecond } from "./data/inMemory.store";



describe("TodoListViewSlice", () => {

    it("when todoListViewReceived event fired", () => {
        const store = createStore({...GLOBAL_INITIAL_STATE});
        const storeApi = new TodoListsStoreApi(store)
        const event = todoListsReceivedEvent(todoListsFakeState)
        storeApi.fireEvent(event);
        storeApi.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsFakeState.viewModel.lists);
        })
    })

    it("when todoListAdded event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TodoListsStoreApi(store);
        const event = todoListAddedEvent(newTodoList);
        storeApi.fireEvent(event);
        storeApi.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual([...todoListsFakeState.viewModel.lists, newTodoList]);
        })
    })

    it("when todoListDeleted event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TodoListsStoreApi(store)
        const event = todoListDeletedEvent("todoList2_of_user")
        storeApi.fireEvent(event);
        storeApi.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsFakeStateAfterDeleteSecond.viewModel.lists)
        })
    })
})