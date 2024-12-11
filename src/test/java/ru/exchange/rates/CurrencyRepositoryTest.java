//package ru.exchange.rates;
//
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.exchange.rates.dal.repository.CurrencyRepository;
//import ru.exchange.rates.model.ExchangeRateModel;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class CurrencyRepositoryTest {
//
//    @Autowired
//    private CurrencyRepository currencyRepository;
//
//    @Test
//    public void testSave() {
//        ExchangeRateModel rate = new ExchangeRateModel("USD", 1.0);
//        currencyRepository.save(rate);
//
//        List<ExchangeRateModel> rates = currencyRepository.getListExchangeRates();
//        System.out.println(rates);  // Выведите список сохранённых курсов
//
//        assertNotNull(rates);
//        assertTrue(rates.size() > 0);
//        assertEquals("USD", rates.get(0).getCurrency());
//    }
//
//}
