package jpa.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;
import jpa.services.GeneriqueDao;

import java.util.List;

/**
 * GeneriqueDao (DAO générique) contient les méthodes génériques, inchangeables et nécéssaire dans les classes DAO.
 * @param <ID> est le type (String) de l'identifiant d'une instance de la classe.
 * @param <T> est le nom de la classe fille qui 2tend la classe abstract GeneriqueDao
 */
public abstract class GeneriqueDaoImpl<T, ID> implements GeneriqueDao<T, ID> {
    @PersistenceContext
    private final EntityManager manager;
    protected Class<T> className;

    protected GeneriqueDaoImpl() {
        this.manager = EntityManagerHelper.getEntityManager();
    }

    /**
     * Enregistre l'objet pour la première fois dans la base de données.
     * Si l'identifiant de l'objet n'existe pas dejà dans la base de donnée alors l'objet sera enregistré et l'operation va
     * renvoyer true. Sinon l'opération renvera false.
     * @param entite L'objet passé en paramètre
     * @return le résultat retourné, (true ou false)
     */
    @Override
    public T create(T entite) {
        if (entite == null) {
            throw new IllegalArgumentException("Erreur, l'entité ne doit pas être nulle");
        }
        EntityTransaction t = manager.getTransaction();
        t.begin();
        manager.persist(entite);
        manager.merge(entite);
        t.commit();
        return entite;
    }

    /**
     * Met l'objet à jour dans la base de données.
     * Si l'identifiant de l'objet existe bien dejà dans la base de donnée alors l'objet sera mise à jour et l'opération va
     * renvoyer true. Sinon l'opération renvera false.
     * @param entite L'objet passé en paramètre
     * @return le résultat retourné, (true ou false)
     */
    @Override
    public T update(ID id, T entite) {
        if (id == null) {
            throw new IllegalArgumentException("Erreur, id ne doit pas etre null");
        }
        if (entite == null) {
            throw new IllegalArgumentException("Erreur," + className + " ne doit pas etre null");
        }

        EntityTransaction t = manager.getTransaction();
        t.begin();
        T ent = manager.merge(entite);
        t.commit();
        return ent;
    }


    /**
     * Trouver un objet en base de données à partir de son identifiant.
     * @param id l'identifiant de l'objet
     * @return l'objet trouvé
     */
    @Override
    public T getById(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Erreur, id ne doit pas etre null");
        }
        return manager.find(className, id);
    }


    @Override
    public List<T> getAll() {
        return manager.createQuery("SELECT d FROM " + className.getName() + " as d", className).getResultList();

    }

    /**
     * Supprimer un objet de la base de données
     * @param id l'objet à supprimer
     */
    @Override
    public void delete(ID id) {
        T entity = manager.find(className, id);
        EntityTransaction t = manager.getTransaction();
        t.begin();
        if (entity != null) {
            manager.remove(entity);
        }
        t.commit();
    }

    @Override
    public long count() {
        return manager.createQuery("SELECT c FROM entity c, className").getResultList().size();

    }
}
