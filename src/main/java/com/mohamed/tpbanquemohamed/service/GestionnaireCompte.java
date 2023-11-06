import jakarta.enterprise.context.RequestScoped;
import jakarta.annotation.sql.DataSourceDefinition;


@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="Mouhammad9266201", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
        "useSSL=false",
        "allowPublicKeyRetrieval=true",
        "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
@RequestScoped    
public class GestionnaireCompte {

    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }
    
    // Vos méthodes et logique métier peuvent suivre ici.
}
