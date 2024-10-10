package io.atlassian.util.adapter.javax.el;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.el.ValueReference;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXValueExpressionAdapter extends ValueExpression {

    private final jakarta.el.ValueExpression delegate;

    public JavaXValueExpressionAdapter(jakarta.el.ValueExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ValueReference getValueReference(ELContext context) {
        return applyIfNonNull(delegate.getValueReference(asJakartaJsp(context)), JavaXValueReferenceAdapter::new);
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
