package br.com.pb.compasso.library.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseBody{

    private int status;

    private String message;

    private long timeStamp;

}