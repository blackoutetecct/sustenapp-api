package sustenapp_api.model.type;

import sustenapp_api.exception.ExceptionGeneric;

public enum RecursoTipo {
    HIDRICO, ELETRICO;

    public static RecursoTipo getRecurso(String recurso){
        if(recurso.equalsIgnoreCase("HIDRICO"))
            return RecursoTipo.HIDRICO;

        if (recurso.equalsIgnoreCase("ELETRICO"))
            return RecursoTipo.ELETRICO;

        throw new ExceptionGeneric("", "", 400);
    }
}
