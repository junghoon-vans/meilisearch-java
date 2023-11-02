package com.meilisearch.sdk.json;

import com.meilisearch.sdk.exceptions.JsonDecodingException;
import com.meilisearch.sdk.exceptions.JsonEncodingException;

public interface JsonHandler {
    /**
     * @param o the Object to serialize
     * @return the serialized Object {@code o}
     * @throws JsonEncodingException if object cannot be serialized
     */
    String encode(Object o);

    /**
     * @param o Object to deserialize, most of the time this is a string
     * @param targetClass return type
     * @param parameters in case the return type is a generic class, this is a list of types to use
     *     with that generic.
     * @param <T> Abstract type to deserialize
     * @return the deserialized object
     * @throws JsonDecodingException if object cannot be deserialized
     */
    <T> T decode(Object o, Class<?> targetClass, Class<?>... parameters);
}
