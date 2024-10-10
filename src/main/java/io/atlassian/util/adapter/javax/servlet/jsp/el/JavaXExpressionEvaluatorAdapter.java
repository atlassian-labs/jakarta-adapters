package io.atlassian.util.adapter.javax.servlet.jsp.el;

import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaFunctionMapperAdapter;
import io.atlassian.util.adapter.jakarta.servlet.jsp.el.JakartaVariableResolverAdapter;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.FunctionMapper;
import javax.servlet.jsp.el.VariableResolver;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXExpressionEvaluatorAdapter extends ExpressionEvaluator {

    private final jakarta.servlet.jsp.el.ExpressionEvaluator delegate;

    public JavaXExpressionEvaluatorAdapter(jakarta.servlet.jsp.el.ExpressionEvaluator delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public Expression parseExpression(String s, Class aClass, FunctionMapper functionMapper) throws ELException {
        try {
            return applyIfNonNull(delegate.parseExpression(s, aClass, applyIfNonNull(functionMapper, JakartaFunctionMapperAdapter::new)), JavaXExpressionAdapter::new);
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
            return delegate.evaluate(s, aClass, applyIfNonNull(variableResolver, JakartaVariableResolverAdapter::new), applyIfNonNull(functionMapper, JakartaFunctionMapperAdapter::new));
        } catch (jakarta.servlet.jsp.el.ELException e) {
            throw new ELException(e);
        }
    }
}
