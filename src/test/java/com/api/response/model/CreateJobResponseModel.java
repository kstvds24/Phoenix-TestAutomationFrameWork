package com.api.response.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobResponseModel {
	  private String message;
	  private CreateJobDataModel data;
	
}
