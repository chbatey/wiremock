package com.github.tomakehurst.wiremock.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TransformerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeserialisingAsString() throws Exception {
        String noParams = "{ \"transformers\": [\"TransformerName\"] }";

        TestTransformers transformers = objectMapper.readValue(noParams, TestTransformers.class);

        List<Transformer<?>> expectedTransformers = Lists.<Transformer<?>>newArrayList(new Transformer("TransformerName"));
        assertThat(transformers.transformers, equalTo(expectedTransformers));
    }

    @Test
    public void testDeserialisingObjectWithNoParams() throws Exception {
        String noParams = "{ \"transformers\": [{\"name\":\"randomInt\", \"param\":\"hello\"}] }";

        TestTransformers transformers = objectMapper.readValue(noParams, TestTransformers.class);

        List<Transformer<?>> expectedTransformers = Lists.<Transformer<?>>newArrayList(new Transformer<String>("randomInt", "hello"));
        assertThat(transformers.transformers, equalTo(expectedTransformers));
    }

    @Test
    public void verifyEquals() throws Exception {
        EqualsVerifier.forClass(Transformer.class).verify();
    }

    static class TestTransformers {
        @JsonProperty
        private List<Transformer<?>> transformers;
    }
}