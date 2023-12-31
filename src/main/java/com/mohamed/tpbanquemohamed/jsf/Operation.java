/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohamed.tpbanquemohamed.jsf;

import com.mohamed.tpbanquemohamed.entity.CompteBancaire;
import com.mohamed.tpbanquemohamed.entity.OperationBancaire;
import com.mohamed.tpbanquemohamed.service.GestionnaireCompte;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author MOHAMED
 */
@Named
@ViewScoped
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/
    private Long id;
  
    private OperationBancaire operationBancaire;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    
    

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }
    
   
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdCompteBancaire() {
        return id;
    }

    public void setIdCompteBancaire(Long idCompteBancaireOpere) {
        this.id = idCompteBancaireOpere;
    }

    public void setOperationBancaire(OperationBancaire operationBancaire) {
        this.operationBancaire = operationBancaire;
    }

    public OperationBancaire getOperationBancaire() {
        return operationBancaire;
    }
    
    /*
    public OperationBancaire operationBancairee() {
        this.operationBancaire = gestionnaireCompte.getOperation(idCompteBancaireOpere);
        return operationBancaire;
    }*/
    
    public void loadCustomer() {
    this.operationBancaire = gestionnaireCompte.findById(id);
  }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mohamed.tpbanquemohamed.entity.jsf.Operation[ id=" + id + " ]";
    }
    
}
