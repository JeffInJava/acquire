package org.jeff.acquire.dao;

import org.jeff.acquire.entity.Accmalfunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccmalfunctionRepository extends JpaRepository<Accmalfunction,String> {
    void deleteBySdate(String sdate);
}
