package io.atlassian.util.adapter.javax.el;

import io.atlassian.util.adapter.jakarta.el.JakartaEvaluationListenerAdapter;

import javax.el.ELContext;
import javax.el.EvaluationListener;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXEvaluationListenerAdapter extends EvaluationListener {

    private final jakarta.el.EvaluationListener delegate;

    public static EvaluationListener from(jakarta.el.EvaluationListener delegate) {
        if (delegate instanceof JakartaEvaluationListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXEvaluationListenerAdapter::new);
    }

    JavaXEvaluationListenerAdapter(jakarta.el.EvaluationListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.el.EvaluationListener getDelegate() {
        return delegate;
    }

    @Override
    public void beforeEvaluation(ELContext context, String expression) {
        delegate.beforeEvaluation(asJakartaJsp(context), expression);
    }

    @Override
    public void afterEvaluation(ELContext context, String expression) {
        delegate.afterEvaluation(asJakartaJsp(context), expression);
    }

    @Override
    public void propertyResolved(ELContext context, Object base, Object property) {
        delegate.propertyResolved(asJakartaJsp(context), base, property);
    }
}
