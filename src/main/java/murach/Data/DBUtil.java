package murach.Data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("userListPU"); // Sử dụng persistence unit "default"
        } catch (Exception e) {
            System.out.println("Error can't found/create EntityManagerFactory: " + e.getMessage());
            return null;
        }
    }

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
