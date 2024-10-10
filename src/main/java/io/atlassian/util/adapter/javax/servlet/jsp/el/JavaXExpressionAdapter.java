package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaVariableResolverAdapter;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import javax.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXExpressionAdapter extends Expression {

    private final jakarta.servlet.jsp.el.Expression delegate;

    public JavaXExpressionAdapter(jakarta.servlet.jsp.el.Expression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Object evaluate(VariableResolver variableResolver) throws ELException {
        try {
            return delegate.evaluate(applyIfNonNull(variableResolver, JakartaVariableResolverAdapter::new));
        } catch (jakarta.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
