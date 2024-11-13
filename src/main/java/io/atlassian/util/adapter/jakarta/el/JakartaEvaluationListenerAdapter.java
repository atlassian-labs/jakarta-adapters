package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.el.JavaXEvaluationListenerAdapter;
import jakarta.el.ELContext;
import jakarta.el.EvaluationListener;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaEvaluationListenerAdapter extends EvaluationListener implements Adapted<javax.el.EvaluationListener> {

    private final javax.el.EvaluationListener delegate;

    public static EvaluationListener from(javax.el.EvaluationListener delegate) {
        if (delegate instanceof JavaXEvaluationListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaEvaluationListenerAdapter::new);
    }

    protected JakartaEvaluationListenerAdapter(javax.el.EvaluationListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.el.EvaluationListener getDelegate() {
        return delegate;
    }

    @Override
    public void beforeEvaluation(ELContext context, String expression) {
        delegate.beforeEvaluation(asJavaXJsp(context), expression);
    }

    @Override
    public void afterEvaluation(ELContext context, String expression) {
        delegate.afterEvaluation(asJavaXJsp(context), expression);
    }

    @Override
    public void propertyResolved(ELContext context, Object base, Object property) {
        delegate.propertyResolved(asJavaXJsp(context), base, property);
    }
}
