package mx.edu.upqroo.kristenandroid.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.models.Day;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.Grades;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.models.Subject;
import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Materia;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.Semana;

class Converter {

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
                "GENERAL",
                alumno.getTopic(),
                alumno.getMatricula(),
                alumno.getContrasena()
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
}
