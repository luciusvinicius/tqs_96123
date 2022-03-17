package ies.lab2;

import java.util.ArrayList;
import java.util.List;

public class StockPortfolio {
    
    public List<Stock> stocks = new ArrayList<Stock>();
    public IStockmarketService stockmarket;

    public StockPortfolio(IStockmarketService s) {
        stockmarket = s;
    }

    public void addStock(Stock s) {
        stocks.add(s);
    }

    public double getTotalValue() {

        double total = 0;

        for (Stock s : stocks) {
            total += stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }

        return total;

    }

}
