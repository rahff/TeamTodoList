import { Observable } from "rxjs";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { Todo } from "../dto/Todo";



export class TodosByListId {
    
    public constructor(private queryHandler: TodoQueryHandler){}

    public query(listId: string): Observable<Todo[]> {
        return this.queryHandler.getTodosByListId(listId);
    }
}