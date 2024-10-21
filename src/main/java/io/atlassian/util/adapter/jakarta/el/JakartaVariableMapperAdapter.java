package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXValueExpressionAdapter;
import io.atlassian.util.adapter.javax.el.JavaXVariableMapperAdapter;
import jakarta.el.ValueExpression;
import jakarta.el.VariableMapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaVariableMapperAdapter extends VariableMapper implements Adapted<javax.el.VariableMapper> {

    private final javax.el.VariableMapper delegate;

    public static VariableMapper from(javax.el.VariableMapper delegate) {
        if (delegate instanceof JavaXVariableMapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaVariableMapperAdapter::new);
    }

    JakartaVariableMapperAdapter(javax.el.VariableMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.VariableMapper getDelegate() {
        return delegate;
    }

    @Override
    public ValueExpression resolveVariable(String s) {
        return JakartaValueExpressionAdapter.from(delegate.resolveVariable(s));
    }

    @Override
    public ValueExpression setVariable(String s, ValueExpression valueExpression) {
        return JakartaValueExpressionAdapter.from(delegate.setVariable(s, JavaXValueExpressionAdapter.from(valueExpression)));
    }
}
