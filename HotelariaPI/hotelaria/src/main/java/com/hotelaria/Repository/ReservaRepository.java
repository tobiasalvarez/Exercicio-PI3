package com.hotelaria.Repository;

import java.util.List;

import com.hotelaria.Entity.Reserva;
import com.hotelaria.Util.JPAUtil;

import jakarta.persistence.EntityManager;

public class ReservaRepository {

    public void salvar(Reserva reserva) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Reserva> listar() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("FROM Reserva", Reserva.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public void atualizar(Reserva reserva) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(reserva);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Reserva reserva = em.find(Reserva.class, id);

            if (reserva != null) {
                em.getTransaction().begin();
                em.remove(reserva);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}