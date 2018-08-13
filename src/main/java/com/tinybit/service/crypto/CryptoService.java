package com.tinybit.service.crypto;

import com.tinybit.model.CryptoDetail;

import java.util.List;

public interface CryptoService {

    void load();
    List<CryptoDetail> get();

}
