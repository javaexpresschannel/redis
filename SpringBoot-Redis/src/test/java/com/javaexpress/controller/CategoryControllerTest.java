package com.javaexpress.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaexpress.entities.Category;
import com.javaexpress.service.CategoryService;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void createCategoryTest() throws JsonProcessingException, Exception {
		
		Category category = new Category();
		category.setName("Mobile");
		category.setBarCode("dfafa");
		
		ObjectMapper mapper = new ObjectMapper();
		Mockito.when(categoryService.createCategory(ArgumentMatchers.any(Category.class))).thenReturn(category);
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category/create")
			.content(mapper.writeValueAsString(category))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		
		resultActions
			.andDo(print())
			.andExpect(jsonPath("$.name",is(category.getName())));
			
	}
	
	
}
