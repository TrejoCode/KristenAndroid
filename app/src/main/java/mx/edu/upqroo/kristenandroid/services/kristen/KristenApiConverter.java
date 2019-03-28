package mx.edu.upqroo.kristenandroid.services.kristen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.edu.upqroo.kristenandroid.database.entities.Content;
import mx.edu.upqroo.kristenandroid.database.entities.ContentGallery;
import mx.edu.upqroo.kristenandroid.database.entities.ContentImage;
import mx.edu.upqroo.kristenandroid.database.entities.ContentLink;
import mx.edu.upqroo.kristenandroid.database.entities.ContentList;
import mx.edu.upqroo.kristenandroid.database.entities.ContentTitle;
import mx.edu.upqroo.kristenandroid.database.entities.ContentVideo;
import mx.edu.upqroo.kristenandroid.database.entities.News;
import mx.edu.upqroo.kristenandroid.database.entities.NewsDetail;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.Contenido;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.Contenido_;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.PublicacionContenido;

/**
 * <h1>KristenApiConverter</h1>
 * Class that converts the entities of the service into entities that are the views in the
 * application.
 */
class KristenApiConverter {

    /**
     * Converts a publication
     * @param publicacion a publication
     * @return a new
     */
    private static News PublicationToNews(Publicacion publicacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM, yyyy",
                Locale.getDefault());
        return new News(publicacion.getIdPublicaciones(),
                publicacion.getUrl(),
                publicacion.getIdTipos_Publicacion(),
                publicacion.getTitulo(),
                publicacion.getDescripcion(),
                publicacion.getCategorias(),
                publicacion.getPortada(),
                formatter.format(publicacion.getFecha()));
    }

    /**
     * Convert a list of publications into a list of news
     * @param publicacionList a publications list
     * @return a news list
     */
    static List<News> PublicationListToNewsList(List<Publicacion> publicacionList) {
        List<News> newsList = new ArrayList<>();
        for (Publicacion p : publicacionList) {
            if (p.getCategorias() != null &&
                    p.getDescripcion() != null &&
                    p.getFecha() != null &&
                    p.getPortada() != null &&
                    p.getTitulo() != null) {
                newsList.add(PublicationToNews(p));
            }

        }
        return newsList;
    }

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
}
