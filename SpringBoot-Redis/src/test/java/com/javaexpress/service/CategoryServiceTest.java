package com.javaexpress.service;

import static org.mockito.Mockito.doNothing;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javaexpress.entities.Category;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.repository.CategoryRepository;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;

	@Test
	public void createCategoryTest() {
		
		Category inputCategory = new Category();
		inputCategory.setName("Mobile");
		
		Category dbCategory = new Category();
		dbCategory.setName("Mobile");
		dbCategory.setId(1234);
		dbCategory.setCreatedTime(new Date());
		
		Mockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class))).thenReturn(dbCategory);
		
		Category result = categoryService.createCategory(inputCategory);
		Assertions.assertEquals(inputCategory.getName(), result.getName());
	}
	
	@Test
	public void fetchCategoryTestWhenIdNotFound() {
		Assertions.assertThrows(ResourceNotFoundException.class, 
				() -> categoryService.fetchCategory(111));
	}
	
	
	@Test
	public void fetchCategoryTest() {
		Category dbCategory = new Category();
		dbCategory.setName("Mobile");
		dbCategory.setId(1234);
		Mockito.when(categoryRepository.findById(ArgumentMatchers.anyInt()))
		.thenReturn(Optional.of(dbCategory));

		Category result = categoryService.fetchCategory(111);
		Assertions.assertEquals(dbCategory.getName(), result.getName());
	}
	
	@Test
	public void deleteTest() {
		Category dbCategory = new Category();
		dbCategory.setName("Mobile");
		dbCategory.setId(111);
		Mockito.when(categoryRepository.findById(ArgumentMatchers.anyInt()))
		.thenReturn(Optional.of(dbCategory));
		
		doNothing().when(categoryRepository)
			.delete(ArgumentMatchers.any(Category.class));
		
		categoryService.deleteCategory(111);
	}
}
