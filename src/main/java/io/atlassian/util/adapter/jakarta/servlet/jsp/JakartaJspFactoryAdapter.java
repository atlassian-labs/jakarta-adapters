package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXPageContextAdapter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspApplicationContext;
import jakarta.servlet.jsp.JspEngineInfo;
import jakarta.servlet.jsp.JspFactory;
import jakarta.servlet.jsp.PageContext;

import static io.atlassian.util.adapter.javax.JavaXAdapters.asJavaX;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaJspFactoryAdapter extends JspFactory {
    private final javax.servlet.jsp.JspFactory delegate;

    public JakartaJspFactoryAdapter(javax.servlet.jsp.JspFactory delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public PageContext getPageContext(Servlet servlet,
                                      ServletRequest servletRequest,
                                      ServletResponse servletResponse,
                                      String s,
                                      boolean b,
                                      int i,
                                      boolean b1) {
        return applyIfNonNull(
                delegate.getPageContext(asJavaX(servlet), asJavaX(servletRequest), asJavaX(servletResponse), s, b, i, b1),
                JakartaPageContextAdapter::new);
    }

    @Override
    public void releasePageContext(PageContext pageContext) {
        delegate.releasePageContext(applyIfNonNull(pageContext, JavaXPageContextAdapter::new));
    }

    @Override
    public JspEngineInfo getEngineInfo() {
        return applyIfNonNull(delegate.getEngineInfo(), JakartaJspEngineInfoAdapter::new);
    }

    @Override
    public JspApplicationContext getJspApplicationContext(ServletContext servletContext) {
        return applyIfNonNull(delegate.getJspApplicationContext(asJavaX(servletContext)), JakartaJspApplicationContextAdapter::new);
    }
}
