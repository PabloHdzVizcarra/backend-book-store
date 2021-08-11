package com.pablojvm.infrastructure;


public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(RequestData requestData);

    protected boolean checkNext(RequestData requestData) {
        if (next == null)
            return true;

        return next.check(requestData);
    }
}
