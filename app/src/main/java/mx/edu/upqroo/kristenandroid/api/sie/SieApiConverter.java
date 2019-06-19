package mx.edu.upqroo.kristenandroid.api.sie;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.data.models.Config;
import mx.edu.upqroo.kristenandroid.data.repositories.DayRepository;
import mx.edu.upqroo.kristenandroid.data.repositories.GradeRepository;
import mx.edu.upqroo.kristenandroid.data.repositories.KardexRepository;
import mx.edu.upqroo.kristenandroid.data.repositories.SubjectRepository;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.api.sie.containers.Alumno;
import mx.edu.upqroo.kristenandroid.api.sie.containers.Materia;
import mx.edu.upqroo.kristenandroid.api.sie.containers.Semana;

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

    static void insertGrades(Application app, List<Grade> grades) {
        GradeRepository repo = GradeRepository.Companion.getInstance(app);
        AsyncTask.execute(() -> {
            repo.deleteAll();
            for (Grade grade : grades) {
                repo.insert(grade);
            }
        });
    }

    static void insertKardex(Application app, List<Kardex> kardexList) {
        KardexRepository repo = KardexRepository.Companion.getInstance(app);
        AsyncTask.execute(() -> {
            repo.deleteAll();
            for (Kardex kardex : kardexList) {
                repo.insert(kardex);
            }
        });
    }

    static void insertSchedule(Semana semana, Application application) {
        AsyncTask.execute(() -> {
            SubjectRepository subjectRepository = SubjectRepository.Companion.getInstance(application);
            DayRepository dayRepository = DayRepository.Companion.getInstance(application);
            dayRepository.deleteAll();

            dayRepository.insert(new Day(0, application.getString(R.string.monday),
                    SessionManager.Companion.getInstance().getSession().getUserId()), id -> {
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
                    SessionManager.Companion.getInstance().getSession().getUserId()), id -> {
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
                    SessionManager.Companion.getInstance().getSession().getUserId()), id -> {
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
                    SessionManager.Companion.getInstance().getSession().getUserId()), id -> {
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
                    SessionManager.Companion.getInstance().getSession().getUserId()), id -> {
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
