package org.security.application;

import org.security.entities.User;
import org.security.ports.dto.PaymentSuccessEvent;
import org.shared.spi.UserRepository;

public class UpdateManagerSubscription {

    private final UserRepository userRepository;

    public UpdateManagerSubscription(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(PaymentSuccessEvent event){
        var foundManager = userRepository.findBySubscription(event.subscriptionId()).orElseThrow();
        var entity = User.fromDto(foundManager);
        entity.paidSubscription();
        userRepository.save(entity.snapshot());
    }
}
