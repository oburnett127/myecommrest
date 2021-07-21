package com.oburnett127.MyEcomm.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.oburnett127.MyEcomm.model.Cart;
import com.oburnett127.MyEcomm.repository.util.CartRowMapper;

@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Cart createCart(Cart cart) {		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.setGeneratedKeyName("id");
		
		Map<String, Object> data = new HashMap<>();
		ArrayList<Integer> productIds = cart.getProductIds();
		int accountId = cart.getAccountId();
		Number id = 0;
		
		for(Integer x : productIds) {
			data.put("accountid", accountId);
			data.put("productid", x);
		
			List<String> columns = new ArrayList<>();
			columns.add("accountid");
			columns.add("productid");
			
			insert.setTableName("cart");
			insert.setColumnNames(columns);
			id = insert.executeAndReturnKey(data);
		}
		
		return getCart(id.intValue());
	}
	
	@Override
	public Cart getCart(Integer id) {
		Cart cart = jdbcTemplate.queryForObject("select * from cart where accountid = ?", new CartRowMapper(), id);
		
		return cart;
	}
	
//	@Override
//	public List<Cart> getCarts() {
//		List<Cart> carts = jdbcTemplate.query("select * from cart", new CartRowMapper());
//		
//		return carts;
//	}
	
//	@Override
//	public Cart updateCart(Cart cart) {
//		jdbcTemplate.update("update cart set email = ?, firstName = ?, lastName = ?, password = ?, isAdmin = ? where accountId = ?", 
//				cart.getEmail(), cart.getFirstName(), cart.getLastName(), cart.getPassword(), cart.isAdmin(), cart.getaccountId());
//		
//		return cart;
//	}
	
//	@Override
//	public void deleteCart(Integer id) {
//		NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
//		
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("id", id);
//		
//		namedTemplate.update("delete from cart where accountId = :id", paramMap);
//	}
}