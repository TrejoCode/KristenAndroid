package mx.edu.upqroo.kristenandroid.service.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.Kardex;

public class KardexListMessage {
    private List<Kardex> kardexList;

    public KardexListMessage(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }
}
