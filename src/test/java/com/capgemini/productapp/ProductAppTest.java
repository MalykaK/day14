package com.capgemini.productapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductAppTest {
	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	/*@Test
	public void testaddproduct()  throws Exception{
		String content = "{\r\n" + 
				"  \"productId\": \"123\",\r\n" + 
				"  \"productName\": \"Washing Machine\",\r\n" + 
				"  \"productCategory\": \"Electronics\",\r\n" + 
				"  \"productPrice\": \"30000\"\r\n" + 
				"}";
		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(123,"Washing Machine","Electronics",30000));
		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON))
			.content(content).accept(MediaType.APPLICATION_JSON))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$.productId").exists())
			 .andExpect(jsonPath("$.productName").exists())
			 .andExpect(jsonPath("$.productCategory").exists())
			 .andExpect(jsonPath("$.productPrice").exists())
			 .andExpect(jsonPath("$.productId").value("123"))
			 .andExpect(jsonPath("$.productName").value("Washing Machine"))
			 .andExpect(jsonPath("$.productCategory").value("Electronics"))
			 .andExpect(jsonPath("$.productPrice").value("30000"))
			 .andDo(print());
	}	*/

	@Test
	public void testAddProductWhichAddsProductObject() throws Exception
	{
		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(1234,"mobile","Electronics",16000));
		mockMvc.perform(post("/product").
				 	contentType(MediaType.APPLICATION_JSON_UTF8)
				 	.content("{\"productId\": \"1234\", \"productName\": \"Mobile\",\"productCategory\":\"Electronics\",\"productPrice\": \"16000\"}")
				 	.accept(MediaType.APPLICATION_JSON))
				 	.andExpect(status().isOk())
				 	.andExpect(jsonPath("$.productId").exists())
				 	.andExpect(jsonPath("$.productName").exists())
				 	.andExpect(jsonPath("$.productCategory").exists())
				 	.andExpect(jsonPath("$.productPrice").exists())
				 	.andDo(print());	
	}
}


