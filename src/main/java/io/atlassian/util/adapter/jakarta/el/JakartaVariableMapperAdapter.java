package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXValueExpressionAdapter;
import jakarta.el.ValueExpression;
import jakarta.el.VariableMapper;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaVariableMapperAdapter extends VariableMapper {

    private final javax.el.VariableMapper delegate;

    public JakartaVariableMapperAdapter(javax.el.VariableMapper delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ValueExpression resolveVariable(String s) {
        return applyIfNonNull(delegate.resolveVariable(s), JakartaValueExpressionAdapter::new);
    }

    @Override
    public ValueExpression setVariable(String s, ValueExpression valueExpression) {
        return applyIfNonNull(delegate.setVariable(s, applyIfNonNull(valueExpression, JavaXValueExpressionAdapter::new)), JakartaValueExpressionAdapter::new);
    }
}
