package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXExpressionEvaluatorAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXFunctionMapperAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.Expression;
import jakarta.servlet.jsp.el.ExpressionEvaluator;
import jakarta.servlet.jsp.el.FunctionMapper;
import jakarta.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaExpressionEvaluatorAdapter extends ExpressionEvaluator implements Adapted<javax.servlet.jsp.el.ExpressionEvaluator> {

    private final javax.servlet.jsp.el.ExpressionEvaluator delegate;

    public static ExpressionEvaluator from(javax.servlet.jsp.el.ExpressionEvaluator delegate) {
        if (delegate instanceof JavaXExpressionEvaluatorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaExpressionEvaluatorAdapter::new);
    }

    protected JakartaExpressionEvaluatorAdapter(javax.servlet.jsp.el.ExpressionEvaluator delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.jsp.el.ExpressionEvaluator getDelegate() {
        return delegate;
    }

    @Override
    public Expression parseExpression(String s, Class<?> aClass, FunctionMapper functionMapper) throws ELException {
        try {
            return JakartaExpressionAdapter.from(delegate.parseExpression(s, aClass, JavaXFunctionMapperAdapter.from(functionMapper)));
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }

    @Override
    public Object evaluate(String s,
                           Class<?> aClass,
                           VariableResolver variableResolver,
                           FunctionMapper functionMapper) throws ELException {
        try {
            return delegate.evaluate(s, aClass, JavaXVariableResolverAdapter.from(variableResolver), JavaXFunctionMapperAdapter.from(functionMapper));
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
