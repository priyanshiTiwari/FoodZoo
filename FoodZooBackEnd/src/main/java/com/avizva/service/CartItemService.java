package com.avizva.service;

import java.util.List;

import com.avizva.model.CartItem;
import com.avizva.model.Products;
public interface CartItemService {
	
	
	
	public boolean saveCartItemService(CartItem cartitem);
	
	public boolean updateCartItemService(CartItem cartitem);
	
	public boolean deleteCartItemService(CartItem cartitem);
	
	public CartItem viewCartItemByIdService(int cart_item_id);
	
	public CartItem viewCartItemByProductId(String product_id);
	
	public List<CartItem> viewCartItemsService(CartItem cartitem);
	
	
    public List<CartItem> viewCartItemsByUserService(String user_name);
    
	
	public float totalPriceService(String user_name);
	
	public List<Products> getAllProductsInCart(String username);
	

}
