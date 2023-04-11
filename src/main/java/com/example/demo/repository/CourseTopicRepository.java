package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseTopic;

@Repository
public interface CourseTopicRepository extends JpaRepository<CourseTopic, Integer>{
	
	List<CourseTopic> findBycourseTitle(String courseTitle);
	
}
