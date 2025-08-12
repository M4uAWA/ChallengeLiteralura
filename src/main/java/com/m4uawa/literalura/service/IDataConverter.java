package com.m4uawa.literalura.service;

public interface IDataConverter {
    <T> T obtainData(String json, Class<T> type);
}
