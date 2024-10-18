package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaVariableResolverAdapter;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXVariableResolverAdapter implements VariableResolver {

    private final jakarta.servlet.jsp.el.VariableResolver delegate;

    public static VariableResolver from(jakarta.servlet.jsp.el.VariableResolver delegate) {
        if (delegate instanceof JakartaVariableResolverAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXVariableResolverAdapter::new);
    }

    JavaXVariableResolverAdapter(jakarta.servlet.jsp.el.VariableResolver delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.jsp.el.VariableResolver getDelegate() {
        return delegate;
    }

    @Override
    public Object resolveVariable(String s) throws ELException {
        try {
            return delegate.resolveVariable(s);
        } catch (jakarta.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
