package io.atlassian.util.adapter.jakarta.el;

import jakarta.el.ValueReference;

public class JakartaValueReferenceAdapter extends ValueReference {

    public JakartaValueReferenceAdapter(javax.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
    }
}
