package ies.lab2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockPortfoloTest {

    private StockPortfolio portfolio;

    @Mock
    IStockmarketService service;

    @BeforeEach
    public void setUp() {
        portfolio = new StockPortfolio(service);

        when(service.lookUpPrice("orange")).thenReturn(2.0);
        when(service.lookUpPrice("lima")).thenReturn(1.0);
    }

    @Test
    public void testTotalValue() {

        portfolio.addStock(new Stock("orange", 50));
        portfolio.addStock(new Stock("lima", 51));

        assertThat(portfolio.getTotalValue(), is(151.0));

    }
}
