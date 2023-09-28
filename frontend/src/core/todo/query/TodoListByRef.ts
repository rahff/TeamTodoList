import { Observable } from "rxjs";
import { TodoList } from "../dto/TodoList";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";

export class TodoListByRef {
    
    public constructor(private queryHandler: TodoQueryHandler){}

    public query(ref: string): Observable<TodoList[]> {
        return this.queryHandler.getTodoListByRef(ref);
    }
}