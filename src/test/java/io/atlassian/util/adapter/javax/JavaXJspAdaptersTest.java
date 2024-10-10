package io.atlassian.util.adapter.javax;

import jakarta.el.ELContext;
import jakarta.servlet.jsp.JspFactory;
import org.junit.jupiter.api.Test;

import static io.atlassian.util.adapter.javax.JavaXJspAdapters.asJavaXJsp;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JavaXJspAdaptersTest {

    @Test
    void jspFactory() {
        var delegate = mock(JspFactory.class);
        var pageContext = mock(javax.servlet.jsp.PageContext.class);
        when(pageContext.getPage()).thenReturn("foo");

        var proxy = asJavaXJsp(delegate);
        proxy.releasePageContext(pageContext);

        verify(delegate).releasePageContext(argThat(pC -> pC.getPage().equals("foo")));
    }

    @Test
    void elContext() {
        var delegate = mock(ELContext.class);
        when(delegate.isPropertyResolved()).thenReturn(true);

        assertTrue(asJavaXJsp(delegate).isPropertyResolved());
    }
}
