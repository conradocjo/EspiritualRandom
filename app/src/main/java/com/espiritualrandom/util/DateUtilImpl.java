package com.espiritualrandom.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.espiritualrandom.util.ServiceConstants.*;

public class DateUtilImpl implements DateUtil {

    @Override
    public String getDataFormatada(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(DIA_MES_ANO);
        return sdf.format(data);
    }

    @Override
    public String getDataAtualFormatada() {
        return getDataFormatada(new Date());
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDataAtualCifrada() {
        return new EncriptyUtilImpl().cifrarInformacao(new DateUtilImpl().getDataFormatada(new Date()));
    }

}
