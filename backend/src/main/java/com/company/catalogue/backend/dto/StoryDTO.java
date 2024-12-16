package com.company.catalogue.backend.dto;

import lombok.Data;

@Data
public class StoryDTO {

	private long company_id;
	private long user_id;
	private String title;
	private String content;
	private int likes;
	private String status;

}
