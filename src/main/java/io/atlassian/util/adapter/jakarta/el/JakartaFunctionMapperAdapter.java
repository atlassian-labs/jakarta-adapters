package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.FunctionMapper;

import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

public class JakartaFunctionMapperAdapter extends FunctionMapper {

    private final javax.el.FunctionMapper delegate;

    public JakartaFunctionMapperAdapter(javax.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void mapFunction(String prefix, String localName, Method meth) {
        delegate.mapFunction(prefix, localName, meth);
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }
}
