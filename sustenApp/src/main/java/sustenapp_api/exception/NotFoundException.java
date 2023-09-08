package sustenapp_api.exception;

public class NotFoundException extends RuntimeException {
    private final String title, message = "INFORMAÇÕES PARA A SEGUINTE IDENTIFICACAO SAO INEXISTENTES";
    private final int status = 404;

    public NotFoundException(String title) {
        this.title = "SERVICO DE "+title;
    }
    public ResponseBody getCorpoResposta(){
        return new ResponseBody(title, message, status);
    }
}