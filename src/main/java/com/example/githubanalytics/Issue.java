package com.example.githubanalytics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issues")
@NoArgsConstructor
@Data
class Issue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String repository;

	Issue(@NotNull String username, @NotNull String repository) {
		super();
		this.username = username;
		this.repository = repository;
	}
	
	
}
