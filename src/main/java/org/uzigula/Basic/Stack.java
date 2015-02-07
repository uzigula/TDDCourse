package org.uzigula.Basic;

        import java.io.InvalidObjectException;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by umamani on 31/01/2015.
 */
public class Stack {
    private List<Object> _elements;
    private int _maxSize;

    public Stack(int maxSize){
        _elements = new ArrayList<Object>();
        _maxSize =maxSize;
    }
    public boolean isEmpty() {
        return _elements.isEmpty();
    }

    public Object Pop() throws InvalidObjectException {
        if (isEmpty()) throw new InvalidObjectException("Stack is Empty!!!");
        Object lastElement = _elements.get(_elements.size()-1);
        _elements.remove(lastElement);
        return lastElement;
    }

    public void Push(Object element) throws StackOverFlowException{
        if (IsFull()) throw new StackOverFlowException();
        _elements.add(element);
    }

    private boolean IsFull() {
        return _elements.size()==_maxSize;
    }
}
