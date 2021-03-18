package com.espiritualrandom.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EncriptyUtilImplTest {

    @Test
    public void deveCifrarData () {
        Date data = new Date();
        String dadoCifrado = new EncriptyUtilImpl().cifrarInformacao(data.toString());
        String dadoDecifrado = new EncriptyUtilImpl().decifrarInformacao(dadoCifrado);
        Assert.assertEquals(dadoDecifrado, data.toString());
    }

}
