package org.shared.dto;

import java.util.Objects;

public record SubscriptionDto(String id, boolean paid) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionDto that)) return false;
        if (paid != that.paid) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
       return Objects.hash(id, paid);
    }
}
