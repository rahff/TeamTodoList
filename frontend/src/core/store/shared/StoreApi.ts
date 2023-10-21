import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import { GlobalState } from "../Store";
import { EventWithPayload } from "./Event";



export abstract class StoreApi {

    protected unSubscribe: Function;

    public constructor(protected store: ToolkitStore<GlobalState>){
        this.unSubscribe = this.store.subscribe(() => this.emitNewState())
    }

    protected abstract emitNewState(): void

    public fireEvent<T>(action: EventWithPayload<T>): void {
        this.store.dispatch(action);
    }

    public removeStoreSubscription(): void {
        this.unSubscribe();
    }
}