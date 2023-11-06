import com.mohamed.tpbanquemohamed.entity.CompteBancaire;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
import java.util.List;

@RequestScoped
public class BanqueManager {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    // ...

    @Transactional
    public void creerCompte(CompteBancaire compte) {
       em.persist(compte);
    }

    public List<CompteBancaire> getAllComptes() {
       Query query = em.createQuery("SELECT c FROM CompteBancaire c");
       return query.getResultList();
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
       return em.merge(compte);
    }

    @Transactional
    public void persist(CompteBancaire compte) {
       em.persist(compte);
    }
}
