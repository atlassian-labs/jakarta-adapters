package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaExpressionEvaluatorAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaFunctionMapperAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaVariableResolverAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.FunctionMapper;
import javax.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXExpressionEvaluatorAdapter extends ExpressionEvaluator implements Adapted<jakarta.servlet.jsp.el.ExpressionEvaluator> {

    private final jakarta.servlet.jsp.el.ExpressionEvaluator delegate;

    public static ExpressionEvaluator from(jakarta.servlet.jsp.el.ExpressionEvaluator delegate) {
        if (delegate instanceof JakartaExpressionEvaluatorAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXExpressionEvaluatorAdapter::new);
    }

    protected JavaXExpressionEvaluatorAdapter(jakarta.servlet.jsp.el.ExpressionEvaluator delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.jsp.el.ExpressionEvaluator getDelegate() {
        return delegate;
    }

    @Override
    public Expression parseExpression(String s, Class aClass, FunctionMapper functionMapper) throws ELException {
        try {
            return JavaXExpressionAdapter.from(delegate.parseExpression(s, aClass, JakartaFunctionMapperAdapter.from(functionMapper)));
        } catch (jakarta.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }

    @Override
    public Object evaluate(String s,
                           Class aClass,
                           VariableResolver variableResolver,
                           FunctionMapper functionMapper) throws ELException {
        try {
            return delegate.evaluate(s, aClass, JakartaVariableResolverAdapter.from(variableResolver), JakartaFunctionMapperAdapter.from(functionMapper));
        } catch (jakarta.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
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
