package com.espiritualrandom.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

public class EncriptyUtilImpl implements EncriptyUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String cifrarInformacao(String informacao) {
        return new String(getEncoder().encodeToString(informacao.getBytes()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String decifrarInformacao(String informacao) {
        return new String(getDecoder().decode(informacao.getBytes()));
    }
}
