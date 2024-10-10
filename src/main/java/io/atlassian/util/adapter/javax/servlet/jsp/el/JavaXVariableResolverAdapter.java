package io.atlassian.util.adapter.javax.servlet.jsp.el;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;

import static java.util.Objects.requireNonNull;

public class JavaXVariableResolverAdapter implements VariableResolver {

    private final jakarta.servlet.jsp.el.VariableResolver delegate;

    public JavaXVariableResolverAdapter(jakarta.servlet.jsp.el.VariableResolver delegate) {
        this.delegate = requireNonNull(delegate);
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
