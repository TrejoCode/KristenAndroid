package mx.edu.upqroo.kristenandroid.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.models.Config;
import mx.edu.upqroo.kristenandroid.models.Content;
import mx.edu.upqroo.kristenandroid.models.ContentGallery;
import mx.edu.upqroo.kristenandroid.models.ContentImage;
import mx.edu.upqroo.kristenandroid.models.ContentLink;
import mx.edu.upqroo.kristenandroid.models.ContentList;
import mx.edu.upqroo.kristenandroid.models.ContentTitle;
import mx.edu.upqroo.kristenandroid.models.ContentVideo;
import mx.edu.upqroo.kristenandroid.models.Day;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.Grades;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.models.NewsDetail;
import mx.edu.upqroo.kristenandroid.models.Subject;
import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Contenido;
import mx.edu.upqroo.kristenandroid.service.containers.Contenido_;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Materia;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.service.containers.Semana;

/**
 * <h1>Converter</h1>
 * Class that converts the entities of the service into entities that are the views in the
 * application.
 */
class Converter {

    /**
     * Converts a publication
     * @param publicacion a publication
     * @return a new
     */
    private static News PublicationToNews(Publicacion publicacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.US);
        return new News(publicacion.getIdPublicaciones(),
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

    /**
     * Convert an user into a general info entity
     * @param alumno student
     * @return General information
     */
    static GeneralInfo AlumnoToGeneralInfo(Alumno alumno) {
        return new GeneralInfo(alumno.getNombre(),
                alumno.getCarrera(),
                alumno.getNombreCarrera(),
                alumno.getCreditos(),
                alumno.getSituacion(),
                alumno.getPdoIni(),
                alumno.getCurp(),
                alumno.getNacimiento(),
                alumno.getDireccion(),
                alumno.getTelDomicilio(),
                alumno.getTelMovil(),
                alumno.getCorreo(),
                alumno.getMatricula(),
                alumno.getContrasena(),
                new Config(alumno.getConfig().getGeneralTopic(),
                        alumno.getConfig().getTopic(),
                        alumno.getConfig().getUrlCalendario(),
                        alumno.getConfig().getUrlBase())
        );
    }

    private static Grades CalificacionToGrade(Calificacion calificacion) {
        return new Grades(calificacion.getGrupo(), calificacion.getNombreMat(),
                calificacion.getCalificacion(), calificacion.getParcial1(),
                calificacion.getParcial2(), calificacion.getParcial3(),
                calificacion.getParcial4(), calificacion.getParcial5());
    }

    static List<Grades> CalificacionListToGradeList(
            List<Calificacion> calificacionList) {
        List<Grades> gradesList = new ArrayList<>();
        for (Calificacion c : calificacionList) {
            gradesList.add(CalificacionToGrade(c));
        }
        return gradesList;
    }

    private static Kardex KardexToKardex(Kardexs kardexs) {
        return new Kardex(kardexs.getNombreMat(),
                kardexs.getCalificacion(),
                kardexs.getCuatrimestre());
    }

    static List<Kardex> KardexListToKardexList(List<Kardexs> kardexsList) {
        List<Kardex> kardexList = new ArrayList<>();
        for (Kardexs c : kardexsList) {
            kardexList.add(KardexToKardex(c));
        }
        return kardexList;
    }

    static List<Day> SemanaToSchedule(Semana semana) {
        List<Day> dayList = new ArrayList<>();
        List<Subject> mondaySubjects = new ArrayList<>();
        for (Materia m : semana.getLunes()) {
            mondaySubjects.add(new Subject(m.getNombre(), m.getProfesor(), m.getHora()));
        }
        List<Subject> tuesdaySubjects = new ArrayList<>();
        for (Materia m : semana.getMartes()) {
            tuesdaySubjects.add(new Subject(m.getNombre(), m.getProfesor(), m.getHora()));
        }
        List<Subject> wednesdaySubjects = new ArrayList<>();
        for (Materia m : semana.getMiercoles()) {
            wednesdaySubjects.add(new Subject(m.getNombre(), m.getProfesor(), m.getHora()));
        }
        List<Subject> thursdaySubjects = new ArrayList<>();
        for (Materia m : semana.getJueves()) {
            thursdaySubjects.add(new Subject(m.getNombre(), m.getProfesor(), m.getHora()));
        }
        List<Subject> fridaySubjects = new ArrayList<>();
        for (Materia m : semana.getViernes()) {
            fridaySubjects.add(new Subject(m.getNombre(), m.getProfesor(), m.getHora()));
        }
        Day lunes = new Day(PreferencesManager
                .getInstance().getContext().getString(R.string.monday), mondaySubjects);
        Day martes = new Day(PreferencesManager
                .getInstance().getContext().getString(R.string.tuesday), tuesdaySubjects);
        Day miercoles = new Day(PreferencesManager
                .getInstance().getContext().getString(R.string.wednesday), wednesdaySubjects);
        Day jueves = new Day(PreferencesManager
                .getInstance().getContext().getString(R.string.thursday), thursdaySubjects);
        Day viernes = new Day(PreferencesManager
                .getInstance().getContext().getString(R.string.friday), fridaySubjects);

        dayList.add(lunes);
        dayList.add(martes);
        dayList.add(miercoles);
        dayList.add(jueves);
        dayList.add(viernes);

        return dayList;
    }

    static NewsDetail PublicacionToNewsDetail(PublicacionContenido post) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.US);
        return new NewsDetail(post.getIdPublicaciones(),
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
                contents.add(new ContentGallery(contenido.getCantidad(), contenido.getImagenes()));
            } else if (c.getIdTipoContenidos().equals(7)) {
                contents.add(new ContentVideo(contenido.getId(), contenido.getServidor()));
            } else if (c.getIdTipoContenidos().equals(8)) {
                contents.add(new ContentList(contenido.getTitulo(), contenido.getCantidad(),
                        contenido.getOrdenada(), contenido.getElementos()));
            } else if (c.getIdTipoContenidos().equals(9)) {
                contents.add(new ContentTitle(contenido.getTexto()));
            }
        }
        return contents;
    }
}
