package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ELContext;
import jakarta.el.MethodExpression;
import jakarta.el.MethodInfo;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaMethodExpressionAdapter extends MethodExpression {

    private final javax.el.MethodExpression delegate;

    public JakartaMethodExpressionAdapter(javax.el.MethodExpression delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public boolean isParametersProvided() {
        return delegate.isParametersProvided();
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context) {
        return applyIfNonNull(delegate.getMethodInfo(asJavaXJsp(context)), JakartaMethodInfoAdapter::new);
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
