package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.javalin.http.Context;

public class GetBodyDataUserMiddleware extends Middleware {

    public GetBodyDataUserMiddleware() {
    }

    @Override
    public boolean check(RequestData requestData) {
        Context httpContext = requestData.getHttpContext();
        Mapper mapper = requestData.getMapper();
        Logger logger = requestData.getLoggerService();
        DataUser dataUser = mapper.createDataUser(httpContext.body());

        if (dataUser == null) {
            logger.log(Level.INFO,
                    "Request data could not be read");
            httpContext.status(400);
            httpContext.result("request data could not be read");
            return false;
        }

        requestData.setDataUser(dataUser);
        return checkNext(requestData);
    }
}
