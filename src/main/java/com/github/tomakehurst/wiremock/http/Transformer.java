package com.github.tomakehurst.wiremock.http;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = TransformerDeserialiser.class)
public final class Transformer<T> {
    private final String name;
    private final T param;

    public Transformer(String name) {
        this.name = name;
        this.param = null;
    }

    public  Transformer(String name, T param) {
        this.name = name;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public T getParam() {
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transformer that = (Transformer) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (param != null ? !param.equals(that.param) : that.param != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (param != null ? param.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transformer{" +
                "name='" + name + '\'' +
                ", param=" + param +
                '}';
    }
}
