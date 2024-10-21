package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXPageContextAdapter;
import jakarta.el.ELContext;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.el.ExpressionEvaluator;
import jakarta.servlet.jsp.el.VariableResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.enumeration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaPageContextAdapterTest {

    @Mock
    private PageContext original;

    private PageContext biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = new JakartaPageContextAdapter(JavaXPageContextAdapter.from(original));
    }

    @Test
    void initialize() throws Exception {
        var servlet = mock(Servlet.class);
        var servletRequest = mock(ServletRequest.class);
        var servletResponse = mock(ServletResponse.class);
        when(servlet.getServletInfo()).thenReturn("foo");
        when(servletRequest.getRemoteAddr()).thenReturn("foo");
        when(servletResponse.getCharacterEncoding()).thenReturn("foo");
        biAdapted.initialize(servlet, servletRequest, servletResponse, "foo", false, 0, false);

        verify(original).initialize(
                argThat(s -> s.getServletInfo().equals("foo")),
                argThat(r -> r.getRemoteAddr().equals("foo")),
                argThat(r -> r.getCharacterEncoding().equals("foo")),
                eq("foo"), eq(false), eq(0), eq(false));
    }

    @Test
    void release() {
        biAdapted.release();

        verify(original).release();
    }

    @Test
    void getSession() {
        var session = mock(HttpSession.class);
        when(session.getId()).thenReturn("foo");
        when(original.getSession()).thenReturn(session);

        assertEquals("foo", biAdapted.getSession().getId());
    }

    @Test
    void getPage() {
        when(original.getPage()).thenReturn("foo");

        assertEquals("foo", biAdapted.getPage());
    }

    @Test
    void getRequest() {
        var request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("foo");
        when(original.getRequest()).thenReturn(request);

        assertEquals("foo", biAdapted.getRequest().getRemoteAddr());
    }

    @Test
    void getResponse() {
        var response = mock(ServletResponse.class);
        when(response.getCharacterEncoding()).thenReturn("foo");
        when(original.getResponse()).thenReturn(response);

        assertEquals("foo", biAdapted.getResponse().getCharacterEncoding());
    }

    @Test
    void getException() {
        var exception = new Exception();
        when(original.getException()).thenReturn(exception);

        assertEquals(exception, biAdapted.getException());
    }

    @Test
    void getServletConfig() {
        var servlet = mock(ServletConfig.class);
        when(servlet.getServletName()).thenReturn("foo");
        when(original.getServletConfig()).thenReturn(servlet);

        assertEquals("foo", biAdapted.getServletConfig().getServletName());
    }

    @Test
    void getServletContext() {
        var servlet = mock(ServletContext.class);
        when(servlet.getContextPath()).thenReturn("foo");
        when(original.getServletContext()).thenReturn(servlet);

        assertEquals("foo", biAdapted.getServletContext().getContextPath());
    }

    @Test
    void forward() throws Exception {
        biAdapted.forward("foo");

        verify(original).forward("foo");
    }

    @Test
    void include() throws Exception {
        biAdapted.include("foo");

        verify(original).include("foo");
    }

    @Test
    void testInclude() throws Exception {
        biAdapted.include("foo", false);

        verify(original).include("foo", false);
    }

    @Test
    void handlePageException() throws Exception {
        var exception = new Exception();
        biAdapted.handlePageException(exception);

        verify(original).handlePageException(exception);
    }

    @Test
    void testHandlePageException() throws Exception {
        Throwable exception = new Exception();
        biAdapted.handlePageException(exception);

        verify(original).handlePageException(exception);

    }

    @Test
    void setAttribute() {
        biAdapted.setAttribute("foo", "bar");

        verify(original).setAttribute("foo", "bar");
    }

    @Test
    void testSetAttribute() {
        biAdapted.setAttribute("foo", "bar", 0);

        verify(original).setAttribute("foo", "bar", 0);
    }

    @Test
    void getAttribute() {
        when(original.getAttribute("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.getAttribute("foo"));
    }

    @Test
    void testGetAttribute() {
        when(original.getAttribute("foo", 0)).thenReturn("bar");

        assertEquals("bar", biAdapted.getAttribute("foo", 0));
    }

    @Test
    void findAttribute() {
        when(original.findAttribute("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.findAttribute("foo"));
    }

    @Test
    void removeAttribute() {
        biAdapted.removeAttribute("foo");

        verify(original).removeAttribute("foo");
    }

    @Test
    void testRemoveAttribute() {
        biAdapted.removeAttribute("foo", 0);

        verify(original).removeAttribute("foo", 0);
    }

    @Test
    void getAttributesScope() {
        when(original.getAttributesScope("foo")).thenReturn(0);

        assertEquals(0, biAdapted.getAttributesScope("foo"));
    }

    @Test
    void getAttributeNamesInScope() {
        when(original.getAttributeNamesInScope(0)).thenReturn(enumeration(List.of("foo")));

        assertEquals("foo", biAdapted.getAttributeNamesInScope(0).nextElement());
    }

    @Test
    void getOut() throws Exception {
        var writer = mock(JspWriter.class);
        when(original.getOut()).thenReturn(writer);

        biAdapted.getOut().newLine();

        verify(writer).newLine();
    }

    @Test
    void getExpressionEvaluator() throws Exception {
        var exprEvaluator = mock(ExpressionEvaluator.class);
        when(exprEvaluator.evaluate("foo", String.class, null, null)).thenReturn("bar");
        when(original.getExpressionEvaluator()).thenReturn(exprEvaluator);

        assertEquals("bar", biAdapted.getExpressionEvaluator().evaluate("foo", String.class, null, null));
    }

    @Test
    void getVariableResolver() throws Exception {
        var resolver = mock(VariableResolver.class);
        when(resolver.resolveVariable("foo")).thenReturn("bar");
        when(original.getVariableResolver()).thenReturn(resolver);

        assertEquals("bar", biAdapted.getVariableResolver().resolveVariable("foo"));
    }

    @Test
    void getELContext() {
        var context = mock(ELContext.class);
        when(context.isPropertyResolved()).thenReturn(true);
        when(original.getELContext()).thenReturn(context);

        assertTrue(biAdapted.getELContext().isPropertyResolved());
    }
}
