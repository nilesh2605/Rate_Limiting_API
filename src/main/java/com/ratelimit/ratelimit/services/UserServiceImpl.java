package com.ratelimit.ratelimit.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratelimit.ratelimit.dao.UserDao;
import com.ratelimit.ratelimit.entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	static long X=1;
	@Override
	public String getUser(long userId) {
		
//		User user= userDao.getOne(userId);
		Optional<User> user=userDao.findById(userId);
		if(user.isPresent()) {
			if(compareTwoTimeStamps(user.get().getFirsthit(),new Timestamp(System.currentTimeMillis()))<1) {
				if(user.get().getNoOfHits()<X) {
					user.get().setNoOfHits(user.get().getNoOfHits()+1);
					userDao.save(user.get());
					System.out.println("Existing user hitting within 1 hr and no. of hits pending");
					return "Welcome! No. of attempts left "+String.valueOf(X-user.get().getNoOfHits())+ " till one hour from "+ user.get().getFirsthit();  
				}
					
				else {
					//429 error code
					System.out.println("Existing user hitting within 1 hr aftr count>X");
					return "No. of attempts exhausted. Please try after one hour from "+String.valueOf(user.get().getFirsthit());
				}
					
			}
				
			else {
				user.get().setNoOfHits(1);
				user.get().setFirsthit(new Timestamp(System.currentTimeMillis()));
				userDao.save(user.get());
				System.out.println("Existing user hitting after 1 hr ");
				return "Welcome! No. of attempts left "+String.valueOf(X-1)+ " till "+ new Timestamp(System.currentTimeMillis()+3600000);
			     }
			
		}
		else {
			User newUser = new User(userId,1,new Timestamp(System.currentTimeMillis()));
			userDao.save(newUser);
			System.out.println("New user");
			return "Welcome! No. of attempts left "+String.valueOf(X-1)+ " till "+ new Timestamp(System.currentTimeMillis()+3600000);
		}
	}
	
	public long compareTwoTimeStamps(java.sql.Timestamp oldTime,java.sql.Timestamp currentTime) {
		long milliseconds1=oldTime.getTime();
		long milliseconds2=currentTime.getTime();
		
		long diff=milliseconds2-milliseconds1;
		long diffHours=diff/(60*60*1000);
		return diffHours;
	}

}
