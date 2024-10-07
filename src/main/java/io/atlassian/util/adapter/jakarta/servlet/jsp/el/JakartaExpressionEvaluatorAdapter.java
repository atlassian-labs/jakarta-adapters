package io.atlassian.util.adapter.jakarta.servlet.jsp.el;

import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXFunctionMapperAdapter;
import io.atlassian.util.adapter.javax.servlet.jsp.el.JavaXVariableResolverAdapter;
import jakarta.servlet.jsp.el.ELException;
import jakarta.servlet.jsp.el.Expression;
import jakarta.servlet.jsp.el.ExpressionEvaluator;
import jakarta.servlet.jsp.el.FunctionMapper;
import jakarta.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaExpressionEvaluatorAdapter extends ExpressionEvaluator {

    private final javax.servlet.jsp.el.ExpressionEvaluator delegate;

    public JakartaExpressionEvaluatorAdapter(javax.servlet.jsp.el.ExpressionEvaluator delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Expression parseExpression(String s, Class<?> aClass, FunctionMapper functionMapper) throws ELException {
        try {
            return applyIfNonNull(delegate.parseExpression(s, aClass, applyIfNonNull(functionMapper, JavaXFunctionMapperAdapter::new)), JakartaExpressionAdapter::new);
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
            return delegate.evaluate(s, aClass, applyIfNonNull(variableResolver, JavaXVariableResolverAdapter::new), applyIfNonNull(functionMapper, JavaXFunctionMapperAdapter::new));
        } catch (javax.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
