package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.se.web.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findTopByOrderByCompanyIdDesc();
}
