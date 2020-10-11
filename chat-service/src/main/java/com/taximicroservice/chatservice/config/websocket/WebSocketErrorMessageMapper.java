package com.taximicroservice.chatservice.config.websocket;

import com.taximicroservice.chatservice.exception.ChatServiceException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Controller
@ControllerAdvice
public class WebSocketErrorMessageMapper {

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(ChatServiceException exception) {
        return exception.getMessage();
    }

}
