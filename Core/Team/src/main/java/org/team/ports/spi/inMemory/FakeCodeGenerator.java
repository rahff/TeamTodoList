package org.team.ports.spi.inMemory;

import org.team.ports.dto.GeneratedCodePair;
import org.team.ports.spi.CodeGenerator;

public class FakeCodeGenerator implements CodeGenerator {

    public GeneratedCodePair generateCode() {
        return new GeneratedCodePair("$$$$$$$$$$$", "generatedPassword");
    }
}
