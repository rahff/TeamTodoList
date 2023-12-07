package org.example.persistance.mappers.security;

import org.example.persistance.entities.security.Subscription;
import org.shared.dto.SubscriptionDto;

import java.util.Optional;

public class SubscriptionMapper {
    public static Optional<Subscription> fromDto(Optional<SubscriptionDto> dto){
        if(dto.isPresent()){
            return dto.map(subscription -> new Subscription(subscription.id(), subscription.paid()));
        }
        return Optional.empty();
    }
}
