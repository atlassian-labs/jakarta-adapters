package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXFunctionMapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.jsp.el.FunctionMapper;
import java.lang.reflect.Method;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFunctionMapperAdapter implements FunctionMapper, Adapted<javax.servlet.jsp.el.FunctionMapper> {

    private final javax.servlet.jsp.el.FunctionMapper delegate;

    public static FunctionMapper from(javax.servlet.jsp.el.FunctionMapper delegate) {
        if (delegate instanceof JavaXFunctionMapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaFunctionMapperAdapter::new);
    }

    protected JakartaFunctionMapperAdapter(javax.servlet.jsp.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.el.FunctionMapper getDelegate() {
        return delegate;
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
