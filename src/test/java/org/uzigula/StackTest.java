package org.uzigula;
import org.junit.Before;
import org.junit.Test;

import java.io.InvalidObjectException;

import static org.junit.Assert.*;

/**
 * Created by umamani on 31/01/2015.
 */
public class StackTest {

    private Stack stack;

    @Before
    public void Setup(){
        stack = new Stack(10);
    }

    @Test
    public void CanaryTest (){
       Stack stack = new Stack(10);
   }

   @Test
   public void EmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test(expected = InvalidObjectException.class)
    public void Pop_TryTOPopAnElement_GetExceptionEmptyStack() throws InvalidObjectException{
            Object element = stack.Pop();
    }

    //Metodo_Escenario_Resultado_o_Comportamiento_Esperado
    @Test
    public void Pop_PushElementOnePopAnElement_ShouldGetElementOne() throws InvalidObjectException, StackOverFlowException {
        stack.Push("One");
        Object element = stack.Pop();
        assertEquals("One",element.toString());
    }
    @Test
    public void Push_PushAnElementIntoEmptyStack_ShouldGetStackIsNotEmpty() throws StackOverFlowException{
        stack.Push("One");
        assertFalse("Stack Should not Be Empty",stack.isEmpty());
    }

    @Test
    public void Pop_PushTwoElementsThenPop_ShouldGetLastElement() throws InvalidObjectException, StackOverFlowException {
        stack.Push("One");
        stack.Push("Two");
        Object element = stack.Pop();
        assertEquals("Two",element.toString());
    }

    @Test
    public void Pop_PushTwoElementsThenPopTwoTimes_ShouldGetLastElements() throws InvalidObjectException,StackOverFlowException {
        stack.Push("One");
        stack.Push("Two");
        Object element1 = stack.Pop();
        Object element2 = stack.Pop();
        assertEquals("Two",element1.toString());
        assertEquals("One",element2.toString());
    }

    @Test
    public void Pop_PushTwoElementsThenPopTwoTimes_ShouldGetLastElementsAndStackShouldBeEmpty() throws InvalidObjectException,StackOverFlowException {
        stack.Push("One");
        stack.Push("Two");
        Object element1 = stack.Pop();
        Object element2 = stack.Pop();
        assertEquals("Two",element1.toString());
        assertEquals("One",element2.toString());
        assertTrue("Stack Should be empty", stack.isEmpty());
    }

    @Test(expected = StackOverFlowException.class)
    public void Push_PushTwoElementsINtoTwoElementsSizeStackInsertAThirdOne_ShouldGetAnException() throws StackOverFlowException{
        Stack stack = new Stack(2);
        stack.Push("One");
        stack.Push("Two");
        stack.Push("Three");
    }
}
