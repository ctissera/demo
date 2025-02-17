package com.mycompany.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Transferencia;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    public static final EntityManager entityManager = null;
    
	final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Query("SELECT t FROM Transferencia t where fecha_transferencia > :monthBefore")
	List<Transferencia> findAllTransferedLastMonth(@Param("monthBefore") Date monthBeforeDate);
	
	@Query("SELECT t FROM Transferencia t ORDER BY t.fecha_transferencia desc")
	List<Transferencia> findAllTransferencia();
	
	@Query("SELECT p FROM Transferencia p where p.id = :id")
	Transferencia findTransferenciaById(@Param("id") Integer id);

    public default Transferencia saveTransferencia(Transferencia transferencia) {
        Set<ConstraintViolation<Transferencia>> violations = validator.validate(transferencia);
        if (!violations.isEmpty()) {
            // Handle violations (e.g., throw an exception)
            throw new RuntimeException("Validation failed: " + violations.toString());
        }
        entityManager.persist(transferencia);
        return transferencia;
    }	

	@Query("SELECT t FROM Transferencia t where t.empresa.id = :empresaId")
	List<Transferencia> findTransferenciasByEmpresaId(@Param("empresaId") Integer empresaId);
    
}
