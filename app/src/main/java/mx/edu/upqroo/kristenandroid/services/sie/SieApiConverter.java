package mx.edu.upqroo.kristenandroid.services.sie;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.models.Config;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.data.models.Grades;
import mx.edu.upqroo.kristenandroid.data.models.Kardex;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.repositories.DayRepository;
import mx.edu.upqroo.kristenandroid.repositories.SubjectRepository;
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

    static void SemanaToSchedule(Semana semana, Application application) {
        AsyncTask.execute(() -> {
            SubjectRepository subjectRepository = SubjectRepository.getInstance(application);
            DayRepository dayRepository = DayRepository.getInstance(application);
            dayRepository.deleteAll();

            dayRepository.insert(new Day(0, application.getString(R.string.monday),
                    SessionManager.getInstance().getSession().getUserId()), id -> {
                for (Materia m : semana.getLunes()) {
                    subjectRepository.insert(
                            new Subject(0,
                                    m.getNombre(),
                                    m.getProfesor(),
                                    m.getHora(),
                                    id));
                }
            });
            dayRepository.insert(new Day(0,
                    application.getString(R.string.tuesday),
                    SessionManager.getInstance().getSession().getUserId()), id -> {
                for (Materia m : semana.getMartes()) {
                    subjectRepository.insert(
                            new Subject(0,
                                    m.getNombre(),
                                    m.getProfesor(),
                                    m.getHora(),
                                    id));
                }
            });
            dayRepository.insert(new Day(0,
                    application.getString(R.string.wednesday),
                    SessionManager.getInstance().getSession().getUserId()), id -> {
                for (Materia m : semana.getMiercoles()) {
                    subjectRepository.insert(
                            new Subject(0,
                                    m.getNombre(),
                                    m.getProfesor(),
                                    m.getHora(),
                                    id));
                }
            });
            dayRepository.insert(new Day(0,
                    application.getString(R.string.thursday),
                    SessionManager.getInstance().getSession().getUserId()), id -> {
                for (Materia m : semana.getJueves()) {
                    subjectRepository.insert(
                            new Subject(0,
                                    m.getNombre(),
                                    m.getProfesor(),
                                    m.getHora(),
                                    id));
                }
            });
            dayRepository.insert(new Day(0, application.getString(R.string.friday),
                    SessionManager.getInstance().getSession().getUserId()), id -> {
                for (Materia m : semana.getViernes()) {
                    subjectRepository.insert(
                            new Subject(0,
                                    m.getNombre(),
                                    m.getProfesor(),
                                    m.getHora(),
                                    id));
                }
            });
        });
    }
}
