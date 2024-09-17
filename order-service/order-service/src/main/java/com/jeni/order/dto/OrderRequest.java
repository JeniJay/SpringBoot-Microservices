package com.jeni.order.dto;

public record OrderRequest(String id,String skuCode,String name,int quantity,UserDetails userDetails) {

    public  record UserDetails(String email, String firstName, String lastName) {}

    public OrderRequest withId(String id)
    {
        return new OrderRequest(id,skuCode,name,quantity,userDetails);
    }
}
