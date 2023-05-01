package com.quangduong.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quangduong.inventoryservice.model.Inventory;
import com.quangduong.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// @Bean
	CommandLineRunner loadData(InventoryRepository repository) {
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("iphone_13")
					.quantity(100)
					.build();
			Inventory inventory2 = Inventory.builder()
					.skuCode("iphone_14")
					.quantity(50)
					.build();

			repository.save(inventory1);
			repository.save(inventory2);
		};
	}

}
