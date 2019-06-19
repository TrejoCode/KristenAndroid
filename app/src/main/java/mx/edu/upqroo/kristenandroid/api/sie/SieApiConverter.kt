package mx.edu.upqroo.kristenandroid.api.sie

import android.app.Application
import android.os.AsyncTask
import android.view.View

import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.database.entities.Day
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation
import mx.edu.upqroo.kristenandroid.data.models.Config
import mx.edu.upqroo.kristenandroid.data.repositories.DayRepository
import mx.edu.upqroo.kristenandroid.data.repositories.GradeRepository
import mx.edu.upqroo.kristenandroid.data.repositories.KardexRepository
import mx.edu.upqroo.kristenandroid.data.repositories.SubjectRepository
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.api.sie.containers.Alumno
import mx.edu.upqroo.kristenandroid.api.sie.containers.Materia
import mx.edu.upqroo.kristenandroid.api.sie.containers.Semana

internal object SieApiConverter {

    /**
     * Convert an user into a general info entity
     * @param alumno student
     * @return General information
     */
    fun AlumnoToGeneralInfo(alumno: Alumno): UserInformation {
        return UserInformation(alumno.nombre!!,
                alumno.carrera!!,
                alumno.nombreCarrera!!,
                alumno.creditos!!,
                alumno.situacion!!,
                alumno.pdoIni!!,
                alumno.curp!!,
                alumno.nacimiento!!,
                alumno.direccion!!,
                alumno.telDomicilio!!,
                alumno.telMovil!!,
                alumno.correo!!,
                alumno.matricula!!,
                alumno.contrasena!!,
                Config(alumno.config!!.generalTopic!!,
                        alumno.config!!.topic!!,
                        alumno.config!!.urlBase!!,
                        alumno.config!!.token!!)
        )
    }

    fun insertGrades(app: Application, grades: List<Grade>) {
        val repo = GradeRepository.getInstance(app)
        AsyncTask.execute {
            repo.deleteAll()
            for (grade in grades) {
                repo.insert(grade)
            }
        }
    }

    fun insertKardex(app: Application, kardexList: List<Kardex>) {
        val repo = KardexRepository.getInstance(app)
        AsyncTask.execute {
            repo.deleteAll()
            for (kardex in kardexList) {
                repo.insert(kardex)
            }
        }
    }

    fun insertSchedule(semana: Semana, application: Application) {
        AsyncTask.execute {
            val subjectRepository = SubjectRepository.getInstance(application)
            val dayRepository = DayRepository.getInstance(application)
            dayRepository.deleteAll()
            dayRepository.insert(Day(0,
                    application.getString(R.string.monday),
                    SessionManager.instance.session.userId),
                    object : DayRepository.OnDayInserted {
                        override fun onInsertCompleted(id: Long) {
                            for (m in semana.lunes!!) {
                                subjectRepository.insert(
                                        Subject(0,
                                                m.nombre!!,
                                                m.profesor!!,
                                                m.hora!!,
                                                id))
                            }
                        }
                    })
            dayRepository.insert(Day(0,
                    application.getString(R.string.tuesday),
                    SessionManager.instance.session.userId),
                    object : DayRepository.OnDayInserted {
                        override fun onInsertCompleted(id: Long) {
                            for (m in semana.martes!!) {
                                subjectRepository.insert(
                                        Subject(0,
                                                m.nombre!!,
                                                m.profesor!!,
                                                m.hora!!,
                                                id))
                            }
                        }
                    })
            dayRepository.insert(Day(0,
                    application.getString(R.string.wednesday),
                    SessionManager.instance.session.userId),
                    object : DayRepository.OnDayInserted {
                        override fun onInsertCompleted(id: Long) {
                            for (m in semana.miercoles!!) {
                                subjectRepository.insert(
                                        Subject(0,
                                                m.nombre!!,
                                                m.profesor!!,
                                                m.hora!!,
                                                id))
                            }
                        }
                    })
            dayRepository.insert(Day(0,
                    application.getString(R.string.thursday),
                    SessionManager.instance.session.userId),
                    object : DayRepository.OnDayInserted {
                        override fun onInsertCompleted(id: Long) {
                            for (m in semana.jueves!!) {
                                subjectRepository.insert(
                                        Subject(0,
                                                m.nombre!!,
                                                m.profesor!!,
                                                m.hora!!,
                                                id))
                            }
                        }
                    })
            dayRepository.insert(Day(0,
                    application.getString(R.string.friday),
                    SessionManager.instance.session.userId),
                    object : DayRepository.OnDayInserted {
                        override fun onInsertCompleted(id: Long) {
                            for (m in semana.viernes!!) {
                                subjectRepository.insert(
                                        Subject(0,
                                                m.nombre!!,
                                                m.profesor!!,
                                                m.hora!!,
                                                id))
                            }
                        }
                    })
        }
    }
}
