package com.nompay.bank.solutions.clientService.errors;

import com.nompay.bank.solutions.clientService.errors.custom.TransactionException;
import com.nompay.bank.solutions.clientService.errors.custom.UnauthorizedException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {

  @GraphQlExceptionHandler(IllegalArgumentException.class)
  public GraphQLError handleIllegalArgument(IllegalArgumentException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Invalid input: " + ex.getMessage())
        .errorType(ErrorType.BAD_REQUEST)
        .build();
  }

  @GraphQlExceptionHandler(ValidationException.class)
  public GraphQLError handleValidation(ValidationException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Validation error: " + ex.getMessage())
        .errorType(ErrorType.BAD_REQUEST)
        .build();
  }


  @GraphQlExceptionHandler(DataIntegrityViolationException.class)
  public GraphQLError dataIntegrityViolationHandler(DataIntegrityViolationException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Constraint violation Exception: " + ex.getMessage())
        .errorType(ErrorType.FORBIDDEN)
        .build();
  }

  @GraphQlExceptionHandler(UnauthorizedException.class)
  public GraphQLError unauthorizedExceptionHandler(UnauthorizedException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Custom Unauthorized Exception: " + ex.getMessage())
        .errorType(ErrorType.UNAUTHORIZED)
        .build();
  }

  @GraphQlExceptionHandler(TransactionException.class)
  public GraphQLError badRequestExceptionHandler(TransactionException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Error with the transaction: " + ex.getMessage())
        .errorType(ErrorType.BAD_REQUEST)
        .build();
  }

  @GraphQlExceptionHandler(BadRequestException.class)
  public GraphQLError transactionExceptionHandler(BadRequestException ex) {
    return GraphqlErrorBuilder.newError()
        .message("Bad Request Error: " + ex.getMessage())
        .errorType(ErrorType.BAD_REQUEST)
        .build();
  }


}
