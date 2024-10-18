package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXExpressionFactoryAdapter;
import jakarta.el.ELContext;
import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.el.MethodExpression;
import jakarta.el.ValueExpression;

import java.lang.reflect.Method;
import java.util.Map;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaExpressionFactoryAdapter extends ExpressionFactory {

    private final javax.el.ExpressionFactory delegate;

    public static ExpressionFactory from(javax.el.ExpressionFactory delegate) {
        if (delegate instanceof JavaXExpressionFactoryAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaExpressionFactoryAdapter::new);
    }

    JakartaExpressionFactoryAdapter(javax.el.ExpressionFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.el.ExpressionFactory getDelegate() {
        return delegate;
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
        return JakartaValueExpressionAdapter.from(delegate.createValueExpression(asJavaXJsp(context), expression, expectedType));
    }

    @Override
    public ValueExpression createValueExpression(Object instance, Class<?> expectedType) {
        return JakartaValueExpressionAdapter.from(delegate.createValueExpression(instance, expectedType));
    }

    @Override
    public MethodExpression createMethodExpression(ELContext context,
                                                   String expression,
                                                   Class<?> expectedReturnType,
                                                   Class<?>[] expectedParamTypes) {
        return JakartaMethodExpressionAdapter.from(delegate.createMethodExpression(asJavaXJsp(context), expression, expectedReturnType, expectedParamTypes));
    }

    @Override
    public Object coerceToType(Object obj, Class<?> targetType) {
        return delegate.coerceToType(obj, targetType);
    }
}
