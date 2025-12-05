package fr.entitesCsv;

import fr.entitesData.ButData;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui stock une list de type ButData du fichier CSV goalscorers.csv
 */
public class ButCsv {

    private List<ButData> butData = new ArrayList<>();


    /**
     * @return une liste de ButData
     */
    public List<ButData> getButData() {
        return butData;
    }

    /**
     * @param butData la nouvelle list de butData
     */
    public void setButData(List<ButData> butData) {
        this.butData = butData;
    }

    @Override
    public String toString() {
        return "ButCsv{" +
                "butData=" + butData +
                '}';
    }
}
