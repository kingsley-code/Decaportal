package com.decagon.recruitmentportal.repositories;
import com.decagon.recruitmentportal.entities.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends MongoRepository<Applicant, String> {
   Optional<Applicant> findByUuid(String uuid);
   Optional<Applicant> findByEmailAddress(String email);
}
