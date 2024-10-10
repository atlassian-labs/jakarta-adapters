package io.atlassian.util.adapter.javax.servlet.jsp;

import javax.servlet.jsp.JspEngineInfo;

import static java.util.Objects.requireNonNull;

public class JavaXJspEngineInfoAdapter extends JspEngineInfo {

    private final jakarta.servlet.jsp.JspEngineInfo delegate;

    public JavaXJspEngineInfoAdapter(jakarta.servlet.jsp.JspEngineInfo delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public String getSpecificationVersion() {
        return delegate.getSpecificationVersion();
    }
}
