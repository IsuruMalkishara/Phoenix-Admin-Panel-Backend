package com.oxcentra.phoenix.adminpanel.repository;

import com.oxcentra.phoenix.adminpanel.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
