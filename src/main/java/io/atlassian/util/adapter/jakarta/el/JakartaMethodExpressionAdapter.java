package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXMethodExpressionAdapter;
import jakarta.el.ELContext;
import jakarta.el.MethodExpression;
import jakarta.el.MethodInfo;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaMethodExpressionAdapter extends MethodExpression implements Adapted<javax.el.MethodExpression> {

    private final javax.el.MethodExpression delegate;

    public static MethodExpression from(javax.el.MethodExpression delegate) {
        if (delegate instanceof JavaXMethodExpressionAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaMethodExpressionAdapter::new);
    }

    protected JakartaMethodExpressionAdapter(javax.el.MethodExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.MethodExpression getDelegate() {
        return delegate;
    }

    @Override
    public boolean isParametersProvided() {
        return delegate.isParametersProvided();
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context) {
        return JakartaMethodInfoAdapter.from(delegate.getMethodInfo(asJavaXJsp(context)));
    }

    @Override
    public Object invoke(ELContext context, Object[] params) {
        return delegate.invoke(asJavaXJsp(context), params);
    }

    @Override
    public String getExpressionString() {
        return delegate.getExpressionString();
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
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
