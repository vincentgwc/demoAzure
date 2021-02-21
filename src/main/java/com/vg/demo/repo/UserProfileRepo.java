package com.vg.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vg.demo.entity.UserProfile;

public interface UserProfileRepo extends PagingAndSortingRepository<UserProfile, String>{

}
