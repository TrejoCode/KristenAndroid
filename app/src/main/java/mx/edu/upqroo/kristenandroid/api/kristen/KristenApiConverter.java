package mx.edu.upqroo.kristenandroid.api.kristen;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contenido;
import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contenido_;
import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;
import mx.edu.upqroo.kristenandroid.data.models.Content;
import mx.edu.upqroo.kristenandroid.data.models.ContentGallery;
import mx.edu.upqroo.kristenandroid.data.models.ContentImage;
import mx.edu.upqroo.kristenandroid.data.models.ContentLink;
import mx.edu.upqroo.kristenandroid.data.models.ContentList;
import mx.edu.upqroo.kristenandroid.data.models.ContentTitle;
import mx.edu.upqroo.kristenandroid.data.models.ContentVideo;
import mx.edu.upqroo.kristenandroid.data.models.NewsDetail;
import mx.edu.upqroo.kristenandroid.data.repositories.ContactRepository;

/**
 * <h1>KristenApiConverter</h1>
 * Class that converts the entities of the service into entities that are the views in the
 * application.
 */
class KristenApiConverter {

    static NewsDetail PublicacionToNewsDetail(PublicacionContenido post) {
        return new NewsDetail(post.getIdPublicaciones(),
                post.getUrl(),
                post.getTitulo(),
                post.getDescripcion(),
                post.getPortada(),
                post.getCategorias(),
                post.getFecha(),
                post.getIdTiposPublicacion(),
                post.getAutor(),
                publicationContentToNewsContent(post.getContenidos()));
    }

    private static List<Content> publicationContentToNewsContent(List<Contenido> contenidos) {
        List<Content> contents = new ArrayList<>();
        for (Contenido c : contenidos) {
            Contenido_ contenido = c.getContenido();
            if (c.getIdTipoContenidos().equals(1)) {
                contents.add(new Content(contenido.getTexto()));
            } else if (c.getIdTipoContenidos().equals(2)) {
                contents.add(new ContentImage(contenido.getAlt(),
                        contenido.getSrc()));
            } else if (c.getIdTipoContenidos().equals(4)) {
                contents.add(new ContentLink(contenido.getTexto(), contenido.getUrl()));
            } else if (c.getIdTipoContenidos().equals(5)) {
                contents.add(new ContentGallery(contenido.getImagenes()));
            } else if (c.getIdTipoContenidos().equals(7)) {
                contents.add(new ContentVideo(contenido.getId(), contenido.getServidor()));
            } else if (c.getIdTipoContenidos().equals(8)) {
                contents.add(new ContentList(contenido.getTitulo(), contenido.getOrdenada(),
                        contenido.getElementos()));
            } else if (c.getIdTipoContenidos().equals(9)) {
                contents.add(new ContentTitle(contenido.getTexto()));
            }
        }
        return contents;
    }

    /***
     * This method will receive a list of contacts to inset only if they are different from
     * the ones that are already in the database, in case that there is no contact in the databse
     * then they will be inserted without comparing anything.
     * @param contacts contacts from service
     * @param application application to get the repository instance
     */
    static void insertContacts(List<Contact> contacts, Application application) {
        ContactRepository repo = ContactRepository.getInstance(application);
        AsyncTask.execute(() -> {
            for (Contact contact: contacts) {
                repo.upsert(contact);
            }
        });
    }
}
