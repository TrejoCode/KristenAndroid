package mx.edu.upqroo.kristenandroid.services.sie;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.models.Config;
import mx.edu.upqroo.kristenandroid.models.Day;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.Grades;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.models.Subject;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Alumno;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Materia;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Semana;

class SieApiConverter {

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
}
