package mx.edu.upqroo.kristenandroid.services.sie;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.Application;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.database.entities.Config;
import mx.edu.upqroo.kristenandroid.database.entities.Day;
import mx.edu.upqroo.kristenandroid.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.database.entities.Grades;
import mx.edu.upqroo.kristenandroid.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.repositories.DayRepository;
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
    static UserInformation AlumnoToGeneralInfo(Alumno alumno) {
        return new UserInformation(alumno.getNombre(),
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
                        alumno.getConfig().getUrlBase(),
                        alumno.getConfig().getToken())
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

    static List<Day> SemanaToSchedule(Semana semana, Application application) {
        List<Day> dayList = new ArrayList<>();
        // Hay que recuperar el ID del dia que se acaba de inseratar para entonces insertar los subjects como sus hijos
        List<Subject> mondaySubjects = new ArrayList<>();
        for (Materia m : semana.getLunes()) {
            mondaySubjects.add(new Subject(0, m.getNombre(), m.getProfesor(), m.getHora()));
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
        Day lunes = new Day(0,
                PreferencesManager.getInstance().getContext().getString(R.string.monday),
                SessionHelper.getInstance().getSession().getUserId());

        Day martes = new Day(0,
                PreferencesManager.getInstance().getContext().getString(R.string.tuesday),
                SessionHelper.getInstance().getSession().getUserId());

        Day miercoles = new Day(0,
                PreferencesManager.getInstance().getContext().getString(R.string.wednesday),
                SessionHelper.getInstance().getSession().getUserId());

        Day jueves = new Day(0,
                PreferencesManager.getInstance().getContext().getString(R.string.thursday),
                SessionHelper.getInstance().getSession().getUserId());

        Day viernes = new Day(0,
                PreferencesManager.getInstance().getContext().getString(R.string.friday),
                SessionHelper.getInstance().getSession().getUserId());

        AsyncTask.execute(() -> {
            DayRepository.getInstance(application).insert(lunes);
            DayRepository.getInstance(application).insert(martes);
            DayRepository.getInstance(application).insert(miercoles);
            DayRepository.getInstance(application).insert(jueves);
            DayRepository.getInstance(application).insert(viernes);
        });

        dayList.add(lunes);
        dayList.add(martes);
        dayList.add(miercoles);
        dayList.add(jueves);
        dayList.add(viernes);

        return dayList;
    }
}
