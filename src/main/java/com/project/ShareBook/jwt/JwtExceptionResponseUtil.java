package com.project.ShareBook.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ShareBook.common.ApiResponse;
import com.project.ShareBook.exception.JWTErrorType;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtExceptionResponseUtil {

    public static void unAuthentication(HttpServletResponse response, JWTErrorType jwtErrorType) throws IOException {
        ObjectMapper om = new ObjectMapper();
//        String responseBody = om.writeValueAsString(new ApiResponse<>(jwtErrorType.getStatus().value(), jwtErrorType.getDesc(), null));
        String responseBody = om.writeValueAsString(ApiResponse.errorResponse(jwtErrorType));
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(jwtErrorType.getStatus().value());
        response.getWriter().println(responseBody);
    }
}