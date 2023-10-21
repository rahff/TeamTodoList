import { createStore } from "../Store"
import { GLOBAL_FAKE_STATE, GLOBAL_INITIAL_STATE } from "../shared/inMemory.store"
import { todoAddedEvent, todoDeletedEvent, todoDonedEvent, todoListDetailsReceivedEvent } from "./Events";
import { TodoListDetailsStoreApi } from "./TodoListDetailsStoreApi";
import { task5, todoListDetailsFakeState, todoListDetailsFakeStateAfterDeletingTask2, todoListDetailsFakeStateAfterDoneTask2 } from "./data/inMemory.store";



describe("TodoListDetailsSlice", () => {

    it("when todoListDetailsReceived event fired", () => {
        const store = createStore({...GLOBAL_INITIAL_STATE});
        const storeApi = new TodoListDetailsStoreApi(store);
        const event = todoListDetailsReceivedEvent(todoListDetailsFakeState);
        storeApi.fireEvent(event);
        storeApi.getTodoListDetails().subscribe((details) => {
            expect(details).toEqual(todoListDetailsFakeState.details);
        })
    });

    it("when todoAdded event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TodoListDetailsStoreApi(store);
        const event = todoAddedEvent(task5);
        storeApi.fireEvent(event);
        storeApi.getTodoListDetails().subscribe((details) => {
            expect(details.todos).toEqual([...todoListDetailsFakeState.details.todos, task5]);
        })
    })

    it("when todoDeleted event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TodoListDetailsStoreApi(store);
        const event = todoDeletedEvent("todo2_of_todoList");
        storeApi.fireEvent(event);
        storeApi.getTodoListDetails().subscribe((details) => {
            expect(details.todos).toEqual(todoListDetailsFakeStateAfterDeletingTask2.details.todos);
        })
    })

    it("when todoDoned event fired", () => {
        const store = createStore({...GLOBAL_FAKE_STATE});
        const storeApi = new TodoListDetailsStoreApi(store);
        const event = todoDonedEvent("todo2_of_todoList");
        storeApi.fireEvent(event);
        storeApi.getTodoListDetails().subscribe((details) => {
            expect(details.todos).toEqual(todoListDetailsFakeStateAfterDoneTask2.details.todos);
        })
    })
})