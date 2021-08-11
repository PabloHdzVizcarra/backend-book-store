package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;

import io.javalin.http.Context;

public class GetBodyDataMiddleware extends Middleware {

    public GetBodyDataMiddleware() {
    }

    @Override
    public boolean check(RequestData requestData) {
        Context httpContext = requestData.getHttpContext();
        Mapper mapper = requestData.getMapper();
        DataUser dataUser = mapper.createDataUser(httpContext.body());
        requestData.setDataUser(dataUser);
        return true;
    }
}
