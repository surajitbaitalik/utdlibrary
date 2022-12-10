/**
 * 
 */
package com.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.EmployeeEntity;






/**
 * @author Surajit
 *
 */
public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {

}
