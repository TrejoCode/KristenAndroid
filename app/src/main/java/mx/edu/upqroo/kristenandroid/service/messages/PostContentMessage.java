package mx.edu.upqroo.kristenandroid.service.messages;

import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;

public class PostContentMessage {
    private PublicacionContenido publicacionContenido;

    public PostContentMessage(PublicacionContenido publicacionContenido) {
        this.publicacionContenido = publicacionContenido;
    }

    public PublicacionContenido getPublicacionContenido() {
        return publicacionContenido;
    }

    public void setPublicacionContenido(PublicacionContenido publicacionContenido) {
        this.publicacionContenido = publicacionContenido;
    }
}
