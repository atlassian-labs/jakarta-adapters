package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.jakarta.el.JakartaValueExpressionAdapter;

import javax.el.ValueExpression;
import javax.el.VariableMapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXVariableMapperAdapter extends VariableMapper {

    private final jakarta.el.VariableMapper delegate;

    public JavaXVariableMapperAdapter(jakarta.el.VariableMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ValueExpression resolveVariable(String s) {
        return applyIfNonNull(delegate.resolveVariable(s), JavaXValueExpressionAdapter::new);
    }

    @Override
    public ValueExpression setVariable(String s, ValueExpression valueExpression) {
        return applyIfNonNull(delegate.setVariable(s, applyIfNonNull(valueExpression, JakartaValueExpressionAdapter::new)), JavaXValueExpressionAdapter::new);
    }
}
