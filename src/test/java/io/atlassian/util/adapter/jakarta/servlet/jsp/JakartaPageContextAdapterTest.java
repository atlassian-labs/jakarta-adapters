package io.atlassian.util.adapter.jakarta.servlet.jsp;

import io.atlassian.util.adapter.javax.servlet.jsp.JavaXPageContextAdapter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.enumeration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JakartaPageContextAdapterTest {

    @Mock
    private PageContext original;

    private PageContext biAdapted;

    @BeforeEach
    public void setUp() throws Exception {
        biAdapted = new JakartaPageContextAdapter(new JavaXPageContextAdapter(original));
    }

    @Test
    public void initialize() throws Exception {
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
    public void release() {
        biAdapted.release();

        verify(original).release();
    }

    @Test
    public void getSession() {
        var session = mock(HttpSession.class);
        when(session.getId()).thenReturn("foo");
        when(original.getSession()).thenReturn(session);

        assertEquals("foo", biAdapted.getSession().getId());
    }

    @Test
    public void getPage() {
        when(original.getPage()).thenReturn("foo");

        assertEquals("foo", biAdapted.getPage());
    }

    @Test
    public void getRequest() {
        var request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("foo");
        when(original.getRequest()).thenReturn(request);

        assertEquals("foo", biAdapted.getRequest().getRemoteAddr());
    }

    @Test
    public void getResponse() {
        var response = mock(ServletResponse.class);
        when(response.getCharacterEncoding()).thenReturn("foo");
        when(original.getResponse()).thenReturn(response);

        assertEquals("foo", biAdapted.getResponse().getCharacterEncoding());
    }

    @Test
    public void getException() {
        var exception = new Exception();
        when(original.getException()).thenReturn(exception);

        assertEquals(exception, biAdapted.getException());
    }

    @Test
    public void getServletConfig() {
        var servlet = mock(ServletConfig.class);
        when(servlet.getServletName()).thenReturn("foo");
        when(original.getServletConfig()).thenReturn(servlet);

        assertEquals("foo", biAdapted.getServletConfig().getServletName());
    }

    @Test
    public void getServletContext() {
        var servlet = mock(ServletContext.class);
        when(servlet.getContextPath()).thenReturn("foo");
        when(original.getServletContext()).thenReturn(servlet);

        assertEquals("foo", biAdapted.getServletContext().getContextPath());
    }

    @Test
    public void forward() throws Exception {
        biAdapted.forward("foo");

        verify(original).forward("foo");
    }

    @Test
    public void include() throws Exception {
        biAdapted.include("foo");

        verify(original).include("foo");
    }

    @Test
    public void testInclude() throws Exception {
        biAdapted.include("foo", false);

        verify(original).include("foo", false);
    }

    @Test
    public void handlePageException() throws Exception {
        var exception = new Exception();
        biAdapted.handlePageException(exception);

        verify(original).handlePageException(exception);
    }

    @Test
    public void testHandlePageException() throws Exception {
        Throwable exception = new Exception();
        biAdapted.handlePageException(exception);

        verify(original).handlePageException(exception);

    }

    @Test
    public void setAttribute() {
        biAdapted.setAttribute("foo", "bar");

        verify(original).setAttribute("foo", "bar");
    }

    @Test
    public void testSetAttribute() {
        biAdapted.setAttribute("foo", "bar", 0);

        verify(original).setAttribute("foo", "bar", 0);
    }

    @Test
    public void getAttribute() {
        when(original.getAttribute("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.getAttribute("foo"));
    }

    @Test
    public void testGetAttribute() {
        when(original.getAttribute("foo", 0)).thenReturn("bar");

        assertEquals("bar", biAdapted.getAttribute("foo", 0));
    }

    @Test
    public void findAttribute() {
        when(original.findAttribute("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.findAttribute("foo"));
    }

    @Test
    public void removeAttribute() {
        biAdapted.removeAttribute("foo");

        verify(original).removeAttribute("foo");
    }

    @Test
    public void testRemoveAttribute() {
        biAdapted.removeAttribute("foo", 0);

        verify(original).removeAttribute("foo", 0);
    }

    @Test
    public void getAttributesScope() {
        when(original.getAttributesScope("foo")).thenReturn(0);

        assertEquals(0, biAdapted.getAttributesScope("foo"));
    }

    @Test
    public void getAttributeNamesInScope() {
        when(original.getAttributeNamesInScope(0)).thenReturn(enumeration(List.of("foo")));

        assertEquals("foo", biAdapted.getAttributeNamesInScope(0).nextElement());
    }

    @Test
    public void getOut() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getOut());
    }

    @Test
    public void getExpressionEvaluator() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getExpressionEvaluator());
    }

    @Test
    public void getVariableResolver() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getVariableResolver());
    }

    @Test
    public void getELContext() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getELContext());
    }
}
