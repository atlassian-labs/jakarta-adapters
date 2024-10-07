package io.atlassian.util.adapter.javax.el;

import javax.el.ELContext;
import javax.el.EvaluationListener;

import static io.atlassian.util.adapter.jakarta.JakartaJspAdapters.asJakartaJsp;
import static java.util.Objects.requireNonNull;

public class JavaXEvaluationListenerAdapter extends EvaluationListener {

    private final jakarta.el.EvaluationListener delegate;

    public JavaXEvaluationListenerAdapter(jakarta.el.EvaluationListener delegate) {
        this.delegate = requireNonNull(delegate);
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
