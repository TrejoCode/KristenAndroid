package mx.edu.upqroo.kristenandroid.api.sie.messages

import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation

class LoginMessage {
    var isResult: Boolean = false
    var student: UserInformation? = null
    lateinit var message: String

    constructor(result: Boolean, student: UserInformation) {
        this.isResult = result
        this.student = student
    }

    constructor(result: Boolean, message: String) {
        this.isResult = result
        this.message = message
    }
}
