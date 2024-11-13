package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaFunctionMapperAdapter;

import javax.el.FunctionMapper;
import java.lang.reflect.Method;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFunctionMapperAdapter extends FunctionMapper implements Adapted<jakarta.el.FunctionMapper> {

    private final jakarta.el.FunctionMapper delegate;

    public static FunctionMapper from(jakarta.el.FunctionMapper delegate) {
        if (delegate instanceof JakartaFunctionMapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFunctionMapperAdapter::new);
    }

    protected JavaXFunctionMapperAdapter(jakarta.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.FunctionMapper getDelegate() {
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
}
