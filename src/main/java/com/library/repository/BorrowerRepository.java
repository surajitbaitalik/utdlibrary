/**
 * 
 */
package com.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.BorrowerEntity;
import com.library.model.Borrower;



/**
 * @author Surajit
 *
 */
@Repository("borrowerRepository")
public interface BorrowerRepository extends CrudRepository<BorrowerEntity, Long> {
	
	

}
