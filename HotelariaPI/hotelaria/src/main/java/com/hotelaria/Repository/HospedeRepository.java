package com.hotelaria.Repository;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Util.JPAUtil;

import jakarta.persistence.EntityManager;

public class HospedeRepository {

    public void salvar(Hospede hospede) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(hospede);

            em.getTransaction().commit();

        } finally {

            em.close();

        }
    }

    public List<Hospede> listar() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "FROM Hospede",
                    Hospede.class
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

    public Hospede buscarPorId(Long id) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
        return em.find(Hospede.class, id);
    } finally {
        em.close();
    }
}

}