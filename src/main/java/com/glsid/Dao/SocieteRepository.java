package com.glsid.Dao;

import com.glsid.Entities.Societe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by X-MART on 5/26/2017.
 */
public interface SocieteRepository extends JpaRepository<Societe,String> {
    public Page<Societe> findSocietesByNomContains(String nom,Pageable pageable);
    public Page<Societe> findAll(Pageable pageable);
}
