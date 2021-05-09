package com.ratelimit.ratelimit.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ratelimit.ratelimit.dao.UserDao;
import com.ratelimit.ratelimit.entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Value("${app.X}")
	private long X;
	
	@Override
	public ResponseEntity<String> callAPI(long userId) {
		
		
		Optional<User> user=userDao.findById(userId);
		if(user.isPresent()) {
			double differenceInHours=compareTwoTimeStamps(user.get().getFirsthit(),new Timestamp(System.currentTimeMillis()));
			if(differenceInHours<1) 
			{
				System.out.println(differenceInHours);
				if(user.get().getNoOfHits()<X) 
				{
					user.get().setNoOfHits(user.get().getNoOfHits()+1);
					userDao.save(user.get());
					return ResponseEntity.status(HttpStatus.OK).body("Welcome! No. of attempts left "+String.valueOf(X-user.get().getNoOfHits())+ " till "+ new Timestamp(user.get().getFirsthit().getTime()+3600000)); 
				}
					
				else 
				   return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("No. of attempts exhausted. Please try after " + String.valueOf((int)(60-differenceInHours*60))+" minutes.");		
			}
				
			else 
			{
				user.get().setNoOfHits(1);
				user.get().setFirsthit(new Timestamp(System.currentTimeMillis()));
				userDao.save(user.get());
				return ResponseEntity.status(HttpStatus.OK).body("Welcome! No. of attempts left "+String.valueOf(X-1)+ " till "+ new Timestamp(user.get().getFirsthit().getTime()+3600000));
			}
			
		}
		else 
		{
			User newUser = new User(userId,1,new Timestamp(System.currentTimeMillis()));
			userDao.save(newUser);
			return ResponseEntity.status(HttpStatus.OK).body("Welcome! No. of attempts left "+String.valueOf(X-1)+ " till "+ new Timestamp(newUser.getFirsthit().getTime()+3600000));
		}
	}
	
	public double compareTwoTimeStamps(java.sql.Timestamp oldTime,java.sql.Timestamp currentTime) {
		long milliseconds1=oldTime.getTime();
		long milliseconds2=currentTime.getTime();
		
		double diff=milliseconds2-milliseconds1;
		System.out.println(diff);
		double diffHours=diff/(60*60*1000);
		return diffHours;
	}

}
