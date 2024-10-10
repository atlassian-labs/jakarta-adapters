package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ELContext;
import jakarta.el.EvaluationListener;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static java.util.Objects.requireNonNull;

public class JakartaEvaluationListenerAdapter extends EvaluationListener {

    private final javax.el.EvaluationListener delegate;

    public JakartaEvaluationListenerAdapter(javax.el.EvaluationListener delegate) {
        this.delegate = requireNonNull(delegate);
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
