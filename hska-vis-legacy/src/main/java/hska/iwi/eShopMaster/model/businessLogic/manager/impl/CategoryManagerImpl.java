package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManagerImpl implements CategoryManager{

	RestTemplate restTemplate = new RestTemplate();
	private final String ipAddress = "category-service";

	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

	public CategoryManagerImpl() {

	}

	public void startHttpMessageConverter() {
		this.mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		this.restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	public List<Category> getCategories() {

		ResponseEntity<List<Category>> categories;
		this.startHttpMessageConverter();
		String uri = "http://" + this.ipAddress + ":8085/api/category/all";
		categories = restTemplate.exchange( uri ,HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
		});

		return categories.getBody();
	}

	public Category getCategory(int id) {

		ResponseEntity<Category> category;
		String uri = "http://" + this.ipAddress + ":8085/api/category/" + id;

		category = restTemplate.exchange( uri ,HttpMethod.GET, null, new ParameterizedTypeReference<Category>() {
		});

		return category.getBody();
	}

	public void addCategory(String name) {

		String uri = "http://" + this.ipAddress +  ":8085/api/category/add";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String categoryJSON = "{\"name\":" + "\"" + name + "\"}";

		System.out.println(categoryJSON);

		String body = categoryJSON;

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		ResponseEntity<Category> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Category.class);
	}

	public void delCategory(Category cat) {
		String uri = "http://" + this.ipAddress + ":8085/api/category/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", cat.getId() + "");

		restTemplate.delete ( uri,  params );
	}

	public void delCategoryById(int id) {

		String uri = "http://" + this.ipAddress + ":8085/api/category/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");

		System.out.println(id);

		restTemplate.delete ( uri,  params );
	}
}
