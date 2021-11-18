package com.example.restservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {

	Logger logger = LoggerFactory.getLogger(SearchController.class);

	@GetMapping("/search")
	public Object[] products(@RequestParam(defaultValue = "") String term) {
		Product[] products = getProducts();
		return Arrays.stream(products).filter(product -> product.getName().toLowerCase().contains(term)).toArray();
	}

	private static Product[] getProducts()
	{
		final String uri = "http://localhost:8081/product-service/api/product/all";

		RestTemplate restTemplate = new RestTemplate();

		Product[] products = restTemplate.getForObject(
				uri, Product[].class);
		return products;
	}
}
