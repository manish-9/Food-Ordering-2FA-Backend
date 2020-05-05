package com.foodproject.food;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepository extends  CrudRepository<Food, Integer>{

	List<Food> findByFoodName(String foodName);
	List<Food> findByFoodOrigin(String foodOrigin);
}
