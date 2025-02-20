package org.make.ext.lang;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static io.vavr.API.Option;
import static jakarta.servlet.RequestDispatcher.ERROR_MESSAGE;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class HandleErrorRedirectController extends AbstractErrorController {

  private static final Logger logger = LoggerFactory.getLogger(HandleErrorRedirectController.class);

  public HandleErrorRedirectController(final ErrorAttributes attributes) {
    super(attributes);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Applicative<String>> error(@NonNull final HttpServletRequest r) {
    final Integer statusCode =
        Option(r.getAttribute(ERROR_STATUS_CODE))
            .map(it -> Integer.valueOf(String.valueOf(it)))
            .getOrElse(INTERNAL_SERVER_ERROR.value());
    final String message =
        Option(r.getAttribute(ERROR_MESSAGE))
            .map(String::valueOf)
            .getOrElse(INTERNAL_SERVER_ERROR.getReasonPhrase());
    if (logger.isDebugEnabled()) {
      logger.debug("handle error: {}, {}", statusCode, message);
    }
    return ok(Applicative.from(statusCode, message));
  }
}