package io.atlassian.util.adapter.jakarta.servlet.jsp;

import jakarta.servlet.jsp.JspEngineInfo;

import static java.util.Objects.requireNonNull;

public class JakartaJspEngineInfoAdapter extends JspEngineInfo {

    private final javax.servlet.jsp.JspEngineInfo delegate;

    public JakartaJspEngineInfoAdapter(javax.servlet.jsp.JspEngineInfo delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getSpecificationVersion() {
        return delegate.getSpecificationVersion();
    }
}
