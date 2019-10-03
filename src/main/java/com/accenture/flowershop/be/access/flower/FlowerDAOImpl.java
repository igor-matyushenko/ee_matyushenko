package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.user.UserDAOImpl;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Repository
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    @Transactional
    public List<Flower> getAllFlowers() {
        try {
            TypedQuery<Flower> query =
                    em.createQuery("from Flower f ", Flower.class);
            LOG.debug("getAllFlowers: " + query.getResultList().toString());
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("List flowers  : не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public void addFlower(Flower flower) {
        LOG.debug("addFlower: " + flower);
        em.persist(flower);
    }

    @Override
    @Transactional
    public void deleteFlower(Flower flower) {
        LOG.debug("deleteFlower: " + flower);
        em.remove(flower);
    }

    @Override
    @Transactional
    public void updateFlower(Flower flower) {
        LOG.debug("updateFlower: " + flower);
        em.merge(flower);
    }

    @Override
    @Transactional
    public Flower getFlowerById(Long idFlower) {
        LOG.debug("getFlowerById: " + idFlower);
        return em.find(Flower.class, idFlower);
    }

    @Override
    @Transactional
    public List<Flower> getAllFlowersBySearch(String flowerName, BigDecimal minPrice, BigDecimal maxPrice) {
        TypedQuery<Flower> query =
                em.createQuery("from  Flower f where upper(f.titleFlower) like :flowerName " +
                        "and f.priceFlower between :minPrice and :maxPrice", Flower.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        query.setParameter("flowerName", "%" + flowerName + "%");
        LOG.debug("getAllFlowerBySearch  : " +
                "flowerName: " + flowerName +
                "; minPrice: " + minPrice +
                "; maxPrice: " + maxPrice);
        return query.getResultList();
    }

}