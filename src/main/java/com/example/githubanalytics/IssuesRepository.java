package com.example.githubanalytics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IssuesRepository extends CrudRepository<Issue, Long>{

	
}
