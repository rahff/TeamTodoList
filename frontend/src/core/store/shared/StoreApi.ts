import { Store } from "../Store";
import { EventWithPayload } from "./Event";



export abstract class StoreApi {

    protected unSubscribe: Function;

    public constructor(protected store: Store){
        this.unSubscribe = this.store.subscribe(() => this.emitNewState());
    }

    protected abstract emitNewState(): void

    public fireEvent<T>(event: EventWithPayload<T>): void {
        this.store.dispatch(event);
    }

    public removeStoreSubscription(): void {
        this.unSubscribe();
    }
}