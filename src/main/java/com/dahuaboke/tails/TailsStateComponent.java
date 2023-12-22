package com.dahuaboke.tails;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * author: dahua
 * date: 2023/12/13 16:00
 */
@Service
@State(name = "transportConfig", storages = {@Storage("tails-config.xml")})
public final class TailsStateComponent implements PersistentStateComponent<TailsStateComponent> {

    private String baiduAppId;
    private String baiduSecret;
    private String aliAppid;
    private String aliSecret;


    @Override
    public @Nullable TailsStateComponent getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TailsStateComponent state) {
        this.baiduAppId = state.getBaiduAppId();
        this.baiduSecret = state.getBaiduSecret();
        this.aliAppid = state.getAliAppid();
        this.aliSecret = state.getAliSecret();
    }

    public String getBaiduAppId() {
        return baiduAppId;
    }

    public void setBaiduAppId(String baiduAppId) {
        this.baiduAppId = baiduAppId;
    }

    public String getBaiduSecret() {
        return baiduSecret;
    }

    public void setBaiduSecret(String baiduSecret) {
        this.baiduSecret = baiduSecret;
    }

    public String getAliAppid() {
        return aliAppid;
    }

    public void setAliAppid(String aliAppid) {
        this.aliAppid = aliAppid;
    }

    public String getAliSecret() {
        return aliSecret;
    }

    public void setAliSecret(String aliSecret) {
        this.aliSecret = aliSecret;
    }
}
