package mx.edu.upqroo.kristenandroid.api.kristen

import android.app.Application
import android.os.AsyncTask

import java.util.ArrayList

import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contenido
import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact
import mx.edu.upqroo.kristenandroid.data.models.Content
import mx.edu.upqroo.kristenandroid.data.models.ContentGallery
import mx.edu.upqroo.kristenandroid.data.models.ContentImage
import mx.edu.upqroo.kristenandroid.data.models.ContentLink
import mx.edu.upqroo.kristenandroid.data.models.ContentList
import mx.edu.upqroo.kristenandroid.data.models.ContentTitle
import mx.edu.upqroo.kristenandroid.data.models.ContentVideo
import mx.edu.upqroo.kristenandroid.data.models.NewsDetail
import mx.edu.upqroo.kristenandroid.data.repositories.ContactRepository

/**
 * <h1>KristenApiConverter</h1>
 * Class that converts the entities of the service into entities that are the views in the
 * application.
 */
internal object KristenApiConverter {

    fun publicacionToNewsDetail(post: PublicacionContenido): NewsDetail {
        return NewsDetail(post.idPublicaciones,
                post.url,
                post.titulo,
                post.descripcion,
                post.portada,
                post.categorias,
                post.fecha,
                post.idTiposPublicacion!!,
                post.autor,
                publicationContentToNewsContent(post.contenidos!!))
    }

    private fun publicationContentToNewsContent(contenidos: List<Contenido>): List<Content> {
        val contents = ArrayList<Content>()
        for (c in contenidos) {
            val contend = c.contenido
            when {
                c.idTipoContenidos == 1 -> contents.add(Content(contend!!.texto!!))
                c.idTipoContenidos == 2 ->
                    contents.add(ContentImage(contend!!.alt!!, contend.src!!))
                c.idTipoContenidos == 4 ->
                    contents.add(ContentLink(contend!!.texto!!, contend.url!!))
                c.idTipoContenidos == 5 ->
                    contents.add(ContentGallery(contend!!.imagenes!! as MutableList<String>))
                c.idTipoContenidos == 7 ->
                    contents.add(ContentVideo(contend!!.id!!, contend.servidor!!))
                c.idTipoContenidos == 8 ->
                    contents.add(ContentList(contend!!.titulo!!, contend.ordenada!!,
                        contend.elementos!! as MutableList<String>))
                c.idTipoContenidos == 9 -> contents.add(ContentTitle(contend!!.texto!!))
            }
        }
        return contents
    }

    /***
     * This method will receive a list of contacts to inset only if they are different from
     * the ones that are already in the database, in case that there is no contact in the databse
     * then they will be inserted without comparing anything.
     * @param contacts contacts from service
     * @param application application to get the repository instance
     */
    fun insertContacts(contacts: List<Contact>, application: Application) {
        val repo = ContactRepository.getInstance(application)
        AsyncTask.execute {
            for (contact in contacts) {
                repo.upsert(contact)
            }
        }
    }
}
