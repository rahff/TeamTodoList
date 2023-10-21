import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";


export class TestIDProvider implements IDProvider {

    public generate(): string {
        return "generatedId";
    }

}