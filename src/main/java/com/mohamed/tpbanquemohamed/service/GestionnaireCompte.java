/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mohamed.tpbanquemohamed.service;
import com.mohamed.tpbanquemohamed.entity.CompteBancaire;
import com.mohamed.tpbanquemohamed.entity.OperationBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author MOHAMED
 */


@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="Mouhammad", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
        "useSSL=false",
        "allowPublicKeyRetrieval=true",
        "driverClass=com.mysql.cj.jdbc.Driver"
    }
)

@Named(value = "gestionnaireCompte")
@ApplicationScoped
public class GestionnaireCompte {

    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }
    
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public CompteBancaire getCompte(long id) {
        return em.find(CompteBancaire.class, id);
    }
    
        public OperationBancaire findById(long id) {
        return em.find(OperationBancaire.class, id);
    }
        
    @Transactional
    public void persist(OperationBancaire operationBancaire) {
        em.persist(operationBancaire);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public long nbComptes() {
        Query query = em.createNamedQuery("CompteBancaire.count");
        return (long) query.getSingleResult();
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
    
     @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    /**
     * Dépôt d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
