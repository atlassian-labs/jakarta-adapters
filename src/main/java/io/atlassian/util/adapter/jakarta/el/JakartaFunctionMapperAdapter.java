package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXFunctionMapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.el.FunctionMapper;
import java.lang.reflect.Method;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaFunctionMapperAdapter extends FunctionMapper implements Adapted<javax.el.FunctionMapper> {

    private final javax.el.FunctionMapper delegate;

    public static FunctionMapper from(javax.el.FunctionMapper delegate) {
        if (delegate instanceof JavaXFunctionMapperAdapter) {
            JavaXFunctionMapperAdapter castDelegate = (JavaXFunctionMapperAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaFunctionMapperAdapter::new);
    }

    protected JakartaFunctionMapperAdapter(javax.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.FunctionMapper getDelegate() {
        return delegate;
    }

    @Override
    public void mapFunction(String prefix, String localName, Method meth) {
        delegate.mapFunction(prefix, localName, meth);
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
