package mx.edu.upqroo.kristenandroid.api.kristen.messages

import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contacto

class ContactListMessage(val success : Boolean, val contacts : List<Contacto>)