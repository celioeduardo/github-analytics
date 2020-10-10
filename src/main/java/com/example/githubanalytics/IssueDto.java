package com.example.githubanalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class IssueDto {
	private String userName;
	private String repository;
}
