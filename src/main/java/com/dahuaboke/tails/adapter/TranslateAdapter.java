package com.dahuaboke.tails.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: dahua
 * date: 2023/12/13 15:42
 */
public class TranslateAdapter {

    public TranslateAdapter() {
        if (!this.getClass().isAssignableFrom(TranslateAdapter.class)) {
            adapters.add(this);
        }
    }

    private static final List<TranslateAdapter> adapters = new ArrayList();

    public String transport(String text) {
        for (TranslateAdapter adapter : adapters) {
            try {
                return adapter.doTransport(text);
            } catch (Exception e) {
            }
        }
        return null;
    }

    protected String doTransport(String text) {
        return null;
    }
}
