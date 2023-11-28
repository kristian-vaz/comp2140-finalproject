import java.io.Serializable;

public class Item implements Serializable{
    public String category;
    private String UPC;
    private String name;
    private Double price;
    private String manufacturer;
    private String expirationDate;

    //creates an item
    public Item(String category, String UPC, String name, Double price, String manufacturer, String expirationDate) {
        this.category = category;
        this.UPC = UPC;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }
    
    public void setCategory(String nCategory) {
        this.category = nCategory;
    }

    public String getUPC() {
        return UPC;
    }
    
    public void setUPC(String nUPC) {
        this.UPC = nUPC;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String nName) {
        this.name = nName;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double nPrice) {
        this.price = nPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String nManufacturer) {
        this.manufacturer = nManufacturer;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(String nExpirationDate) {
        this.expirationDate = nExpirationDate;
    }
}

