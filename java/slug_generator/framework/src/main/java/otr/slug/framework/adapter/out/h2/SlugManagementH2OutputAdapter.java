package otr.slug.framework.adapter.out.h2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import otr.slug.application.port.out.SlugManagementOutputPort;
import otr.slug.domain.vo.Slug;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;

public class SlugManagementH2OutputAdapter implements SlugManagementOutputPort {

    private static SlugManagementH2OutputAdapter instance;

    @PersistenceContext
    private EntityManager em;

    private SlugManagementH2OutputAdapter() {
        setUpH2Database();
    }

    @Override
    public Slug fetchSlugById(UUID slugId) {
        SlugData slugData = em.getReference(SlugData.class, slugId);
        return SlugH2Mapper.toDomain(slugData);
    }

    @Override
    public Slug persistSlug(Slug slug) {
        SlugData slugData = SligH2Mapper.toH2(slug);
        em.persist(slugData);
        return slug;
    }

    @Override
    public List<Slug> retrieveSlugs() {
        return null;
    }

    private void setUpH2Database() {
        EntityManagerFactory emFactory = Persistence
            .createEntityManagerFactory("slugs");
        this.em = emFactory.createEntityManager();
    }

    public SlugManagementH2OutputAdapter getInstance() {
        if (instance == null) {
            instance = new SlugManagementH2OutputAdapter();
        }
        return instance;
    }

}
