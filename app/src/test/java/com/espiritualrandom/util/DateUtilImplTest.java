package com.espiritualrandom.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class DateUtilImplTest {

    @Test
    public void deveFormatarData() {
        Date data = new Date();
        String dataFormatada = new DateUtilImpl().getDataFormatada(data);
        assertNotNull(dataFormatada);
    }
}
