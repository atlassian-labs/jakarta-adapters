package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.el.JakartaELContextAdapter;
import io.atlassian.util.adapter.jakarta.el.JakartaEvaluationListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.EvaluationListener;
import javax.el.FunctionMapper;
import javax.el.ImportHandler;
import javax.el.VariableMapper;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformListIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXELContextAdapter extends ELContext implements Adapted<jakarta.el.ELContext> {

    private final jakarta.el.ELContext delegate;

    public static ELContext from(jakarta.el.ELContext delegate) {
        if (delegate instanceof JakartaELContextAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXELContextAdapter::new);
    }

    protected JavaXELContextAdapter(jakarta.el.ELContext delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.el.ELContext getDelegate() {
        return delegate;
    }

    @Override
    public void setPropertyResolved(boolean resolved) {
        delegate.setPropertyResolved(resolved);
    }

    @Override
    public void setPropertyResolved(Object base, Object property) {
        delegate.setPropertyResolved(base, property);
    }

    @Override
    public boolean isPropertyResolved() {
        return delegate.isPropertyResolved();
    }

    @Override
    public void putContext(Class key, Object contextObject) {
        delegate.putContext(key, contextObject);
    }

    @Override
    public Object getContext(Class key) {
        return delegate.getContext(key);
    }

    @Override
    public ELResolver getELResolver() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ImportHandler getImportHandler() {
        return JavaXImportHandlerAdapter.from(delegate.getImportHandler());
    }

    @Override
    public FunctionMapper getFunctionMapper() {
        return JavaXFunctionMapperAdapter.from(delegate.getFunctionMapper());
    }

    @Override
    public Locale getLocale() {
        return delegate.getLocale();
    }

    @Override
    public void setLocale(Locale locale) {
        delegate.setLocale(locale);
    }

    @Override
    public VariableMapper getVariableMapper() {
        return JavaXVariableMapperAdapter.from(delegate.getVariableMapper());
    }

    @Override
    public void addEvaluationListener(EvaluationListener listener) {
        delegate.addEvaluationListener(JakartaEvaluationListenerAdapter.from(listener));
    }

    @Override
    public List<EvaluationListener> getEvaluationListeners() {
        return transformListIfNonNull(delegate.getEvaluationListeners(), JavaXEvaluationListenerAdapter::from);
    }

    @Override
    public void notifyBeforeEvaluation(String expr) {
        delegate.notifyBeforeEvaluation(expr);
    }

    @Override
    public void notifyAfterEvaluation(String expr) {
        delegate.notifyAfterEvaluation(expr);
    }

    @Override
    public void notifyPropertyResolved(Object base, Object property) {
        delegate.notifyPropertyResolved(base, property);
    }

    @Override
    public boolean isLambdaArgument(String arg) {
        return delegate.isLambdaArgument(arg);
    }

    @Override
    public Object getLambdaArgument(String arg) {
        return delegate.getLambdaArgument(arg);
    }

    @Override
    public void enterLambdaScope(Map<String, Object> args) {
        delegate.enterLambdaScope(args);
    }

    @Override
    public void exitLambdaScope() {
        delegate.exitLambdaScope();
    }

    @Override
    public Object convertToType(Object obj, Class<?> targetType) {
        return delegate.convertToType(obj, targetType);
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
