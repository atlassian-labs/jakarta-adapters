package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXExpressionAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.Expression;
import jakarta.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaExpressionAdapter extends Expression implements Adapted<javax.servlet.jsp.el.Expression> {

    private final javax.servlet.jsp.el.Expression delegate;

    public static Expression from(javax.servlet.jsp.el.Expression delegate) {
        if (delegate instanceof JavaXExpressionAdapter) {
            JavaXExpressionAdapter castDelegate = (JavaXExpressionAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaExpressionAdapter::new);
    }

    protected JakartaExpressionAdapter(javax.servlet.jsp.el.Expression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.el.Expression getDelegate() {
        return delegate;
    }

    @Override
    public Object evaluate(VariableResolver variableResolver) throws ELException {
        try {
            return delegate.evaluate(JavaXVariableResolverAdapter.from(variableResolver));
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
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
