package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaValueExpressionAdapter;
import io.atlassian.util.adapter.jakarta.el.JakartaVariableMapperAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ValueExpression;
import javax.el.VariableMapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXVariableMapperAdapter extends VariableMapper implements Adapted<jakarta.el.VariableMapper> {

    private final jakarta.el.VariableMapper delegate;

    public static VariableMapper from(jakarta.el.VariableMapper delegate) {
        if (delegate instanceof JakartaVariableMapperAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXVariableMapperAdapter::new);
    }

    public JavaXVariableMapperAdapter(jakarta.el.VariableMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.VariableMapper getDelegate() {
        return delegate;
    }

    @Override
    public ValueExpression resolveVariable(String s) {
        return JavaXValueExpressionAdapter.from(delegate.resolveVariable(s));
    }

    @Override
    public ValueExpression setVariable(String s, ValueExpression valueExpression) {
        return JavaXValueExpressionAdapter.from(delegate.setVariable(s, JakartaValueExpressionAdapter.from(valueExpression)));
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
