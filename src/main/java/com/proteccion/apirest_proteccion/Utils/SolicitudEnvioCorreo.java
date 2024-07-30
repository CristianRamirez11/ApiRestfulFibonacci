package com.proteccion.apirest_proteccion.Utils;

public class SolicitudEnvioCorreo {

    private String ToEmail;
    private String AsuntoCorreo;
    private String CuerpoCorreo;

    public SolicitudEnvioCorreo() {
    }

    public String getToEmail() {
        return ToEmail;
    }

    public void setToEmail(String toEmail) {
        ToEmail = toEmail;
    }

    public String getAsuntoCorreo() {
        return AsuntoCorreo;
    }

    public void setAsuntoCorreo(String asuntoCorreo) {
        AsuntoCorreo = asuntoCorreo;
    }

    public String getCuerpoCorreo() {
        return CuerpoCorreo;
    }

    public void setCuerpoCorreo(String cuerpoCorreo) {
        CuerpoCorreo = cuerpoCorreo;
    }
}
