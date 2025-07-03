package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaValueExpressionAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.el.ValueReference;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXValueExpressionAdapter extends ValueExpression implements Adapted<jakarta.el.ValueExpression> {

    private final jakarta.el.ValueExpression delegate;

    public static ValueExpression from(jakarta.el.ValueExpression delegate) {
        if (delegate instanceof JakartaValueExpressionAdapter) {
            JakartaValueExpressionAdapter castDelegate = (JakartaValueExpressionAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXValueExpressionAdapter::new);
    }

    protected JavaXValueExpressionAdapter(jakarta.el.ValueExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.ValueExpression getDelegate() {
        return delegate;
    }

    @Override
    public ValueReference getValueReference(ELContext context) {
        return JavaXValueReferenceAdapter.from(delegate.getValueReference(asJakartaJsp(context)));
    }

    @Override
    public Object getValue(ELContext elContext) {
        return delegate.getValue(asJakartaJsp(elContext));
    }

    @Override
    public void setValue(ELContext elContext, Object o) {
        delegate.setValue(asJakartaJsp(elContext), o);
    }

    @Override
    public boolean isReadOnly(ELContext elContext) {
        return delegate.isReadOnly(asJakartaJsp(elContext));
    }

    @Override
    public Class<?> getType(ELContext elContext) {
        return delegate.getType(asJakartaJsp(elContext));
    }

    @Override
    public Class<?> getExpectedType() {
        return delegate.getExpectedType();
    }

    @Override
    public String getExpressionString() {
        return delegate.getExpressionString();
    }

    @Override
    public boolean isLiteralText() {
        return delegate.isLiteralText();
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
