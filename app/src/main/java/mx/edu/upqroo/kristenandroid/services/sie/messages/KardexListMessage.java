package mx.edu.upqroo.kristenandroid.services.sie.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.Kardex;

public class KardexListMessage {
    private boolean successful;
    private List<Kardex> kardexList;

    public KardexListMessage(boolean successful, List<Kardex> kardexList) {
        this.successful = successful;
        this.kardexList = kardexList;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }
}
