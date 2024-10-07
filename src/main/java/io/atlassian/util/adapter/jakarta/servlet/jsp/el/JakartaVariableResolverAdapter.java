package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.VariableResolver;

import static java.util.Objects.requireNonNull;

public class JakartaVariableResolverAdapter implements VariableResolver {

    private final javax.servlet.jsp.el.VariableResolver delegate;

    public JakartaVariableResolverAdapter(javax.servlet.jsp.el.VariableResolver delegate) {
        this.delegate = requireNonNull(delegate);
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
