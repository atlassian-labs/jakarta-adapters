package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXEvaluationListenerAdapter;
import jakarta.el.ELContext;
import jakarta.el.ELResolver;
import jakarta.el.EvaluationListener;
import jakarta.el.FunctionMapper;
import jakarta.el.ImportHandler;
import jakarta.el.VariableMapper;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static io.atlassian.util.adapter.util.WrapperUtil.transformListIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaELContextAdapter extends ELContext {

    private final javax.el.ELContext delegate;

    public JakartaELContextAdapter(javax.el.ELContext delegate) {
        this.delegate = requireNonNull(delegate);
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
    public void putContext(Class<?> key, Object contextObject) {
        delegate.putContext(key, contextObject);
    }

    @Override
    public Object getContext(Class<?> key) {
        return delegate.getContext(key);
    }

    @Override
    public ELResolver getELResolver() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ImportHandler getImportHandler() {
        return applyIfNonNull(delegate.getImportHandler(), JakartaImportHandlerAdapter::new);
    }

    @Override
    public FunctionMapper getFunctionMapper() {
        return applyIfNonNull(delegate.getFunctionMapper(), JakartaFunctionMapperAdapter::new);
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
        return applyIfNonNull(delegate.getVariableMapper(), JakartaVariableMapperAdapter::new);
    }

    @Override
    public void addEvaluationListener(EvaluationListener listener) {
        delegate.addEvaluationListener(applyIfNonNull(listener, JavaXEvaluationListenerAdapter::new));
    }

    @Override
    public List<EvaluationListener> getEvaluationListeners() {
        return transformListIfNonNull(delegate.getEvaluationListeners(), JakartaEvaluationListenerAdapter::new);
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
}
