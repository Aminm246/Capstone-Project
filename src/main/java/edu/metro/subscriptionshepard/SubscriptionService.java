package edu.metro.subscriptionshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

@Service
@Transactional
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public void create(Subscription sub) {
        repository.save(sub);
    }

    public void update(Subscription sub) {
        repository.save(sub);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Subscription> retrieve(Long id) {
        return repository.findById(id);
    }

    public List<Subscription> retrieveAll() {
        return repository.findAll();
    }
}
