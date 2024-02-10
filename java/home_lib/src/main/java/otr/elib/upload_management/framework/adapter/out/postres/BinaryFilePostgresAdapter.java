package otr.elib.upload_management.framework.adapter.out.postres;

import otr.elib.upload_management.application.port.out.BinaryFileOutputPort;
import otr.elib.upload_management.domain.entity.BinaryFile;

import otr.elib.upload_management.framework.adapter.out.postres.data.BinaryFileDbo;
import otr.elib.upload_management.framework.adapter.out.postres.mapper.BinaryFileMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

/**
 *
 */
public class BinaryFilePostgresAdapter implements BinaryFileOutputPort {

    private static BinaryFilePostgresAdapter instance;

    @PersistenceContext
    private EntityManager em;

    private BinaryFilePostgresAdapter() {
        setUpPostgresDatabase();
    }

    @Override
    public BinaryFile retrieveBinaryFileById(String uuId) {
        var binaryFileDbo = em.getReference(BinaryFileDbo.class, uuId);
        return BinaryFileMapper.toDomain(binaryFileDbo);
    }

    @Override
    public boolean storeBinaryFile(BinaryFile binaryFile) {
        var binaryFileDbo = BinaryFileMapper.toDbo(binaryFile);
        em.persist(binaryFileDbo);
        return true;
    }

    private void setUpPostgresDatabase() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("binary_file");
        EntityManager em = emFactory.createEntityManager();
        this.em = em;
    }

    public static BinaryFilePostgresAdapter getInstance() {
        if (instance == null) {
            instance = new BinaryFilePostgresAdapter();
        }

        return instance;
    }

}
