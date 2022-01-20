package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
	RestTemplate restTemplate = new RestTemplate();
	private final String ipAddress = "product-service";

	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();



	public ProductManagerImpl() {

	}

	public void startHttpMessageConverter() {
		this.mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		this.restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	public List<Product> getProducts() {
		ResponseEntity<List<Product>> products;
		this.startHttpMessageConverter();
		String uri = "http://" + this.ipAddress + ":8081/api/product/all";
			products = restTemplate.exchange( uri ,HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
			});

		return products.getBody();
	}

	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {
		this.startHttpMessageConverter();

		ResponseEntity<List<Product>> products;

		String uri = "http://" + this.ipAddress + ":8081/api/product/search?";


		if (!searchDescription.equals("")) {
			uri += "term=" + searchDescription;
		}
		if (!(searchMinPrice == null)) {
			uri += "&priceMin=" + searchMinPrice;
		} if (!(searchMaxPrice == null)) {
			uri += "&priceMax=" + searchMaxPrice;
		}

		products = restTemplate.exchange( uri ,HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
		});

		return products.getBody();
	}

	public Product getProductById(int id) {
		ResponseEntity<Product> product;
		String uri = "http://" + this.ipAddress + ":8081/api/product/" + id;

		product = restTemplate.exchange( uri ,HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {
		});

		return product.getBody();
	}


	public int addProduct(String name, double price, int categoryId, String details) {
		int productId = -1;

		String uri = "http://" + this.ipAddress +  ":8081/api/product/add";
		String productJSON = "";

		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);

		Product product = null;

		if(category != null){
			if(details == null){
				product = new Product(name, price, category);
				productJSON = "{\"name\": \"" + product.getName() + "\", \"price\": " + product.getPrice() + ", \"categoryId\": " + product.getCategory().getId() + "\" }";

			} else{
				product = new Product(name, price, category, details);
				productJSON = "{\"name\": \"" + product.getName() + "\", \"price\": " + product.getPrice() + ", \"categoryId\": " + product.getCategory().getId() + ", \"details\": \"" + product.getDetails() + "\" }";
			}
		}

		System.out.println(productJSON);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		body.add("1", productJSON);

		HttpEntity<String> requestEntity = new HttpEntity<String>(productJSON, headers);
		ResponseEntity<Product> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Product.class);

		return productId;
	}


	public void deleteProductById(int id) {
		String uri = "http://" + this.ipAddress + ":8081/api/product/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");

		restTemplate.delete ( uri,  params );

	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

}
