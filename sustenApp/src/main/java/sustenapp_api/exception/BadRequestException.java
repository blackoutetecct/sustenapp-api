package sustenapp_api.exception;

public class BadRequestException extends RuntimeException {
    private final String title, message = "INFORMAÇÕES INCOMPATIVIES COM O ESPERADO";
    private final int status = 400;

    public BadRequestException(String title) {
        this.title = "SERVICO DE "+title;
    }
    public ResponseBody getCorpoResposta(){
        return new ResponseBody(title, message, status);
    }
}