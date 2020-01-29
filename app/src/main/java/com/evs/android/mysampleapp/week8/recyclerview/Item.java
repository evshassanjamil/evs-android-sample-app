package com.evs.android.mysampleapp.week8.recyclerview;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class Item {

    private long id;
    private String name;
    private String category;
    private int price;
    private String imageUrl;

    public Item(long id, String name, String category, int price, String imageUrl) {
        setId(id);
        setName(name);
        setCategory(category);
        setPrice(price);
        setImageUrl(imageUrl);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
