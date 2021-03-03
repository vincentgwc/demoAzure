package com.vg.demo.controller.rest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vg.demo.controller.rest.vo.UserProfileVO;
import com.vg.demo.entity.UserProfile;
import com.vg.demo.repo.UserProfileRepo;

@RestController
@RequestMapping("rest")
public class AzureRestController {
	
	@Autowired
	private UserProfileRepo userProfileRepo;
	
	@GetMapping("/users")
	List<UserProfile> all() {
		return (List<UserProfile>) userProfileRepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	UserProfile findByUserId(@PathVariable(value = "id") String id) {
		Optional<UserProfile> up = userProfileRepo.findById(id);
		if (up != null) {
			return up.get();
		}
		return null;
	}
	
	@PostMapping("/register")
	private UserProfile createUserProfile(@RequestBody UserProfileVO input) {
		Optional<UserProfile> up = userProfileRepo.findById(input.getUserId());
		if(up!= null) {
			UserProfile n_up = new UserProfile();
			n_up.setCreatedBy("REST");
			n_up.setCreatedTs(new Timestamp(System.currentTimeMillis()));
			n_up.setUserId(input.getUserId());
			n_up.setUserName(input.getUserName());
			n_up = userProfileRepo.save(n_up);
			return n_up;
		}
		
		return up.get();
	}
	
}
