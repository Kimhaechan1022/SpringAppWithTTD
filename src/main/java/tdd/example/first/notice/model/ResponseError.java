package tdd.example.first.notice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseError {
    private String field;
    private String msg;

    public static ResponseError of(FieldError e){
        return ResponseError.builder()
                .field(e.getField())
                .msg(e.getDefaultMessage())
                .build();
    }
}
