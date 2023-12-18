package org.example.persistance.entities.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;
    private boolean paid;

    public Subscription(){}
    public Subscription(String id, boolean paid){
        this.id = id;
        this.paid = paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public boolean paid() {
        return paid;
    }
}