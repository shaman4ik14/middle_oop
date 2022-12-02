package com.example.demo.company;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT s FROM Company s WHERE s.link = ?1")
    Optional<Company> findCompanyByLink(String link);

}
