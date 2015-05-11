package com.github.tomakehurst.wiremock.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class TransformerDeserialiser extends JsonDeserializer<Transformer> {
    @Override
    public Transformer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonToken currentToken = jp.getCurrentToken();
        if (currentToken.equals(JsonToken.VALUE_STRING)) {
            return new Transformer(jp.getText());
        } else {
            //todo perhaps we can keep a registry of transformers so we know the type of the param?
            TransformerWithoutDeserialiser transformerDeserialiser =  ctxt.readValue(jp, TransformerWithoutDeserialiser.class);
            return new Transformer(transformerDeserialiser.name, transformerDeserialiser.param);
        }
    }

    static class TransformerWithoutDeserialiser {
        @JsonProperty(required = true)
        private String name;
        @JsonProperty(required = true)
        private Object param;
    }
}
