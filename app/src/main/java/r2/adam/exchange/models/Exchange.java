package r2.adam.exchange.models;

public class Exchange {

    private String id;
    private String currency;
    private Double oldPrice;
    private Double newPrice;

    public Exchange(String id, String currency, Double oldPrice, Double newPrice) {
        this.id = id;
        this.currency = currency;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }
}
