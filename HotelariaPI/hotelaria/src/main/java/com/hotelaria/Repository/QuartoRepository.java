package com.hotelaria.Repository;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Entity.Quarto;
import com.hotelaria.Util.JPAUtil;

import jakarta.persistence.EntityManager;

public class QuartoRepository {

    public void salvar(Quarto quarto) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(quarto);

            em.getTransaction().commit();

        } finally {

            em.close();

        }
    }

    public List<Quarto> listar() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "FROM Quarto",
                    Quarto.class
            ).getResultList();

        } finally {

            em.close();

        }
    }
    public void deletar(Long id) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            Hospede hospede =
                    em.find(Hospede.class, id);

            if (hospede != null) {

                em.getTransaction().begin();

                em.remove(hospede);

                em.getTransaction().commit();

            }

        } finally {

            em.close();

        }
    }

    public void atualizar(Hospede hospede) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(hospede);

            em.getTransaction().commit();

        } finally {

            em.close();

        }
    }

    public Quarto buscarPorId(Long id) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
        return em.find(Quarto.class, id);
    } finally {
        em.close();
    }
}
}