import { Observable } from "rxjs";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { TodoList } from "../dto/TodoList";
import { TodoListDetails } from "../dto/TodoListDetails";

export class TodoListById {
    
    public constructor(private queryHandler: TodoQueryHandler){}

    public query(id: string): Observable<TodoListDetails> {
        return this.queryHandler.getTodoListDetailById(id);
    }
}