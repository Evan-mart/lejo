package com.evan.lejo.api.storage.data;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Map;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service
public class DataStorage implements com.evan.lejo.api.storage.data.DataStorageHandler {

    protected static final Map< Integer, LockModeType > LOCK_TYPE = Map.of(
            4, LockModeType.PESSIMISTIC_READ,
            5, LockModeType.PESSIMISTIC_WRITE
    );
    protected final        EntityManager                entityManager;
    protected final        StampManagedEntity           stampManagedEntity;


    public DataStorage(
            final EntityManager entityManager,
            final StampManagedEntity stampManagedEntity ) {
        this.entityManager      = entityManager;
        this.stampManagedEntity = stampManagedEntity;
    }


    @Override
    public void lock( final Object entity, final int type ) {
        assert entity != null : "variable entity should not be null";

        this.entityManager.lock( entity, DataStorage.LOCK_TYPE.get( type ) );
    }


    @Override
    public void persist( final Object entity ) {
        assert entity != null : "variable entity should not be null";

        this.stampManagedEntity.buffer( entity );
    }


    @Override
    public void remove( final Object entity ) {
        assert entity != null : "variable entity should not be null";

        this.stampManagedEntity.remove( entity );
    }


    @Override
    public void save() {
        this.stampManagedEntity.collectBuffer( this.entityManager );

        this.entityManager.flush();

        this.stampManagedEntity.collectBuffer( this.entityManager );
    }
}
