package mx.edu.upqroo.kristenandroid.api.kristen.messages;

import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido;

public class PostContentMessage {
    private boolean successful;
    private PublicacionContenido publicacionContenido;

    public PostContentMessage(boolean successful, PublicacionContenido publicacionContenido) {
        this.successful = successful;
        this.publicacionContenido = publicacionContenido;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public PublicacionContenido getPublicacionContenido() {
        return publicacionContenido;
    }

    public void setPublicacionContenido(PublicacionContenido publicacionContenido) {
        this.publicacionContenido = publicacionContenido;
    }
}
