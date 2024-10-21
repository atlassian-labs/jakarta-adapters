package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaVariableResolverAdapter implements VariableResolver, Adapted<javax.servlet.jsp.el.VariableResolver> {

    private final javax.servlet.jsp.el.VariableResolver delegate;

    public static VariableResolver from(javax.servlet.jsp.el.VariableResolver delegate) {
        if (delegate instanceof JavaXVariableResolverAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaVariableResolverAdapter::new);
    }

    JakartaVariableResolverAdapter(javax.servlet.jsp.el.VariableResolver delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.el.VariableResolver getDelegate() {
        return delegate;
    }

    @Override
    public Object resolveVariable(String s) throws ELException {
        try {
            return delegate.resolveVariable(s);
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
