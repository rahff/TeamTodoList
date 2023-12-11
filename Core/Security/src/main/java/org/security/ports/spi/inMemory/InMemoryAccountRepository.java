package org.security.ports.spi.inMemory;

import org.security.ports.spi.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAccountRepository implements AccountRepository {

    private final List<String> data;
    public InMemoryAccountRepository(){
        data = new ArrayList<>();
    }
    public String save(String id) {
        data.add(id);
        return  id;
    }

    public List<String> items() {
        return data;
    }
}
