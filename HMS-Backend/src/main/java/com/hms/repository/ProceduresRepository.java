package com.hms.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.entity.Procedures;

@Repository
public interface ProceduresRepository extends JpaRepository<Procedures, Integer>{
   
//	@Query("SELECT p.cost FROM Procedures p WHERE p.name = :name")
    Optional<Procedures> findCostOfProceduresByName(String name);

//	List<Procedures> findNameByCode(List<TrainedIn> trainedIn);

}