package com.github.otr.home_lib.upload_management.framework.adapter.output.postres;

import com.github.otr.home_lib.upload_management.domain.entity.BinaryFile;

import com.github.otr.home_lib.upload_management.framework.adapter.output.postres.data.BinaryFileDbo;
import com.github.otr.home_lib.upload_management.framework.adapter.output.postres.mapper.BinaryFileMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

/**
 *
 */
public class BinaryFilePostgresAdapter implements BinaryFilePostgresOutputPort {

    private static BinaryFilePostgresAdapter instance;

    @PersistenceContext
    private EntityManager em;

    private BinaryFilePostgresAdapter() {
        setUpPostgresDatabase();
    }

    @Override
    public BinaryFile fetchBinaryFileById(String uuId) {
        var binaryFileDbo = em.getReference(BinaryFileDbo.class, uuId);
        return BinaryFileMapper.toDomain(binaryFileDbo);
    }

    @Override
    public boolean persistBinaryFile(BinaryFile binaryFile) {
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
