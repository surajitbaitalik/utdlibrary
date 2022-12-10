/**
 * 
 */
package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.CheckIn;
import com.library.repository.CheckInRepository;

/**
 * @author Surajit
 *
 */
@Service("checkinService")
public class CheckinServiceImpl implements CheckinService {
	@Autowired
	private CheckInRepository checkinRepo;

	@Override
	public String completeCheckIn(CheckIn checkin) {
		
		return checkinRepo.proceedCheckIn(checkin);
	}

	

}
