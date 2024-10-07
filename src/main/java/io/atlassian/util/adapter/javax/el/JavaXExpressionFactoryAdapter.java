package io.atlassian.util.adapter.javax.el;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import java.lang.reflect.Method;
import java.util.Map;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXExpressionFactoryAdapter extends ExpressionFactory {

    private final jakarta.el.ExpressionFactory delegate;

    public JavaXExpressionFactoryAdapter(jakarta.el.ExpressionFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public ELResolver getStreamELResolver() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Map<String, Method> getInitFunctionMap() {
        return delegate.getInitFunctionMap();
    }

    @Override
    public ValueExpression createValueExpression(ELContext context, String expression, Class<?> expectedType) {
        return applyIfNonNull(delegate.createValueExpression(asJakartaJsp(context), expression, expectedType), JavaXValueExpressionAdapter::new);
    }

    @Override
    public ValueExpression createValueExpression(Object instance, Class<?> expectedType) {
        return applyIfNonNull(delegate.createValueExpression(instance, expectedType), JavaXValueExpressionAdapter::new);
    }

    @Override
    public MethodExpression createMethodExpression(ELContext context,
                                                   String expression,
                                                   Class<?> expectedReturnType,
                                                   Class<?>[] expectedParamTypes) {
        return applyIfNonNull(delegate.createMethodExpression(asJakartaJsp(context), expression, expectedReturnType, expectedParamTypes), JavaXMethodExpressionAdapter::new);
    }

    @Override
    public Object coerceToType(Object obj, Class<?> targetType) {
        return delegate.coerceToType(obj, targetType);
    }
}
