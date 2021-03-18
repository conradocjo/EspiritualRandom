package com.espiritualrandom.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

public interface DateUtil {
    String  getDataFormatada (Date data);

    String getDataAtualFormatada();

    @RequiresApi(api = Build.VERSION_CODES.O)
    String getDataAtualCifrada();
}
