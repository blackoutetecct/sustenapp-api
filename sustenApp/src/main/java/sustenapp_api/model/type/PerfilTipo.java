package sustenapp_api.model.type;

import sustenapp_api.exception.ExceptionGeneric;

public enum PerfilTipo {
    ADMINISTRADOR, SUPORTE, USUARIO;

    public static PerfilTipo getRecurso(String recurso){
        if(recurso.equalsIgnoreCase("ADMINISTRADOR"))
            return PerfilTipo.ADMINISTRADOR;

        if (recurso.equalsIgnoreCase("SUPORTE"))
            return PerfilTipo.SUPORTE;

        if (recurso.equalsIgnoreCase("USUARIO"))
            return PerfilTipo.USUARIO;

        throw new ExceptionGeneric("", "", 400);
    }
}
