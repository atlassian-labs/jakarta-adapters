package io.atlassian.util.adapter.jakarta.el;

import io.atlassian.util.adapter.javax.el.JavaXELContextAdapter;
import jakarta.el.ELContext;
import jakarta.el.EvaluationListener;
import jakarta.el.ImportHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JakartaELContextAdapterTest {

    @Mock
    private ELContext original;

    private ELContext biAdapted;

    @BeforeEach
    void setUp() throws Exception {
        biAdapted = new JakartaELContextAdapter(JavaXELContextAdapter.from(original));
    }

    @Test
    void setPropertyResolved() {
        biAdapted.setPropertyResolved(true);

        verify(original).setPropertyResolved(true);
    }

    @Test
    void testSetPropertyResolved() {
        biAdapted.setPropertyResolved("foo", "bar");

        verify(original).setPropertyResolved("foo", "bar");
    }

    @Test
    void isPropertyResolved() {
        when(original.isPropertyResolved()).thenReturn(true);

        assertTrue(biAdapted.isPropertyResolved());
    }

    @Test
    void putContext() {
        biAdapted.putContext(String.class, "foo");

        verify(original).putContext(String.class, "foo");
    }

    @Test
    void getContext() {
        when(original.getContext(String.class)).thenReturn("foo");

        assertEquals("foo", biAdapted.getContext(String.class));
    }

    @Test
    void getELResolver() {
        assertThrows(UnsupportedOperationException.class, () -> biAdapted.getELResolver());
    }

    @Test
    void getImportHandler() {
        var importHandler = mock(ImportHandler.class);
        when(importHandler.resolveClass("foo")).thenReturn((Class) String.class);
        when(original.getImportHandler()).thenReturn(importHandler);

        assertEquals(String.class, biAdapted.getImportHandler().resolveClass("foo"));
    }

    @Test
    void getFunctionMapper() throws Exception {
        var method = Object.class.getMethod("toString");
        var functionMapper = mock(jakarta.el.FunctionMapper.class);
        when(functionMapper.resolveFunction("foo", "bar")).thenReturn(method);
        when(original.getFunctionMapper()).thenReturn(functionMapper);

        assertEquals(method, biAdapted.getFunctionMapper().resolveFunction("foo", "bar"));
    }

    @Test
    void getLocale() {
        var locale = new Locale("en");
        when(original.getLocale()).thenReturn(locale);

        assertEquals(locale, biAdapted.getLocale());
    }

    @Test
    void setLocale() {
        var locale = new Locale("en");
        biAdapted.setLocale(locale);

        verify(original).setLocale(locale);
    }

    @Test
    void getVariableMapper() {
        var valueExpression = mock(jakarta.el.ValueExpression.class);
        when(valueExpression.getExpectedType()).thenReturn((Class) String.class);
        var variableMapper = mock(jakarta.el.VariableMapper.class);
        when(variableMapper.resolveVariable("foo")).thenReturn(valueExpression);
        when(original.getVariableMapper()).thenReturn(variableMapper);

        assertEquals(String.class, biAdapted.getVariableMapper().resolveVariable("foo").getExpectedType());
    }

    @Test
    void addEvaluationListener() {
        var listener = mock(EvaluationListener.class);
        biAdapted.addEvaluationListener(listener);

        verify(original).addEvaluationListener(assertArg(adaptedListener -> {
            adaptedListener.beforeEvaluation(null, "foo");
            verify(listener).beforeEvaluation(null, "foo");
            clearInvocations(listener);
        }));
    }

    @Test
    void getEvaluationListeners() {
        var listener = mock(EvaluationListener.class);
        when(original.getEvaluationListeners()).thenReturn(List.of(listener));
        biAdapted.getEvaluationListeners().get(0).beforeEvaluation(null, "foo");

        verify(listener).beforeEvaluation(null, "foo");
    }

    @Test
    void notifyBeforeEvaluation() {
        biAdapted.notifyBeforeEvaluation("foo");

        verify(original).notifyBeforeEvaluation("foo");
    }

    @Test
    void notifyAfterEvaluation() {
        biAdapted.notifyAfterEvaluation("foo");

        verify(original).notifyAfterEvaluation("foo");
    }

    @Test
    void notifyPropertyResolved() {
        biAdapted.notifyPropertyResolved("foo", "bar");

        verify(original).notifyPropertyResolved("foo", "bar");
    }

    @Test
    void isLambdaArgument() {
        when(original.isLambdaArgument("foo")).thenReturn(true);

        assertTrue(biAdapted.isLambdaArgument("foo"));
    }

    @Test
    void getLambdaArgument() {
        when(original.getLambdaArgument("foo")).thenReturn("bar");

        assertEquals("bar", biAdapted.getLambdaArgument("foo"));
    }

    @Test
    void enterLambdaScope() {
        biAdapted.enterLambdaScope(Map.of("foo", "bar"));

        verify(original).enterLambdaScope(Map.of("foo", "bar"));
    }

    @Test
    void exitLambdaScope() {
        biAdapted.exitLambdaScope();

        verify(original).exitLambdaScope();
    }

    @Test
    void convertToType() {
        when(original.convertToType("foo", String.class)).thenReturn("bar");

        assertEquals("bar", biAdapted.convertToType("foo", String.class));
    }
}
