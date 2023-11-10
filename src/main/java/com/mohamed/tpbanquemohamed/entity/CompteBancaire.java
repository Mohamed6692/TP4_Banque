/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohamed.tpbanquemohamed.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Cette classe représente un compte bancaire avec des propriétés telles que l'identifiant, le nom du titulaire et le solde.
 * Elle fournit des méthodes pour déposer et retirer de l'argent, ainsi que des méthodes de base pour la gestion du compte.
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.count", query = "SELECT COUNT(c) FROM CompteBancaire c")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList<>();

    private String nom;
    private int solde;

    public CompteBancaire() {
        // Ajout d'une opération pour la création du compte
        operations.add(new OperationBancaire("Création du compte", solde));
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;

        // Ajout d'une opération pour la création du compte
        operations.add(new OperationBancaire("Création du compte", solde));
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public void deposer(int montant) {
        solde += montant;

        // Ajout d'une opération de dépôt
        operations.add(new OperationBancaire("Dépôt", montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;

            // Ajout d'une opération de retrait
            operations.add(new OperationBancaire("Retrait", montant));
        } else {
            solde = 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.mohamed.tpbanquemohamed.entity.CompteBancaire[ id=" + id + " ]";
    }
}
