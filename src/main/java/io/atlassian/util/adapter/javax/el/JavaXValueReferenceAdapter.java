package io.atlassian.util.adapter.javax.el;

import javax.el.ValueReference;

public class JavaXValueReferenceAdapter extends ValueReference {

    public JavaXValueReferenceAdapter(jakarta.el.ValueReference delegate) {
        super(delegate.getBase(), delegate.getProperty());
    }
}
