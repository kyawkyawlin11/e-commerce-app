package com.visibleone.ecommerce.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel implements Serializable {

    @SerializedName("code_no")
    @Expose
    private String codeNo;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("images")
    @Expose
    private List<String> images;
    @SerializedName("us")
    @Expose
    private List<String> usSize;
    @SerializedName("uk")
    @Expose
    private List<String> ukSize;
    @SerializedName("eu")
    @Expose
    private List<String> euSize;
    @SerializedName("color")
    @Expose
    private List<String> colorList;
    @SerializedName("p_category")
    @Expose
    private String pCategory;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("brand_logo")
    @Expose
    private String brand_logo;

    @SerializedName("stock")
    @Expose
    private Integer stock;

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public List<String> getUsSize() {
        return usSize;
    }

    public void setUsSize(List<String> usSize) {
        this.usSize = usSize;
    }

    public List<String> getUkSize() {
        return ukSize;
    }

    public void setUkSize(List<String> ukSize) {
        this.ukSize = ukSize;
    }

    public List<String> getEuSize() {
        return euSize;
    }

    public void setEuSize(List<String> euSize) {
        this.euSize = euSize;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}