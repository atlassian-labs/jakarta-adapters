package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ELContext;
import jakarta.el.ValueExpression;
import jakarta.el.ValueReference;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaValueExpressionAdapter extends ValueExpression {

    private final javax.el.ValueExpression delegate;

    public JakartaValueExpressionAdapter(javax.el.ValueExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ValueReference getValueReference(ELContext context) {
        return applyIfNonNull(delegate.getValueReference(asJavaXJsp(context)), JakartaValueReferenceAdapter::new);
    }

    @Override
    public Object getValue(ELContext elContext) {
        return delegate.getValue(asJavaXJsp(elContext));
    }

    @Override
    public void setValue(ELContext elContext, Object o) {
        delegate.setValue(asJavaXJsp(elContext), o);
    }

    @Override
    public boolean isReadOnly(ELContext elContext) {
        return delegate.isReadOnly(asJavaXJsp(elContext));
    }

    @Override
    public Class<?> getType(ELContext elContext) {
        return delegate.getType(asJavaXJsp(elContext));
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
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean isLiteralText() {
        return delegate.isLiteralText();
    }
}
