package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaExpressionAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaVariableResolverAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import javax.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXExpressionAdapter extends Expression implements Adapted<jakarta.servlet.jsp.el.Expression> {

    private final jakarta.servlet.jsp.el.Expression delegate;

    public static Expression from(jakarta.servlet.jsp.el.Expression delegate) {
        if (delegate instanceof JakartaExpressionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXExpressionAdapter::new);
    }

    protected JavaXExpressionAdapter(jakarta.servlet.jsp.el.Expression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.jsp.el.Expression getDelegate() {
        return delegate;
    }

    @Override
    public Object evaluate(VariableResolver variableResolver) throws ELException {
        try {
            return delegate.evaluate(JakartaVariableResolverAdapter.from(variableResolver));
        } catch (jakarta.servlet.jsp.el.ELException e) {
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
