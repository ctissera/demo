package com.mycompany.demo.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {


	@Query("SELECT e FROM Empresa e ORDER BY e.razon_social")
	List<Empresa> findAllEmpresa();
	
	@Query("SELECT e FROM Empresa e where fecha_adhesion > :monthBefore ORDER BY e.razon_social")
	List<Empresa> findAllEmpresasAdheredLastMonth(@Param("monthBefore") Date monthBeforeDate);

	@Query("SELECT e FROM Empresa e where e.id = :empresaId")
	Empresa findEmpresaById(@Param("empresaId") Integer empresaId);

	@Query("SELECT e FROM Empresa e where e.cuit = :cuit")
	Empresa findEmpresaByCuit(@Param("cuit") String cuit);
	
	@Query("SELECT e FROM Empresa e "
			+ "WHERE e.razon_social LIKE CONCAT ('%', :search , '%') "	)
	Page<Empresa> findEmpresaByName(@Param("search") String search, Pageable pageable);
	

}
