package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger is not found with given id: " + id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> foundAll = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return foundAll.getResultList();
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
        TypedQuery<Burger> findByPriceQuery = entityManager.createQuery("SELECT b FROM b WHERE b.price > :price ORDER BY b.price desc", Burger.class);
        findByPriceQuery.setParameter("price", price);
        return findByPriceQuery.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> findByBreadType = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name desc", Burger.class);
        findByBreadType.setParameter("breadType", breadType);
        return findByBreadType.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> findByContentQuery = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%', :content, '%')", Burger.class);
        findByContentQuery.setParameter("content", content);
        return findByContentQuery.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
        Burger found = findById(id);
        entityManager.remove(found);
        return found;
    }
}
