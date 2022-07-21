package com.ss.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    //@PersistenceContext tracks the state of persisted object. After this.em.persist(user), user will be tracked
    private EntityManager em;

    public long insert(User user){
        this.em.persist(user);
        return user.getId();
    }
}
