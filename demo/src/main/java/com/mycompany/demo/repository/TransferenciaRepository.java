package com.mycompany.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

	@Query("SELECT t FROM Transferencia t where fecha_transferencia > :monthBefore")
	List<Transferencia> findAllTransferedLastMonth(@Param("monthBefore") Date monthBeforeDate);
	
	@Query("SELECT t FROM Transferencia t ORDER BY t.fecha_transferencia desc")
	List<Transferencia> findAllTransferencia();
	
	@Query("SELECT p FROM Transferencia p where p.id = :id")
	Transferencia findTransferenciaById(@Param("id") Integer id);

}
