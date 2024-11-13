package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaExpressionFactoryAdapter;

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

public class JavaXExpressionFactoryAdapter extends ExpressionFactory implements Adapted<jakarta.el.ExpressionFactory> {

    private final jakarta.el.ExpressionFactory delegate;

    public static ExpressionFactory from(jakarta.el.ExpressionFactory delegate) {
        if (delegate instanceof JakartaExpressionFactoryAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXExpressionFactoryAdapter::new);
    }

    protected JavaXExpressionFactoryAdapter(jakarta.el.ExpressionFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.ExpressionFactory getDelegate() {
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
        return JavaXValueExpressionAdapter.from(delegate.createValueExpression(asJakartaJsp(context), expression, expectedType));
    }

    @Override
    public ValueExpression createValueExpression(Object instance, Class<?> expectedType) {
        return JavaXValueExpressionAdapter.from(delegate.createValueExpression(instance, expectedType));
    }

    @Override
    public MethodExpression createMethodExpression(ELContext context,
                                                   String expression,
                                                   Class<?> expectedReturnType,
                                                   Class<?>[] expectedParamTypes) {
        return JavaXMethodExpressionAdapter.from(delegate.createMethodExpression(asJakartaJsp(context), expression, expectedReturnType, expectedParamTypes));
    }

    @Override
    public Object coerceToType(Object obj, Class<?> targetType) {
        return delegate.coerceToType(obj, targetType);
    }
}
