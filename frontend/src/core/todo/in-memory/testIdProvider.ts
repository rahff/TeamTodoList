import { IDProvider } from "src/core/shared/interfaces/IDProvider";


export class TestIDProvider implements IDProvider {

    public generate(): string {
        return "generatedId";
    }

}