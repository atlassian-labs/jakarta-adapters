package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import jakarta.servlet.jsp.el.FunctionMapper;

import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

public class JakartaFunctionMapperAdapter implements FunctionMapper {

    private final javax.servlet.jsp.el.FunctionMapper delegate;

    public JakartaFunctionMapperAdapter(javax.servlet.jsp.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }
}
