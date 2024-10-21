package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaFunctionMapperAdapter;

import javax.servlet.jsp.el.FunctionMapper;
import java.lang.reflect.Method;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXFunctionMapperAdapter implements FunctionMapper {

    private final jakarta.servlet.jsp.el.FunctionMapper delegate;

    public static FunctionMapper from(jakarta.servlet.jsp.el.FunctionMapper delegate) {
        if (delegate instanceof JakartaFunctionMapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXFunctionMapperAdapter::new);
    }

    JavaXFunctionMapperAdapter(jakarta.servlet.jsp.el.FunctionMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.jsp.el.FunctionMapper getDelegate() {
        return delegate;
    }

    @Override
    public Method resolveFunction(String s, String s1) {
        return delegate.resolveFunction(s, s1);
    }
}
