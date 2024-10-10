package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.Expression;
import jakarta.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaExpressionAdapter extends Expression {

    private final javax.servlet.jsp.el.Expression delegate;

    public JakartaExpressionAdapter(javax.servlet.jsp.el.Expression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Object evaluate(VariableResolver variableResolver) throws ELException {
        try {
            return delegate.evaluate(applyIfNonNull(variableResolver, JavaXVariableResolverAdapter::new));
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
